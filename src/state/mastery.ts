/** Calculs de progression, purs et testables. */
import type { CivicQuestion } from "../data/types";
import type { CivicChapterKey } from "../data/enums";

export type MasteryStats = {
  total: number;
  mastered: number;
  percent: number;
};

export function computeMastery(questions: readonly CivicQuestion[], masteredIds: readonly number[]): MasteryStats {
  const mastered = questions.filter((question) => masteredIds.includes(question.id)).length;
  const total = questions.length;
  return { total, mastered, percent: total > 0 ? Math.round((mastered / total) * 100) : 0 };
}

export function questionsOfChapter(
  questions: readonly CivicQuestion[],
  chapter: CivicChapterKey
): CivicQuestion[] {
  return questions.filter((question) => question.chapter === chapter);
}

export function chapterMastery(
  questions: readonly CivicQuestion[],
  masteredIds: readonly number[],
  chapter: CivicChapterKey
): MasteryStats {
  return computeMastery(questionsOfChapter(questions, chapter), masteredIds);
}

/** Prochaine question non maîtrisée, pour la reprise de session. */
export function nextUnmastered(
  questions: readonly CivicQuestion[],
  masteredIds: readonly number[]
): CivicQuestion | null {
  return questions.find((question) => !masteredIds.includes(question.id)) ?? null;
}
