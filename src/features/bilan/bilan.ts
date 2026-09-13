import { civicQuestions, oralQuestions } from "../../data";
import { CIVIC_CHAPTERS, type CivicChapterKey } from "../../data/enums";
import { el } from "../../lib/dom";
import { animateNumber } from "../../lib/motion";
import { createRing } from "../../lib/ring";
import { renderShell } from "../../lib/shell";
import { averageOralScore, currentStreak, loadProgress } from "../../state/progress";
import { chapterMastery, computeMastery } from "../../state/mastery";

const CHAPTER_KEYS = Object.keys(CIVIC_CHAPTERS) as CivicChapterKey[];

function statTile(value: number, label: string, suffix = ""): HTMLElement {
  const valueNode = el("strong", {}, ["0"]);
  animateNumber(valueNode, value, { duration: 1200, suffix });
  return el("div", { className: "stat" }, [valueNode, el("span", {}, [label])]);
}

function chapterBar(chapter: CivicChapterKey, masteredIds: readonly number[], index: number): HTMLElement {
  const stats = chapterMastery(civicQuestions, masteredIds, chapter);
  const fill = el("span", {});

  if (typeof requestAnimationFrame === "function") {
    requestAnimationFrame(() => {
      fill.style.transitionDelay = `${index * 90}ms`;
      fill.style.width = `${stats.percent}%`;
    });
  } else {
    fill.style.width = `${stats.percent}%`;
  }

  return el("div", { style: "margin-bottom:14px" }, [
    el("div", { className: "meta-row", style: "margin-bottom:6px" }, [
      el("span", { style: "font-size:0.85rem" }, [CIVIC_CHAPTERS[chapter].shortTitle]),
      el("span", { style: "font-size:0.78rem;color:var(--text-faint)" }, [
        `${stats.mastered}/${stats.total}`
      ])
    ]),
    el("div", { className: "progress-bar" }, [fill])
  ]);
}

export function renderBilan(root: HTMLElement): void {
  const progress = loadProgress();
  const streak = currentStreak(progress.visitedDays);
  const mastery = computeMastery(civicQuestions, progress.masteredQuestionIds);
  const avgOral = averageOralScore(progress.oralAttempts);

  const content = renderShell(root, "bilan", {
    eyebrow: "Tableau de bord",
    title: "Bilan",
    subtitle: "Votre préparation en un coup d'œil"
  });

  content.append(
    el("div", { className: "card", "data-reveal": "" }, [
      el("div", { className: "meta-row" }, [
        el("div", {}, [
          el("h2", {}, ["Maîtrise globale"]),
          el("p", {}, [`${mastery.mastered} questions sur ${mastery.total}`])
        ]),
        createRing(mastery.percent, { caption: "Global", size: 100 })
      ]),
      el("div", { className: "stat-grid", style: "margin-top:18px" }, [
        statTile(streak, "Jours d'affilée"),
        statTile(progress.oralAttempts.length, "Essais oraux"),
        statTile(avgOral, "Score moyen")
      ])
    ]),

    el("div", { className: "card", "data-reveal": "" }, [
      el("h2", { style: "margin-bottom:14px" }, ["Progression par thème"]),
      ...CHAPTER_KEYS.map((chapter, index) => chapterBar(chapter, progress.masteredQuestionIds, index))
    ]),

    el("div", { className: "card", "data-reveal": "" }, [
      el("h2", {}, ["Historique oral récent"]),
      progress.oralAttempts.length === 0
        ? el("p", {}, ["Aucun entraînement oral pour le moment."])
        : el(
            "div",
            { className: "stack", style: "margin-top:12px" },
            [...progress.oralAttempts]
              .slice(-5)
              .reverse()
              .map((attempt) => {
                const question = oralQuestions.find((item) => item.id === attempt.questionId);
                return el("div", { className: "list-item" }, [
                  el("span", { className: "body" }, [
                    question ? question.questionText : `Question #${attempt.questionId}`,
                    el("small", {}, [new Date(attempt.timestamp).toLocaleDateString("fr-FR")])
                  ]),
                  el("span", { className: attempt.score >= 70 ? "tag good" : "tag" }, [`${attempt.score}/100`])
                ]);
              })
          )
    ])
  );
}
