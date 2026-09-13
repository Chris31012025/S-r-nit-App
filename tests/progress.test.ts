import { describe, expect, it } from "vitest";
import {
  averageOralScore,
  currentStreak,
  emptyProgress,
  recordOralAttempt,
  recordVisitToday,
  toggleMastered
} from "../src/state/progress";

describe("progress store", () => {
  it("toggles mastered questions immutably", () => {
    const initial = emptyProgress;
    const withOne = toggleMastered(initial, 5);
    expect(withOne.masteredQuestionIds).toEqual([5]);
    expect(initial.masteredQuestionIds).toEqual([]);

    const withoutOne = toggleMastered(withOne, 5);
    expect(withoutOne.masteredQuestionIds).toEqual([]);
  });

  it("records a visit only once per day", () => {
    const withVisit = recordVisitToday(emptyProgress, "2026-09-13");
    const again = recordVisitToday(withVisit, "2026-09-13");
    expect(again.visitedDays).toEqual(["2026-09-13"]);
  });

  it("computes a streak from consecutive visited days", () => {
    const today = new Date();
    const yesterday = new Date(today);
    yesterday.setDate(today.getDate() - 1);
    const twoDaysAgo = new Date(today);
    twoDaysAgo.setDate(today.getDate() - 2);

    const key = (d: Date) => d.toISOString().slice(0, 10);
    const streak = currentStreak([key(twoDaysAgo), key(yesterday), key(today)]);
    expect(streak).toBe(3);
  });

  it("returns zero streak when today was not visited", () => {
    const yesterday = new Date();
    yesterday.setDate(yesterday.getDate() - 1);
    const streak = currentStreak([yesterday.toISOString().slice(0, 10)]);
    expect(streak).toBe(0);
  });

  it("averages oral attempt scores", () => {
    let state = emptyProgress;
    state = recordOralAttempt(state, { questionId: 1, score: 80, timestamp: "t1" });
    state = recordOralAttempt(state, { questionId: 2, score: 60, timestamp: "t2" });
    expect(averageOralScore(state.oralAttempts)).toBe(70);
  });

  it("returns zero average with no attempts", () => {
    expect(averageOralScore([])).toBe(0);
  });
});
