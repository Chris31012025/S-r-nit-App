import { livretChapters } from "../../data";
import { LIVRET_CATEGORIES } from "../../data/enums";
import { el } from "../../lib/dom";
import { renderShell } from "../../lib/shell";

export function renderLivret(root: HTMLElement, params: URLSearchParams): void {
  const chapterId = params.get("chapter");
  const chapter = chapterId ? livretChapters.find((c) => c.id === chapterId) : null;

  const content = renderShell(
    root,
    "livret",
    "Livret du Citoyen",
    chapter ? chapter.title : "Principes, symboles, droits et institutions"
  );

  if (chapter) {
    content.append(
      el("a", { href: "#/livret", className: "button-secondary" }, ["← Retour aux chapitres"]),
      ...chapter.articles.map((article) =>
        el("div", { className: "card" }, [
          el("h2", {}, [article.title]),
          el("p", { style: "white-space:pre-line" }, [article.content]),
          article.officialQuote ? el("p", { style: "font-style:italic" }, [article.officialQuote]) : null,
          article.keyPoints.length > 0
            ? el(
                "ul",
                { style: "margin:8px 0 0; padding-left:18px; color:var(--text-muted); font-size:0.9rem" },
                article.keyPoints.map((point) => el("li", {}, [point]))
              )
            : null,
          article.legalReference ? el("p", { className: "tag" }, [article.legalReference]) : null
        ])
      )
    );
    return;
  }

  content.append(
    ...livretChapters.map((c) =>
      el("a", { href: `#/livret?chapter=${encodeURIComponent(c.id)}`, className: "list-item" }, [
        el("div", { className: "meta-row" }, [
          el("strong", {}, [`${c.number}. ${c.title}`]),
          el("span", { className: "tag" }, [LIVRET_CATEGORIES[c.category]])
        ]),
        el("span", {}, [c.subtitle])
      ])
    )
  );
}
