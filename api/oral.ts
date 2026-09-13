import type { VercelRequest, VercelResponse } from "@vercel/node";
import { scoreOralAnswerLocally } from "../src/lib/oral-score";

const MAX_LENGTH = 2000;
const MAX_KEYWORDS = 20;
const GEMINI_MODEL = "gemini-1.5-flash";

type OralScoreRequestBody = {
  questionId: number;
  question: string;
  sampleAnswer: string;
  keywords: string[];
  userAnswer: string;
};

function isValidBody(body: unknown): body is OralScoreRequestBody {
  if (!body || typeof body !== "object") return false;
  const b = body as Record<string, unknown>;
  return (
    typeof b.questionId === "number" &&
    typeof b.question === "string" &&
    b.question.length <= MAX_LENGTH &&
    typeof b.sampleAnswer === "string" &&
    b.sampleAnswer.length <= MAX_LENGTH &&
    typeof b.userAnswer === "string" &&
    b.userAnswer.length <= MAX_LENGTH &&
    Array.isArray(b.keywords) &&
    b.keywords.length <= MAX_KEYWORDS &&
    b.keywords.every((k) => typeof k === "string" && k.length <= 80)
  );
}

async function scoreWithGemini(
  apiKey: string,
  body: OralScoreRequestBody
): Promise<{ score: number; matchedKeywords: string[]; feedback: string } | null> {
  const prompt = [
    "Tu es un examinateur de préfecture qui évalue une réponse à l'entretien de naturalisation française.",
    `Question posée : ${body.question}`,
    `Réponse de référence : ${body.sampleAnswer}`,
    `Mots-clés attendus : ${body.keywords.join(", ")}`,
    `Réponse du candidat : ${body.userAnswer}`,
    "Réponds UNIQUEMENT avec un objet JSON strict de cette forme, sans texte autour :",
    '{"score": <entier 0-100>, "matchedKeywords": [<mots-clés effectivement couverts>], "feedback": "<conseil court et bienveillant en français>"}'
  ].join("\n");

  const controller = new AbortController();
  const timeout = setTimeout(() => controller.abort(), 15000);

  try {
    const response = await fetch(
      `https://generativelanguage.googleapis.com/v1beta/models/${GEMINI_MODEL}:generateContent?key=${apiKey}`,
      {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
          contents: [{ parts: [{ text: prompt }] }],
          generationConfig: { temperature: 0.3, maxOutputTokens: 300 }
        }),
        signal: controller.signal
      }
    );
    if (!response.ok) return null;

    const data = (await response.json()) as {
      candidates?: { content?: { parts?: { text?: string }[] } }[];
    };
    const text = data.candidates?.[0]?.content?.parts?.[0]?.text;
    if (!text) return null;

    const jsonMatch = text.match(/\{[\s\S]*\}/);
    if (!jsonMatch) return null;
    const parsed = JSON.parse(jsonMatch[0]) as {
      score?: unknown;
      matchedKeywords?: unknown;
      feedback?: unknown;
    };

    if (
      typeof parsed.score !== "number" ||
      !Array.isArray(parsed.matchedKeywords) ||
      typeof parsed.feedback !== "string"
    ) {
      return null;
    }

    return {
      score: Math.max(0, Math.min(100, Math.round(parsed.score))),
      matchedKeywords: parsed.matchedKeywords.filter((k): k is string => typeof k === "string"),
      feedback: parsed.feedback.slice(0, 500)
    };
  } catch {
    return null;
  } finally {
    clearTimeout(timeout);
  }
}

export default async function handler(req: VercelRequest, res: VercelResponse): Promise<void> {
  if (req.method !== "POST") {
    res.status(405).json({ error: "Method not allowed" });
    return;
  }

  if (!isValidBody(req.body)) {
    res.status(400).json({ error: "Invalid request body" });
    return;
  }

  const apiKey = process.env.GEMINI_API_KEY;
  const geminiResult = apiKey ? await scoreWithGemini(apiKey, req.body) : null;
  const result = geminiResult ?? scoreOralAnswerLocally(req.body.userAnswer, req.body.keywords);

  res.setHeader("Cache-Control", "no-store");
  res.status(200).json(result);
}
