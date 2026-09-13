import { civicPillars, guide707Questions } from "../../data";
import { el } from "../../lib/dom";
import { renderShell } from "../../lib/shell";
import { currentStreak, loadProgress, recordVisitToday, saveProgress } from "../../state/progress";

export function renderHome(root: HTMLElement): void {
  const progress = saveProgress(recordVisitToday(loadProgress()));
  const streak = currentStreak(progress.visitedDays);
  const mastered = progress.masteredQuestionIds.length;
  const total = guide707Questions.length;
  const masteryPct = total > 0 ? Math.round((mastered / total) * 100) : 0;

  const content = renderShell(
    root,
    "home",
    "Sérénité Républicaine",
    "Préparez sereinement votre entretien de naturalisation"
  );

  content.append(
    el("div", { className: "card" }, [
      el("div", { className: "meta-row" }, [
        el("h2", {}, ["Votre progression"]),
        el("span", { className: "tag" }, [`🔥 ${streak} j`])
      ]),
      el("p", {}, [`${mastered} / ${total} questions du Guide 707 maîtrisées`]),
      el("div", { className: "progress-bar" }, [el("span", { style: `width:${masteryPct}%` })])
    ]),
    el(
      "div",
      { className: "content", style: "padding:0; gap:12px" },
      civicPillars.map((pillar) =>
        el(
          "a",
          { href: "#/livret", className: "list-item" },
          [
            el("div", { className: "meta-row" }, [
              el("strong", {}, [pillar.title]),
              pillar.tag ? el("span", { className: "tag" }, [pillar.tag]) : null
            ]),
            el("span", {}, [pillar.subtitle]),
            el("span", { className: "tag" }, [pillar.meta])
          ]
        )
      )
    )
  );
}
