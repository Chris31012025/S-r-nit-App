type SpeechRecognitionLike = {
  lang: string;
  continuous: boolean;
  interimResults: boolean;
  onresult: ((event: unknown) => void) | null;
  onerror: ((event: unknown) => void) | null;
  onend: (() => void) | null;
  start: () => void;
  stop: () => void;
};

type SpeechWindow = Window & {
  SpeechRecognition?: new () => SpeechRecognitionLike;
  webkitSpeechRecognition?: new () => SpeechRecognitionLike;
};

export function isSpeechRecognitionSupported(): boolean {
  const w = window as SpeechWindow;
  return Boolean(w.SpeechRecognition || w.webkitSpeechRecognition);
}

export function startDictation(onResult: (text: string) => void, onDone: () => void): (() => void) | null {
  const w = window as SpeechWindow;
  const Recognition = w.SpeechRecognition ?? w.webkitSpeechRecognition;
  if (!Recognition) return null;

  const recognition = new Recognition();
  recognition.lang = "fr-FR";
  recognition.continuous = false;
  recognition.interimResults = false;

  recognition.onresult = (event) => {
    const results = (event as { results: { transcript: string }[][] }).results;
    const transcript = Array.from(results)
      .map((result) => result[0]?.transcript ?? "")
      .join(" ");
    onResult(transcript);
  };
  recognition.onerror = () => onDone();
  recognition.onend = () => onDone();

  recognition.start();
  return () => recognition.stop();
}
