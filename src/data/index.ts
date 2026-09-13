import guide707Raw from "./guide707.json";
import historicDatesRaw from "./historic-dates.json";
import livretChaptersRaw from "./livret-chapters.json";
import pillarsRaw from "./pillars.json";
import oralQuestionsRaw from "./oral-questions.json";
import type { CivicPillar, GuideQuestion, HistoricDateMnemonic, LivretChapter, OralQuestion } from "./types";

export const guide707Questions = guide707Raw as unknown as GuideQuestion[];
export const historicDates = historicDatesRaw as unknown as HistoricDateMnemonic[];
export const livretChapters = livretChaptersRaw as unknown as LivretChapter[];
export const civicPillars = pillarsRaw as unknown as CivicPillar[];
export const oralQuestions = oralQuestionsRaw as unknown as OralQuestion[];

export * from "./types";
export * from "./enums";
