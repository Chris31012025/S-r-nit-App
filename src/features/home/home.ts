import { civicPillars, civicQuestions } from "../../data";
import { CIVIC_CHAPTERS, type CivicChapterKey } from "../../data/enums";
import { el } from "../../lib/dom";
import { kineticTitle } from "../../lib/kinetic";
import { animateNumber } from "../../lib/motion";
import { createRing } from "../../lib/ring";
import { renderShell } from "../../lib/shell";
import { averageOralScore, currentStreak, loadProgress, recordVisitToday, saveProgress } from "../../state/progress";
import { chapterMastery, computeMastery, nextUnmastered } from "../../state/mastery";
import { themeCard } from "../questions/theme-card";

const FEATURED_THEMES: CivicChapterKey[] = ["REPUBLIQUE", "HISTOIRE", "EUROPE"];

function statTile(value: number, label: string, suffix = ""): HTMLElement {
  const valueNode = el("strong", {}, ["0"]);
  animateNumber(valueNode, value, { duration: 1100, suffix });
  return el("div", { className: "stat" }, [valueNode, el("span", {}, [label])]);
}

export function renderHome(root: HTMLElement): void {
  const progress = saveProgress(recordVisitToday(loadProgress()));
  const streak = currentStreak(progress.visitedDays);
  const mastery = computeMastery(civicQuestions, progress.masteredQuestionIds);
  const avgOral = averageOralScore(progress.oralAttempts);
  const resume = nextUnmastered(civicQuestions, progress.masteredQuestionIds);

  const content = renderShell(root, "home", {
    eyebrow: "Liberté · Égalité · Fraternité",
    title: "Sérénité"
  });

  content.append(
    el("section", { className: "hero" }, [
      kineticTitle("Préparez votre entretien, sereinement.", { tag: "h2" }),
      el("p", { className: "hero-lede" }, [
        "Le Livret du Citoyen, des questions classées par thème et une simulation orale guidée."
      ]),
      el("div", { className: "button-row", style: "margin-top:20px" }, [
        el("a", { href: "#/questions", className: "button-primary" }, ["Explorer les thèmes"]),
        el("a", { href: "#/oral", className: "button-secondary" }, ["S'entraîner à l'oral"])
      ])
    ]),

    el("div", { className: "card", "data-reveal": "" }, [
      el("div", { className: "meta-row" }, [
        el("div", {}, [
          el("h2", {}, ["Votre progression"]),
          el("p", {}, [`${mastery.mastered} questions maîtrisées sur ${mastery.total}`])
        ]),
        createRing(mastery.percent, { caption: "Global", size: 92 })
      ]),
      el("div", { className: "stat-grid", style: "margin-top:16px" }, [
        statTile(streak, "Jours d'affilée"),
        statTile(mastery.mastered, "Maîtrisées"),
        statTile(avgOral, progress.oralAttempts.length > 0 ? "Score oral" : "Oral à faire")
      ])
    ]),

    resume
      ? el("a", { href: `#/questions?q=${resume.id}`, className: "list-item", "data-reveal": "" }, [
          el("span", { className: "index" }, ["Reprise"]),
          el("span", { className: "body" }, [
            resume.question,
            el("small", {}, [CIVIC_CHAPTERS[resume.chapter].title])
          ]),
          el("span", { className: "check" }, ["→"])
        ])
      : el("div", { className: "card", "data-reveal": "" }, [
          el("h2", {}, ["Bravo 🎉"]),
          el("p", {}, ["Vous avez marqué toutes les questions comme maîtrisées. Passez à la simulation orale."])
        ]),

    el("div", { className: "section-title", "data-reveal": "" }, [
      el("h2", {}, ["Thèmes à la une"]),
      el("a", { href: "#/questions", style: "font-size:0.78rem" }, ["Tout voir"])
    ]),
    el(
      "div",
      { className: "theme-grid" },
      FEATURED_THEMES.map((chapter, index) =>
        themeCard(chapter, chapterMastery(civicQuestions, progress.masteredQuestionIds, chapter), {
          wide: index === 0,
          revealIndex: index
        })
      )
    ),

    el("div", { className: "section-title", "data-reveal": "" }, [el("h2", {}, ["Repères essentiels"])]),
    el(
      "div",
      { className: "stack" },
      civicPillars.map((pillar, index) =>
        el(
          "a",
          {
            href: "#/livret",
            className: "list-item",
            "data-reveal": "left",
            "data-reveal-delay": String(index * 70)
          },
          [
            el("span", { className: "index" }, [pillar.meta]),
            el("span", { className: "body" }, [pillar.title, el("small", {}, [pillar.subtitle])]),
            pillar.tag ? el("span", { className: "tag" }, [pillar.tag]) : null
          ]
        )
      )
    )
  );
}
