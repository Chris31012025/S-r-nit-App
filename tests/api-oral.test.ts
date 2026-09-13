import { describe, expect, it, vi } from "vitest";
import handler from "../api/oral";

type MockResponse = {
  statusCode: number;
  body: unknown;
  status: (code: number) => MockResponse;
  json: (payload: unknown) => MockResponse;
  setHeader: () => void;
};

function mockResponse(): MockResponse {
  const res = {
    statusCode: 0,
    body: undefined as unknown,
    status(code: number) {
      res.statusCode = code;
      return res;
    },
    json(payload: unknown) {
      res.body = payload;
      return res;
    },
    setHeader() {
      /* no-op */
    }
  };
  return res;
}

describe("POST /api/oral", () => {
  it("rejects non-POST methods", async () => {
    const res = mockResponse();
    await handler({ method: "GET" } as never, res as never);
    expect(res.statusCode).toBe(405);
  });

  it("rejects invalid request bodies", async () => {
    const res = mockResponse();
    await handler({ method: "POST", body: { question: "x" } } as never, res as never);
    expect(res.statusCode).toBe(400);
  });

  it("falls back to local scoring when no Gemini key is configured", async () => {
    vi.stubEnv("GEMINI_API_KEY", "");
    const res = mockResponse();
    await handler(
      {
        method: "POST",
        body: {
          questionId: 1,
          question: "Pourquoi voulez-vous devenir français ?",
          sampleAnswer: "Réponse de référence avec intégration et valeurs.",
          keywords: ["Intégration", "Valeurs"],
          userAnswer: "Je parle d'intégration et de valeurs républicaines."
        }
      } as never,
      res as never
    );
    expect(res.statusCode).toBe(200);
    const body = res.body as { score: number; matchedKeywords: string[] };
    expect(body.score).toBeGreaterThanOrEqual(0);
    expect(Array.isArray(body.matchedKeywords)).toBe(true);
    vi.unstubAllEnvs();
  });
});
