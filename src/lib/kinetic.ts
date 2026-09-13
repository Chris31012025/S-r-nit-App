/** Titre cinétique : chaque mot se lève à son tour derrière un masque. */
import { el } from "./dom";

const WORD_STEP_MS = 90;

export function splitWords(text: string): string[] {
  return text.split(/\s+/).filter((word) => word.length > 0);
}

export function kineticTitle(text: string, options: { tag?: "h1" | "h2" | "p"; step?: number } = {}): HTMLElement {
  const { tag = "h2", step = WORD_STEP_MS } = options;
  const words = splitWords(text);

  return el(
    tag,
    { className: "hero-title" },
    words.flatMap((word, index) => [
      el("span", { className: "word" }, [el("span", { style: `--word-delay:${index * step}ms` }, [word])]),
      index < words.length - 1 ? document.createTextNode(" ") : null
    ])
  );
}
