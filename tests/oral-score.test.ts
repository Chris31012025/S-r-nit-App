import { describe, expect, it } from "vitest";
import { scoreOralAnswerLocally } from "../src/lib/oral-score";

describe("scoreOralAnswerLocally", () => {
  const keywords = ["Intégration", "Valeurs républicaines", "Droit de vote"];

  it("gives a low score with no matched keywords", () => {
    const result = scoreOralAnswerLocally("Je ne sais pas.", keywords);
    expect(result.matchedKeywords).toEqual([]);
    expect(result.score).toBeLessThan(30);
  });

  it("matches keywords case- and accent-insensitively", () => {
    const result = scoreOralAnswerLocally(
      "Je crois profondement en l'INTEGRATION et aux valeurs republicaines.",
      keywords
    );
    expect(result.matchedKeywords).toContain("Intégration");
    expect(result.matchedKeywords).toContain("Valeurs républicaines");
  });

  it("gives full feedback when every keyword is present", () => {
    const longAnswer =
      "L'intégration, les valeurs républicaines et le droit de vote sont au cœur de mon engagement citoyen, que je prends très au sérieux depuis de nombreuses années dans ma vie quotidienne.";
    const result = scoreOralAnswerLocally(longAnswer, keywords);
    expect(result.matchedKeywords.length).toBe(keywords.length);
    expect(result.score).toBeGreaterThan(80);
  });

  it("never returns a score outside 0-100", () => {
    const result = scoreOralAnswerLocally("", keywords);
    expect(result.score).toBeGreaterThanOrEqual(0);
    expect(result.score).toBeLessThanOrEqual(100);
  });
});
