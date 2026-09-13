import { readJson, writeJson } from "../lib/storage";

const STORAGE_KEY = "serenite.progress.v1";

export type OralAttempt = {
  questionId: number;
  score: number;
  timestamp: string;
};

export type ProgressState = {
  masteredQuestionIds: number[];
  visitedDays: string[];
  oralAttempts: OralAttempt[];
};

export const emptyProgress: ProgressState = {
  masteredQuestionIds: [],
  visitedDays: [],
  oralAttempts: []
};

export function loadProgress(): ProgressState {
  return readJson(STORAGE_KEY, emptyProgress);
}

export function saveProgress(state: ProgressState): ProgressState {
  writeJson(STORAGE_KEY, state);
  return state;
}

export function toggleMastered(state: ProgressState, questionId: number): ProgressState {
  const isMastered = state.masteredQuestionIds.includes(questionId);
  const masteredQuestionIds = isMastered
    ? state.masteredQuestionIds.filter((id) => id !== questionId)
    : [...state.masteredQuestionIds, questionId];
  return { ...state, masteredQuestionIds };
}

export function recordVisitToday(state: ProgressState, today: string = todayKey()): ProgressState {
  if (state.visitedDays.includes(today)) return state;
  return { ...state, visitedDays: [...state.visitedDays, today].sort() };
}

export function recordOralAttempt(state: ProgressState, attempt: OralAttempt): ProgressState {
  return { ...state, oralAttempts: [...state.oralAttempts, attempt] };
}

export function todayKey(date: Date = new Date()): string {
  return date.toISOString().slice(0, 10);
}

export function currentStreak(visitedDays: string[]): number {
  if (visitedDays.length === 0) return 0;
  const daySet = new Set(visitedDays);
  let streak = 0;
  const cursor = new Date();
  for (;;) {
    const key = todayKey(cursor);
    if (!daySet.has(key)) break;
    streak += 1;
    cursor.setDate(cursor.getDate() - 1);
  }
  return streak;
}

export function averageOralScore(attempts: OralAttempt[]): number {
  if (attempts.length === 0) return 0;
  const total = attempts.reduce((sum, attempt) => sum + attempt.score, 0);
  return Math.round(total / attempts.length);
}
