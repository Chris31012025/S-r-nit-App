import { civicQuestions } from "../../data";
import { CIVIC_CHAPTERS } from "../../data/enums";
import type { CivicQuestion } from "../../data/types";
import { el } from "../../lib/dom";
import { loadProgress, saveProgress, toggleMastered } from "../../state/progress";
import { questionsOfChapter } from "../../state/mastery";

/** Ajuste la hauteur de la carte retournable sur sa face la plus haute. */
function syncFlipHeight(inner: HTMLElement, faces: HTMLElement[]): void {
  const apply = (): void => {
    const tallest = faces.reduce((max, face) => Math.max(max, face.scrollHeight), 0);
    if (tallest > 0) inner.style.minHeight = `${tallest + 8}px`;
  };
  if (typeof requestAnimationFrame === "function") requestAnimationFrame(apply);
  else apply();
}

function navLink(question: CivicQuestion | undefined, label: string): HTMLElement {
  if (!question) return el("span", { style: "flex:1" });
  return el("a", { href: `#/questions?q=${question.id}`, className: "button-secondary" }, [label]);
}

export function renderQuestionDetail(content: HTMLElement, questionId: number): void {
  const question = civicQuestions.find((item) => item.id === questionId);
  if (!question) {
    content.append(el("div", { className: "empty-state" }, ["Question introuvable."]));
    return;
  }

  const chapter = CIVIC_CHAPTERS[question.chapter];
  const siblings = questionsOfChapter(civicQuestions, question.chapter);
  const position = siblings.findIndex((item) => item.id === question.id);

  const mastered = loadProgress().masteredQuestionIds.includes(question.id);

  const masterButton = el(
    "button",
    { className: mastered ? "button-secondary" : "button-primary" },
    [mastered ? "✓ Maîtrisée" : "Marquer comme maîtrisée"]
  );

  masterButton.addEventListener("click", (event) => {
    event.stopPropagation();
    const updated = saveProgress(toggleMastered(loadProgress(), question.id));
    const isMastered = updated.masteredQuestionIds.includes(question.id);
    masterButton.textContent = isMastered ? "✓ Maîtrisée" : "Marquer comme maîtrisée";
    masterButton.className = isMastered ? "button-secondary" : "button-primary";
  });

  const front = el("div", { className: "flip-face front" }, [
    el("div", { className: "meta-row" }, [
      el("span", { className: "tag" }, [chapter.shortTitle]),
      question.isCrucial ? el("span", { className: "tag crucial" }, ["Fréquente"]) : null
    ]),
    el("h2", { style: "font-size:1.25rem;line-height:1.3" }, [question.question]),
    el(
      "div",
      {},
      question.keywords.map((keyword) => el("span", { className: "keyword-chip" }, [keyword]))
    ),
    el("p", { className: "flip-hint" }, ["Touchez la carte pour révéler la réponse"])
  ]);

  const back = el("div", { className: "flip-face back" }, [
    el("div", { className: "meta-row" }, [
      el("span", { className: "tag good" }, ["Réponse"]),
      el("span", { className: "tag" }, [question.numberText])
    ]),
    el("p", { style: "color:var(--text);font-size:0.95rem" }, [question.answer]),
    question.dateTip ? el("p", {}, [`💡 ${question.dateTip}`]) : null,
    masterButton,
    el("p", { className: "flip-hint" }, ["Touchez à nouveau pour revoir la question"])
  ]);

  const inner = el("div", { className: "flip-inner" }, [front, back]);
  const flip = el("div", { className: "card flip", "data-reveal": "", role: "button", tabindex: "0" }, [inner]);

  const toggle = (): void => {
    flip.classList.toggle("flipped");
  };
  flip.addEventListener("click", toggle);
  flip.addEventListener("keydown", (event) => {
    const key = (event as KeyboardEvent).key;
    if (key !== "Enter" && key !== " ") return;
    event.preventDefault();
    toggle();
  });

  syncFlipHeight(inner, [front, back]);

  content.append(
    el("a", { href: `#/questions?theme=${question.chapter}`, className: "button-secondary back-link" }, [
      `← ${chapter.shortTitle}`
    ]),
    flip,
    el("div", { className: "button-row", "data-reveal": "" }, [
      navLink(siblings[position - 1], "← Précédente"),
      navLink(siblings[position + 1], "Suivante →")
    ])
  );
}
