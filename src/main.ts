import "./styles/base.css";

import { renderBilan } from "./features/bilan/bilan";
import { renderQuestions } from "./features/questions/questions";
import { renderHome } from "./features/home/home";
import { renderLivret } from "./features/livret/livret";
import { renderOral } from "./features/oral/oral";
import { el, mount } from "./lib/dom";
import { startRouter } from "./lib/router";
import { mountAurora } from "./lib/shell";

const app = document.getElementById("app");
if (!app) throw new Error("Root #app element not found");

mountAurora();

startRouter(
  app,
  [
    { path: "/", render: renderHome },
    { path: "/livret", render: renderLivret },
    { path: "/questions", render: renderQuestions },
    { path: "/oral", render: renderOral },
    { path: "/bilan", render: renderBilan }
  ],
  (root) =>
    mount(
      root,
      el("div", { className: "empty-state" }, [
        el("p", {}, ["Page introuvable."]),
        el("a", { href: "#/", className: "button-secondary", style: "margin-top:16px" }, ["Retour à l'accueil"])
      ])
    )
);
