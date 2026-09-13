import { civicQuestions } from "../../data";
import { CIVIC_CHAPTERS, type CivicChapterKey } from "../../data/enums";
import type { CivicQuestion } from "../../data/types";
import { el } from "../../lib/dom";
import { revealOnScroll } from "../../lib/motion";
import { createRing } from "../../lib/ring";
import { chapterVisual, createThemeArt } from "../../lib/theme-visuals";
import { loadProgress } from "../../state/progress";
import { computeMastery, questionsOfChapter } from "../../state/mastery";

type FilterKey = "all" | "todo" | "crucial";

const FILTERS: { key: FilterKey; label: string }[] = [
  { key: "all", label: "Toutes" },
  { key: "todo", label: "À réviser" },
  { key: "crucial", label: "Fréquentes" }
];

export function filterQuestions(
  questions: readonly CivicQuestion[],
  masteredIds: readonly number[],
  filter: FilterKey
): CivicQuestion[] {
  if (filter === "todo") return questions.filter((question) => !masteredIds.includes(question.id));
  if (filter === "crucial") return questions.filter((question) => question.isCrucial);
  return [...questions];
}

function questionRow(question: CivicQuestion, isMastered: boolean, index: number): HTMLElement {
  return el(
    "a",
    {
      href: `#/questions?q=${question.id}`,
      className: `list-item${isMastered ? " done" : ""}`,
      "data-reveal": "left",
      "data-reveal-delay": String(Math.min(400, index * 45))
    },
    [
      el("span", { className: "index" }, [String(index + 1).padStart(2, "0")]),
      el("span", { className: "body" }, [question.question]),
      el("span", { className: "check" }, ["✓"])
    ]
  );
}

export function renderThemeDetail(content: HTMLElement, chapter: CivicChapterKey): void {
  const meta = CIVIC_CHAPTERS[chapter];
  const questions = questionsOfChapter(civicQuestions, chapter);
  const masteredIds = loadProgress().masteredQuestionIds;
  const mastery = computeMastery(questions, masteredIds);

  const art = createThemeArt(`${chapter}-banner`, chapterVisual(chapter), 6);
  art.setAttribute("data-parallax", "");

  const list = el("div", { className: "stack" });
  let disposeReveal: () => void = () => undefined;

  const fillList = (filter: FilterKey): void => {
    disposeReveal();
    const visible = filterQuestions(questions, masteredIds, filter);
    list.replaceChildren(
      ...(visible.length === 0
        ? [el("div", { className: "empty-state" }, ["Rien à afficher pour ce filtre."])]
        : visible.map((question, index) => questionRow(question, masteredIds.includes(question.id), index)))
    );
    disposeReveal = revealOnScroll(list);
  };

  const filterBar = el(
    "div",
    { className: "button-row", "data-reveal": "" },
    FILTERS.map((filter, index) => {
      const button = el("button", { className: index === 0 ? "button-primary" : "button-secondary" }, [
        filter.label
      ]);
      button.addEventListener("click", () => {
        for (const sibling of Array.from(filterBar.children)) {
          sibling.className = "button-secondary";
        }
        button.className = "button-primary";
        fillList(filter.key);
      });
      return button;
    })
  );

  content.append(
    el("a", { href: "#/questions", className: "button-secondary back-link" }, ["← Tous les thèmes"]),
    el("section", { className: "theme-banner" }, [
      art,
      el("div", {}, [
        el("span", { className: "tag" }, [`Thème 0${meta.chapterNumber}`]),
        el("h2", { style: "margin:10px 0 4px;font-size:1.4rem;letter-spacing:-0.03em" }, [meta.title]),
        el("p", { style: "margin:0;color:rgba(244,246,253,0.72);font-size:0.85rem" }, [meta.description])
      ])
    ]),
    el("div", { className: "card", "data-reveal": "" }, [
      el("div", { className: "meta-row" }, [
        el("div", {}, [
          el("h2", {}, ["Progression du thème"]),
          el("p", {}, [`${mastery.mastered} / ${mastery.total} questions maîtrisées`])
        ]),
        createRing(mastery.percent, { caption: meta.shortTitle, size: 88 })
      ])
    ]),
    filterBar,
    list
  );

  fillList("all");
}
