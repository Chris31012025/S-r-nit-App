export type OralScoreResult = {
  score: number;
  matchedKeywords: string[];
  feedback: string;
};

function normalize(text: string): string {
  return text
    .toLowerCase()
    .normalize("NFD")
    .replace(/[̀-ͯ]/g, "");
}

export function scoreOralAnswerLocally(userAnswer: string, keywords: string[]): OralScoreResult {
  const normalizedAnswer = normalize(userAnswer);
  const matchedKeywords = keywords.filter((keyword) => normalizedAnswer.includes(normalize(keyword)));
  const lengthBonus = Math.min(userAnswer.trim().split(/\s+/).filter(Boolean).length / 30, 1);
  const keywordRatio = keywords.length > 0 ? matchedKeywords.length / keywords.length : 0;
  const score = Math.round((keywordRatio * 0.7 + lengthBonus * 0.3) * 100);

  const feedback =
    matchedKeywords.length === 0
      ? "Essayez d'intégrer davantage de mots-clés attendus dans votre réponse."
      : matchedKeywords.length === keywords.length
        ? "Excellente réponse, tous les points clés attendus sont présents."
        : `Bonne base. Pensez aussi à mentionner : ${keywords.filter((k) => !matchedKeywords.includes(k)).join(", ")}.`;

  return { score, matchedKeywords, feedback };
}
