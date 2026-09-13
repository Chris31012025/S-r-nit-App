import { el, mount } from "./dom";

export type TabKey = "home" | "livret" | "questions" | "oral" | "bilan";

const TABS: { key: TabKey; path: string; label: string }[] = [
  { key: "home", path: "/", label: "Accueil" },
  { key: "livret", path: "/livret", label: "Livret" },
  { key: "questions", path: "/questions", label: "Questions" },
  { key: "oral", path: "/oral", label: "Oral" },
  { key: "bilan", path: "/bilan", label: "Bilan" }
];

export function renderShell(
  root: HTMLElement,
  active: TabKey,
  headerTitle: string,
  headerSubtitle: string
): HTMLElement {
  const content = el("div", { className: "content" });

  const nav = el(
    "nav",
    { className: "bottom-nav" },
    TABS.map((tab) =>
      el(
        "a",
        {
          href: `#${tab.path}`,
          className: tab.key === active ? "active" : undefined
        },
        [tab.label]
      )
    )
  );

  const shell = el("div", { className: "app-shell" }, [
    el("header", { className: "app-header" }, [
      el("h1", {}, [headerTitle]),
      el("p", {}, [headerSubtitle])
    ]),
    content,
    nav
  ]);

  mount(root, shell);
  return content;
}
