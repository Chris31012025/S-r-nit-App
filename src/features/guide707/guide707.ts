import { guide707Questions } from "../../data";
import { CIVIC_CHAPTERS, type CivicChapterKey } from "../../data/enums";
import { el } from "../../lib/dom";
import { renderShell } from "../../lib/shell";
import { loadProgress, saveProgress, toggleMastered } from "../../state/progress";

function renderQuestionDetail(content: HTMLElement, questionId: number): void {
  const index = guide707Questions.findIndex((q) => q.id === questionId);
  const question = guide707Questions[index];
  if (!question) {
    content.append(el("div", { className: "empty-state" }, ["Question introuvable."]));
    return;
  }

  const progress = loadProgress();
  const isMastered = progress.masteredQuestionIds.includes(question.id);
  const prev = guide707Questions[index - 1];
  const next = guide707Questions[index + 1];

  content.append(
    el("a", { href: "#/guide707", className: "button-secondary" }, ["← Retour à la liste"]),
    el("div", { className: "card" }, [
      el("div", { className: "meta-row" }, [
        el("span", { className: "tag" }, [CIVIC_CHAPTERS[question.chapter].shortTitle]),
        question.isCrucial ? el("span", { className: "tag crucial" }, ["Fréquente"]) : null
      ]),
      el("h2", {}, [question.question]),
      el("p", {}, [question.answer]),
      question.dateTip ? el("p", { className: "tag" }, [`💡 ${question.dateTip}`]) : null,
      el(
        "div",
        {},
        question.keywords.map((keyword) => el("span", { className: "keyword-chip" }, [keyword]))
      ),
      el(
        "button",
        {
          className: isMastered ? "button-secondary" : "button-primary",
          onclick: () => {
            saveProgress(toggleMastered(loadProgress(), question.id));
            content.replaceChildren();
            renderQuestionDetail(content, questionId);
          }
        },
        [isMastered ? "✓ Maîtrisée" : "Marquer comme maîtrisée"]
      )
    ]),
    el("div", { className: "meta-row" }, [
      prev ? el("a", { href: `#/guide707?q=${prev.id}`, className: "button-secondary" }, ["← Précédente"]) : el("span", {}, []),
      next ? el("a", { href: `#/guide707?q=${next.id}`, className: "button-secondary" }, ["Suivante →"]) : el("span", {}, [])
    ])
  );
}

function renderList(content: HTMLElement): void {
  const progress = loadProgress();
  const chapterKeys = Object.keys(CIVIC_CHAPTERS) as CivicChapterKey[];

  for (const chapterKey of chapterKeys) {
    const questions = guide707Questions.filter((q) => q.chapter === chapterKey);
    if (questions.length === 0) continue;
    const masteredCount = questions.filter((q) => progress.masteredQuestionIds.includes(q.id)).length;

    content.append(
      el("div", { className: "card" }, [
        el("div", { className: "meta-row" }, [
          el("h2", {}, [CIVIC_CHAPTERS[chapterKey].title]),
          el("span", { className: "tag" }, [`${masteredCount}/${questions.length}`])
        ]),
        el("p", {}, [CIVIC_CHAPTERS[chapterKey].description]),
        el(
          "div",
          { style: "display:flex; flex-direction:column; gap:8px; margin-top:12px" },
          questions.map((q) =>
            el("a", { href: `#/guide707?q=${q.id}`, className: "list-item" }, [
              el("div", { className: "meta-row" }, [
                el("strong", {}, [q.numberText]),
                progress.masteredQuestionIds.includes(q.id) ? el("span", { className: "tag" }, ["✓"]) : null
              ]),
              el("span", {}, [q.question])
            ])
          )
        )
      ])
    );
  }
}

export function renderGuide707(root: HTMLElement, params: URLSearchParams): void {
  const questionIdParam = params.get("q");
  const questionId = questionIdParam ? Number.parseInt(questionIdParam, 10) : null;

  const content = renderShell(
    root,
    "guide707",
    "Guide 707",
    "Les questions clés posées en préfecture, avec réponses types"
  );

  if (questionId !== null && Number.isFinite(questionId)) {
    renderQuestionDetail(content, questionId);
  } else {
    renderList(content);
  }
}
