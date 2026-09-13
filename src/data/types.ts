import type { CivicChapterKey, HistoricPeriodKey, LivretCategoryKey } from "./enums";

export type GuideQuestion = {
  id: number;
  numberText: string;
  chapter: CivicChapterKey;
  question: string;
  answer: string;
  dateTip: string | null;
  isCrucial: boolean;
  keywords: string[];
};

export type HistoricDateMnemonic = {
  id: number;
  year: number;
  exactDate: string;
  title: string;
  period: HistoricPeriodKey;
  summary: string;
  mnemonicTip: string;
  officialContext: string;
  isCrucial?: boolean;
  tags: string[];
};

export type LivretArticle = {
  title: string;
  content: string;
  keyPoints: string[];
  officialQuote: string | null;
  legalReference: string | null;
};

export type LivretChapter = {
  id: string;
  number: number;
  title: string;
  subtitle: string;
  category: LivretCategoryKey;
  articles: LivretArticle[];
  relatedDateIds: number[];
};

export type CivicPillar = {
  id: string;
  title: string;
  subtitle: string;
  meta: string;
  tag: string | null;
  progressNote: string | null;
  gradientIndex: number;
  description: string;
  keyFacts: string[];
};

export type OralQuestion = {
  id: number;
  indexText: string;
  questionText: string;
  sampleAnswer: string;
  instructorAdvice: string;
  keywords: string[];
};
