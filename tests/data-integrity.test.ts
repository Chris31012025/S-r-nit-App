import { describe, expect, it } from "vitest";
import { civicPillars, civicQuestions, historicDates, livretChapters, oralQuestions } from "../src/data";
import { CIVIC_CHAPTERS, HISTORIC_PERIODS, LIVRET_CATEGORIES } from "../src/data/enums";

describe("bundled content integrity", () => {
  it("has a non-empty, unique-id civic question bank referencing known chapters", () => {
    expect(civicQuestions.length).toBeGreaterThan(0);
    const ids = new Set(civicQuestions.map((q) => q.id));
    expect(ids.size).toBe(civicQuestions.length);
    for (const question of civicQuestions) {
      expect(CIVIC_CHAPTERS[question.chapter]).toBeDefined();
      expect(question.question.length).toBeGreaterThan(0);
      expect(question.answer.length).toBeGreaterThan(0);
    }
  });

  it("has historic date mnemonics referencing known periods", () => {
    expect(historicDates.length).toBeGreaterThan(0);
    for (const date of historicDates) {
      expect(HISTORIC_PERIODS[date.period]).toBeDefined();
    }
  });

  it("has Livret chapters with at least one article each, referencing known categories", () => {
    expect(livretChapters.length).toBeGreaterThan(0);
    for (const chapter of livretChapters) {
      expect(LIVRET_CATEGORIES[chapter.category]).toBeDefined();
      expect(chapter.articles.length).toBeGreaterThan(0);
    }
  });

  it("has civic pillars and oral questions with usable content", () => {
    expect(civicPillars.length).toBeGreaterThan(0);
    expect(oralQuestions.length).toBeGreaterThan(0);
    for (const oral of oralQuestions) {
      expect(oral.questionText.length).toBeGreaterThan(0);
      expect(oral.keywords.length).toBeGreaterThan(0);
    }
  });
});
