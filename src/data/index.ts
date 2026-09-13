import civicQuestionsRaw from "./civic-questions.json";
import historicDatesRaw from "./historic-dates.json";
import livretChaptersRaw from "./livret-chapters.json";
import pillarsRaw from "./pillars.json";
import oralQuestionsRaw from "./oral-questions.json";
import type { CivicPillar, CivicQuestion, HistoricDateMnemonic, LivretChapter, OralQuestion } from "./types";

export const civicQuestions = civicQuestionsRaw as unknown as CivicQuestion[];
export const historicDates = historicDatesRaw as unknown as HistoricDateMnemonic[];
export const livretChapters = livretChaptersRaw as unknown as LivretChapter[];
export const civicPillars = pillarsRaw as unknown as CivicPillar[];
export const oralQuestions = oralQuestionsRaw as unknown as OralQuestion[];

export * from "./types";
export * from "./enums";
