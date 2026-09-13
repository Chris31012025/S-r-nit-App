import { describe, expect, it } from "vitest";
import { civicQuestions } from "../src/data";
import type { CivicQuestion } from "../src/data/types";
import { chapterMastery, computeMastery, nextUnmastered, questionsOfChapter } from "../src/state/mastery";
import { filterQuestions } from "../src/features/questions/theme-detail";
import { masteryPercent } from "../src/features/questions/theme-card";

function question(id: number, overrides: Partial<CivicQuestion> = {}): CivicQuestion {
  return {
    id,
    numberText: `Q${id}`,
    chapter: "REPUBLIQUE",
    question: `Question ${id} ?`,
    answer: `Réponse ${id}`,
    dateTip: null,
    isCrucial: false,
    keywords: ["test"],
    ...overrides
  };
}

const SAMPLE: CivicQuestion[] = [
  question(1),
  question(2, { isCrucial: true }),
  question(3, { chapter: "HISTOIRE" }),
  question(4, { chapter: "HISTOIRE", isCrucial: true })
];

describe("mastery calculations", () => {
  it("computes totals and percentage", () => {
    expect(computeMastery(SAMPLE, [1, 3])).toEqual({ total: 4, mastered: 2, percent: 50 });
    expect(computeMastery([], [1])).toEqual({ total: 0, mastered: 0, percent: 0 });
  });

  it("ignores mastered ids that are not part of the set", () => {
    expect(computeMastery(SAMPLE, [99]).mastered).toBe(0);
  });

  it("scopes questions and mastery to a chapter", () => {
    expect(questionsOfChapter(SAMPLE, "HISTOIRE").map((item) => item.id)).toEqual([3, 4]);
    expect(chapterMastery(SAMPLE, [3], "HISTOIRE")).toEqual({ total: 2, mastered: 1, percent: 50 });
  });

  it("finds the next question left to learn, or null when complete", () => {
    expect(nextUnmastered(SAMPLE, [1])?.id).toBe(2);
    expect(nextUnmastered(SAMPLE, [1, 2, 3, 4])).toBeNull();
  });

  it("mirrors the percentage used by theme cards", () => {
    expect(masteryPercent({ total: 4, mastered: 1 })).toBe(25);
    expect(masteryPercent({ total: 0, mastered: 0 })).toBe(0);
  });
});

describe("theme filters", () => {
  it("returns every question by default without mutating the source", () => {
    const result = filterQuestions(SAMPLE, [], "all");
    expect(result).toHaveLength(4);
    expect(result).not.toBe(SAMPLE);
  });

  it("keeps only unmastered questions for the revision filter", () => {
    expect(filterQuestions(SAMPLE, [1, 2], "todo").map((item) => item.id)).toEqual([3, 4]);
  });

  it("keeps only frequently asked questions", () => {
    expect(filterQuestions(SAMPLE, [], "crucial").map((item) => item.id)).toEqual([2, 4]);
  });
});

describe("bundled questions are usable by every theme view", () => {
  it("splits the real question bank across chapters without loss", () => {
    const chapters = new Set(civicQuestions.map((item) => item.chapter));
    const regrouped = [...chapters].flatMap((chapter) => questionsOfChapter(civicQuestions, chapter));
    expect(regrouped).toHaveLength(civicQuestions.length);
  });
});
