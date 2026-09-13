import { guide707Questions, oralQuestions } from "../../data";
import { CIVIC_CHAPTERS, type CivicChapterKey } from "../../data/enums";
import { el } from "../../lib/dom";
import { renderShell } from "../../lib/shell";
import { averageOralScore, currentStreak, loadProgress } from "../../state/progress";

export function renderBilan(root: HTMLElement): void {
  const progress = loadProgress();
  const streak = currentStreak(progress.visitedDays);
  const masteredCount = progress.masteredQuestionIds.length;
  const totalQuestions = guide707Questions.length;
  const masteryPct = totalQuestions > 0 ? Math.round((masteredCount / totalQuestions) * 100) : 0;
  const avgOralScore = averageOralScore(progress.oralAttempts);

  const content = renderShell(root, "bilan", "Bilan de progression", "Votre préparation en un coup d'œil");

  const chapterKeys = Object.keys(CIVIC_CHAPTERS) as CivicChapterKey[];

  content.append(
    el("div", { className: "card" }, [
      el("h2", {}, ["Vue d'ensemble"]),
      el("div", { className: "meta-row" }, [el("span", {}, ["Série de révision"]), el("strong", {}, [`${streak} jour(s)`])]),
      el("div", { className: "meta-row" }, [
        el("span", {}, ["Guide 707 maîtrisé"]),
        el("strong", {}, [`${masteredCount}/${totalQuestions} (${masteryPct}%)`])
      ]),
      el("div", { className: "progress-bar" }, [el("span", { style: `width:${masteryPct}%` })]),
      el("div", { className: "meta-row", style: "margin-top:10px" }, [
        el("span", {}, ["Score moyen à l'oral"]),
        el("strong", {}, [progress.oralAttempts.length > 0 ? `${avgOralScore}/100` : "Pas encore d'essai"])
      ])
    ]),
    el(
      "div",
      { className: "card" },
      [
        el("h2", {}, ["Par chapitre"]),
        ...chapterKeys.map((key) => {
          const questions = guide707Questions.filter((q) => q.chapter === key);
          const mastered = questions.filter((q) => progress.masteredQuestionIds.includes(q.id)).length;
          const pct = questions.length > 0 ? Math.round((mastered / questions.length) * 100) : 0;
          return el("div", { style: "margin-bottom:10px" }, [
            el("div", { className: "meta-row" }, [
              el("span", {}, [CIVIC_CHAPTERS[key].shortTitle]),
              el("span", {}, [`${mastered}/${questions.length}`])
            ]),
            el("div", { className: "progress-bar" }, [el("span", { style: `width:${pct}%` })])
          ]);
        })
      ]
    ),
    el("div", { className: "card" }, [
      el("h2", {}, ["Historique oral récent"]),
      progress.oralAttempts.length === 0
        ? el("p", {}, ["Aucun entraînement oral pour le moment."])
        : el(
            "div",
            {},
            [...progress.oralAttempts]
              .slice(-5)
              .reverse()
              .map((attempt) => {
                const question = oralQuestions.find((q) => q.id === attempt.questionId);
                return el("div", { className: "meta-row" }, [
                  el("span", {}, [question ? question.questionText : `Question #${attempt.questionId}`]),
                  el("span", { className: "tag" }, [`${attempt.score}/100`])
                ]);
              })
          )
    ])
  );
}
