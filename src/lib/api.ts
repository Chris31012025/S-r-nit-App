import { scoreOralAnswerLocally, type OralScoreResult } from "./oral-score";

export type OralScoreRequest = {
  questionId: number;
  question: string;
  sampleAnswer: string;
  keywords: string[];
  userAnswer: string;
};

const MAX_ANSWER_LENGTH = 2000;

export async function requestOralScore(payload: OralScoreRequest): Promise<OralScoreResult> {
  const userAnswer = payload.userAnswer.slice(0, MAX_ANSWER_LENGTH);

  try {
    const controller = new AbortController();
    const timeout = setTimeout(() => controller.abort(), 12000);
    const response = await fetch("/api/oral", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ ...payload, userAnswer }),
      signal: controller.signal
    });
    clearTimeout(timeout);

    if (!response.ok) throw new Error(`Score API failed with status ${response.status}`);
    const data = (await response.json()) as OralScoreResult;
    if (typeof data.score !== "number" || !Array.isArray(data.matchedKeywords)) {
      throw new Error("Unexpected score API response shape");
    }
    return data;
  } catch {
    return scoreOralAnswerLocally(userAnswer, payload.keywords);
  }
}
