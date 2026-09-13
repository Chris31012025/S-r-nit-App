import { livretChapters } from "../../data";
import { LIVRET_CATEGORIES } from "../../data/enums";
import type { LivretChapter } from "../../data/types";
import { el } from "../../lib/dom";
import { renderShell } from "../../lib/shell";
import { createThemeArt, livretVisual } from "../../lib/theme-visuals";

function chapterBanner(chapter: LivretChapter): HTMLElement {
  const art = createThemeArt(`livret-${chapter.id}`, livretVisual(chapter.category), 5);
  art.setAttribute("data-parallax", "");

  return el("section", { className: "theme-banner" }, [
    art,
    el("div", {}, [
      el("span", { className: "tag" }, [LIVRET_CATEGORIES[chapter.category]]),
      el("h2", { style: "margin:10px 0 4px;font-size:1.35rem;letter-spacing:-0.03em" }, [chapter.title]),
      el("p", { style: "margin:0;color:rgba(244,246,253,0.72);font-size:0.85rem" }, [chapter.subtitle])
    ])
  ]);
}

function renderChapter(content: HTMLElement, chapter: LivretChapter): void {
  content.append(
    el("a", { href: "#/livret", className: "button-secondary back-link" }, ["← Tous les chapitres"]),
    chapterBanner(chapter),
    ...chapter.articles.map((article, index) =>
      el(
        "article",
        { className: "card", "data-reveal": "", "data-reveal-delay": String(Math.min(320, index * 80)) },
        [
          el("h2", {}, [article.title]),
          el("p", { style: "white-space:pre-line" }, [article.content]),
          article.officialQuote
            ? el(
                "p",
                {
                  style:
                    "margin-top:12px;padding-left:12px;border-left:2px solid var(--border-strong);font-style:italic;color:var(--text)"
                },
                [article.officialQuote]
              )
            : null,
          article.keyPoints.length > 0
            ? el(
                "ul",
                { style: "margin:12px 0 0;padding-left:18px;color:var(--text-muted);font-size:0.86rem" },
                article.keyPoints.map((point) => el("li", { style: "margin-bottom:4px" }, [point]))
              )
            : null,
          article.legalReference
            ? el("p", { style: "margin-top:12px" }, [el("span", { className: "tag" }, [article.legalReference])])
            : null
        ]
      )
    )
  );
}

export function renderLivret(root: HTMLElement, params: URLSearchParams): void {
  const chapterId = params.get("chapter");
  const chapter = chapterId ? livretChapters.find((item) => item.id === chapterId) : undefined;

  const content = renderShell(root, "livret", {
    eyebrow: chapter ? `Chapitre ${chapter.number}` : "Référence officielle",
    title: chapter ? chapter.title : "Livret du Citoyen",
    subtitle: chapter ? undefined : "Principes, symboles, droits et institutions"
  });

  if (chapter) {
    renderChapter(content, chapter);
    return;
  }

  content.append(
    el(
      "div",
      { className: "stack" },
      livretChapters.map((item, index) =>
        el(
          "a",
          {
            href: `#/livret?chapter=${encodeURIComponent(item.id)}`,
            className: "list-item",
            "data-reveal": "left",
            "data-reveal-delay": String(Math.min(400, index * 60))
          },
          [
            el("span", { className: "index" }, [String(item.number).padStart(2, "0")]),
            el("span", { className: "body" }, [item.title, el("small", {}, [item.subtitle])]),
            el("span", { className: "tag" }, [LIVRET_CATEGORIES[item.category]])
          ]
        )
      )
    )
  );
}
