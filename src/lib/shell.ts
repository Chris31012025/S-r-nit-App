import { el, mount } from "./dom";
import { icon, type IconName } from "./icons";

export type TabKey = "home" | "livret" | "questions" | "oral" | "bilan";

type Tab = { key: TabKey; path: string; label: string; icon: IconName };

const TABS: Tab[] = [
  { key: "home", path: "/", label: "Accueil", icon: "home" },
  { key: "livret", path: "/livret", label: "Livret", icon: "livret" },
  { key: "questions", path: "/questions", label: "Thèmes", icon: "questions" },
  { key: "oral", path: "/oral", label: "Oral", icon: "oral" },
  { key: "bilan", path: "/bilan", label: "Bilan", icon: "bilan" }
];

export type ShellHeader = {
  eyebrow?: string;
  title: string;
  subtitle?: string;
};

/** Injecte une seule fois le fond « aurora » animé, hors du cycle de rendu. */
export function mountAurora(host: HTMLElement = document.body): void {
  if (host.querySelector(".aurora")) return;
  const aurora = el("div", { className: "aurora", "aria-hidden": "true" }, [
    el("span", {}),
    el("span", {}),
    el("span", {})
  ]);
  host.prepend(aurora);
}

function navigation(active: TabKey): HTMLElement {
  const activeIndex = Math.max(
    0,
    TABS.findIndex((tab) => tab.key === active)
  );

  return el(
    "nav",
    { className: "bottom-nav", style: `--tab-count:${TABS.length}`, "aria-label": "Navigation principale" },
    [
      el("span", { className: "nav-pill", style: `--tab-index:${activeIndex}`, "aria-hidden": "true" }),
      ...TABS.map((tab) =>
        el(
          "a",
          {
            href: `#${tab.path}`,
            className: tab.key === active ? "active" : undefined,
            "aria-current": tab.key === active ? "page" : undefined
          },
          [icon(tab.icon), el("span", {}, [tab.label])]
        )
      )
    ]
  );
}

export function renderShell(root: HTMLElement, active: TabKey, header: ShellHeader): HTMLElement {
  const content = el("div", { className: "content page-enter" });

  const shell = el("div", { className: "app-shell" }, [
    el("header", { className: "app-header" }, [
      header.eyebrow ? el("span", { className: "eyebrow" }, [header.eyebrow]) : null,
      el("h1", {}, [header.title]),
      header.subtitle ? el("p", {}, [header.subtitle]) : null
    ]),
    content
  ]);

  // La navigation reste hors de `.app-shell` : un ancêtre animé en `transform`
  // formerait un bloc conteneur et casserait son `position: fixed`.
  mount(root, shell, navigation(active));
  return content;
}
