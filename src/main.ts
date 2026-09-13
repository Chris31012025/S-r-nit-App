import "./styles/base.css";
import { renderBilan } from "./features/bilan/bilan";
import { renderGuide707 } from "./features/guide707/guide707";
import { renderHome } from "./features/home/home";
import { renderLivret } from "./features/livret/livret";
import { renderOral } from "./features/oral/oral";
import { el, mount } from "./lib/dom";
import { startRouter } from "./lib/router";

const app = document.getElementById("app");
if (!app) throw new Error("Root #app element not found");

startRouter(
  app,
  [
    { path: "/", render: renderHome },
    { path: "/livret", render: renderLivret },
    { path: "/guide707", render: renderGuide707 },
    { path: "/oral", render: renderOral },
    { path: "/bilan", render: renderBilan }
  ],
  (root) => mount(root, el("div", { className: "empty-state" }, ["Page introuvable."]))
);
