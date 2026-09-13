/** Palettes associées à chaque thème, et fabrique d'illustration animée. */
import type { CivicChapterKey, LivretCategoryKey } from "../data/enums";
import { createArtElement, createArtSpec } from "./theme-art";

export type ThemeVisual = {
  base: readonly [string, string];
  palette: readonly string[];
};

export const THEME_VISUALS: Record<CivicChapterKey, ThemeVisual> = {
  PERSONNEL: { base: ["#2a1522", "#100811"], palette: ["#ff8a6b", "#ff5f7e", "#ffd08a"] },
  ACTUALITE: { base: ["#10221f", "#06100f"], palette: ["#ffc46b", "#4fd1b6", "#ff8f5e"] },
  REPUBLIQUE: { base: ["#0b1533", "#04081a"], palette: ["#3c63ff", "#eef2ff", "#e04a60"] },
  HISTOIRE: { base: ["#241a0e", "#0e0906"], palette: ["#e8b25a", "#d4694a", "#f5e0b0"] },
  CULTURE: { base: ["#1d1030", "#0b0617"], palette: ["#a06bff", "#ff7ad9", "#ffd6f2"] },
  GEOGRAPHIE: { base: ["#0c1f1b", "#040f0d"], palette: ["#3fd39a", "#6bd0ff", "#d8f5c8"] },
  EUROPE: { base: ["#081733", "#030a1a"], palette: ["#4f8bff", "#ffd75e", "#a8c8ff"] }
};

export const NEUTRAL_VISUAL: ThemeVisual = {
  base: ["#0d1428", "#05080f"],
  palette: ["#3c63ff", "#eef2ff", "#d4394f"]
};

let instanceCount = 0;

/** Crée une illustration SVG animée et unique pour une graine donnée. */
export function createThemeArt(seed: string, visual: ThemeVisual, orbCount = 5): SVGSVGElement {
  instanceCount += 1;
  const spec = createArtSpec(seed, visual.palette, visual.base, orbCount);
  return createArtElement(spec, `art-${instanceCount}`);
}

export const LIVRET_VISUALS: Record<LivretCategoryKey, ThemeVisual> = {
  PRINCIPES: { base: ["#0b1533", "#04081a"], palette: ["#3c63ff", "#eef2ff", "#e04a60"] },
  SYMBOLES: { base: ["#1b0f22", "#0a0610"], palette: ["#d4394f", "#eef2ff", "#2b4aca"] },
  DROITS_DEVOIRS: { base: ["#0c1f1b", "#040f0d"], palette: ["#3fd39a", "#6bd0ff", "#d8f5c8"] },
  HISTOIRE: { base: ["#241a0e", "#0e0906"], palette: ["#e8b25a", "#d4694a", "#f5e0b0"] },
  INSTITUTIONS: { base: ["#0d1428", "#05080f"], palette: ["#6d8cff", "#a8c8ff", "#eef2ff"] },
  EUROPE_MONDE: { base: ["#081733", "#030a1a"], palette: ["#4f8bff", "#ffd75e", "#a8c8ff"] }
};

export function chapterVisual(chapter: CivicChapterKey): ThemeVisual {
  return THEME_VISUALS[chapter] ?? NEUTRAL_VISUAL;
}

export function livretVisual(category: LivretCategoryKey): ThemeVisual {
  return LIVRET_VISUALS[category] ?? NEUTRAL_VISUAL;
}
