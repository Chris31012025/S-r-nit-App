import { civicQuestions } from "../../data";
import { CIVIC_CHAPTERS, type CivicChapterKey } from "../../data/enums";
import { el } from "../../lib/dom";
import { renderShell } from "../../lib/shell";
import { loadProgress } from "../../state/progress";
import { chapterMastery, computeMastery } from "../../state/mastery";
import { renderQuestionDetail } from "./question-detail";
import { renderThemeDetail } from "./theme-detail";
import { themeCard } from "./theme-card";

const CHAPTER_KEYS = Object.keys(CIVIC_CHAPTERS) as CivicChapterKey[];

function isChapterKey(value: string | null): value is CivicChapterKey {
  return value !== null && CHAPTER_KEYS.includes(value as CivicChapterKey);
}

function renderThemeGrid(content: HTMLElement): void {
  const masteredIds = loadProgress().masteredQuestionIds;
  const overall = computeMastery(civicQuestions, masteredIds);

  content.append(
    el("div", { className: "card", "data-reveal": "" }, [
      el("div", { className: "meta-row" }, [
        el("h2", {}, [`${CHAPTER_KEYS.length} thèmes`]),
        el("span", { className: "tag" }, [`${overall.percent}% global`])
      ]),
      el("p", {}, [
        "Chaque thème regroupe les questions d'un même domaine. Choisissez-en un pour réviser à votre rythme."
      ])
    ]),
    el(
      "div",
      { className: "theme-grid" },
      CHAPTER_KEYS.map((chapter, index) =>
        themeCard(chapter, chapterMastery(civicQuestions, masteredIds, chapter), {
          wide: index % 3 === 0,
          revealIndex: index
        })
      )
    )
  );
}

export function renderQuestions(root: HTMLElement, params: URLSearchParams): void {
  const themeParam = params.get("theme");
  const questionParam = params.get("q");
  const questionId = questionParam ? Number.parseInt(questionParam, 10) : null;

  if (questionId !== null && Number.isFinite(questionId)) {
    const question = civicQuestions.find((item) => item.id === questionId);
    const content = renderShell(root, "questions", {
      eyebrow: question ? CIVIC_CHAPTERS[question.chapter].shortTitle : "Question",
      title: "Fiche de révision"
    });
    renderQuestionDetail(content, questionId);
    return;
  }

  if (isChapterKey(themeParam)) {
    const content = renderShell(root, "questions", {
      eyebrow: `Thème 0${CIVIC_CHAPTERS[themeParam].chapterNumber}`,
      title: CIVIC_CHAPTERS[themeParam].shortTitle
    });
    renderThemeDetail(content, themeParam);
    return;
  }

  const content = renderShell(root, "questions", {
    eyebrow: "Révisions",
    title: "Thèmes",
    subtitle: "Les sujets essentiels de l'entretien, organisés par domaine"
  });
  renderThemeGrid(content);
}
