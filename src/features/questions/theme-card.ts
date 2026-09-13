import { CIVIC_CHAPTERS, type CivicChapterKey } from "../../data/enums";
import { el } from "../../lib/dom";
import { chapterVisual, createThemeArt } from "../../lib/theme-visuals";

export type ThemeStats = {
  total: number;
  mastered: number;
};

export function masteryPercent(stats: ThemeStats): number {
  if (stats.total <= 0) return 0;
  return Math.round((stats.mastered / stats.total) * 100);
}

/** Vignette de thème : illustration générative animée + progression. */
export function themeCard(
  chapter: CivicChapterKey,
  stats: ThemeStats,
  options: { wide?: boolean; revealIndex?: number } = {}
): HTMLElement {
  const meta = CIVIC_CHAPTERS[chapter];
  const percent = masteryPercent(stats);
  const meterFill = el("span", {});

  const card = el(
    "a",
    {
      href: `#/questions?theme=${chapter}`,
      className: `theme-card${options.wide ? " wide" : ""}`,
      "data-tilt": "",
      "data-reveal": "scale",
      "data-reveal-delay": options.revealIndex !== undefined ? String(options.revealIndex * 80) : undefined
    },
    [
      (() => {
        const art = createThemeArt(chapter, chapterVisual(chapter), options.wide ? 6 : 5);
        art.setAttribute("data-parallax", "");
        return art;
      })(),
      el("span", { className: "theme-index" }, [`0${meta.chapterNumber}`]),
      el("h3", {}, [options.wide ? meta.title : meta.shortTitle]),
      el("small", {}, [`${stats.mastered} / ${stats.total} maîtrisées · ${percent}%`]),
      el("div", { className: "theme-meter" }, [meterFill])
    ]
  );

  if (typeof requestAnimationFrame === "function") {
    requestAnimationFrame(() => {
      meterFill.style.width = `${percent}%`;
    });
  } else {
    meterFill.style.width = `${percent}%`;
  }

  return card;
}
