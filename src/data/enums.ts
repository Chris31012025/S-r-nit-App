export type CivicChapterKey =
  | "PERSONNEL"
  | "ACTUALITE"
  | "REPUBLIQUE"
  | "HISTOIRE"
  | "CULTURE"
  | "GEOGRAPHIE"
  | "EUROPE";

export const CIVIC_CHAPTERS: Record<
  CivicChapterKey,
  { chapterNumber: number; title: string; shortTitle: string; questionRange: string; description: string }
> = {
  PERSONNEL: {
    chapterNumber: 1,
    title: "Vie personnelle, familiale & pro",
    shortTitle: "Personnel",
    questionRange: "Q1 à Q113",
    description: "Motivations, intégration, vie quotidienne, respect des règles républicaines"
  },
  ACTUALITE: {
    chapterNumber: 2,
    title: "Actualité & Société",
    shortTitle: "Actualité",
    questionRange: "Q114 à Q205",
    description: "Événements récents, laïcité, débats, institutions en action, JO"
  },
  REPUBLIQUE: {
    chapterNumber: 3,
    title: "République française & Institutions",
    shortTitle: "République",
    questionRange: "Q206 à Q392",
    description: "Valeurs, devise, pouvoirs exécutif, législatif, judiciaire, symboles"
  },
  HISTOIRE: {
    chapterNumber: 4,
    title: "Histoire de France",
    shortTitle: "Histoire",
    questionRange: "Q393 à Q553",
    description: "Révolution de 1789, les 5 Républiques, guerres mondiales, grandes lois"
  },
  CULTURE: {
    chapterNumber: 5,
    title: "Culture & Patrimoine français",
    shortTitle: "Culture",
    questionRange: "Q554 à Q616",
    description: "Monuments, littérature, cinéma, gastronomie, personnalités au Panthéon"
  },
  GEOGRAPHIE: {
    chapterNumber: 6,
    title: "Géographie de la France",
    shortTitle: "Géographie",
    questionRange: "Q617 à Q683",
    description: "Régions, départements, DROM-COM, fleuves, massifs montagneux"
  },
  EUROPE: {
    chapterNumber: 7,
    title: "L'Europe & l'Union Européenne",
    shortTitle: "Europe",
    questionRange: "Q684 à Q723",
    description: "Traités fondateurs, institutions de l'UE, espace Schengen, zone euro"
  }
};

export type LivretCategoryKey =
  | "PRINCIPES"
  | "SYMBOLES"
  | "DROITS_DEVOIRS"
  | "HISTOIRE"
  | "INSTITUTIONS"
  | "EUROPE_MONDE";

export const LIVRET_CATEGORIES: Record<LivretCategoryKey, string> = {
  PRINCIPES: "Principes & Valeurs",
  SYMBOLES: "Symboles Nationaux",
  DROITS_DEVOIRS: "Droits & Devoirs",
  HISTOIRE: "Histoire & Repères",
  INSTITUTIONS: "Institutions & Territoires",
  EUROPE_MONDE: "Europe & Monde"
};

export type HistoricPeriodKey =
  | "ANCIEN_REGIME_REVOLUTION"
  | "XIX_SIECLE"
  | "GUERRES_MONDIALES"
  | "VE_REPUBLIQUE"
  | "XXIE_SIECLE";

export const HISTORIC_PERIODS: Record<HistoricPeriodKey, string> = {
  ANCIEN_REGIME_REVOLUTION: "Révolution & Fondations (1789-1804)",
  XIX_SIECLE: "Le XIXe Siècle & Républiques (1848-1905)",
  GUERRES_MONDIALES: "Guerres Mondiales & Libération (1914-1945)",
  VE_REPUBLIQUE: "Ve République & Évolutions (1958-1999)",
  XXIE_SIECLE: "Époque Contemporaine (2000-2026)"
};
