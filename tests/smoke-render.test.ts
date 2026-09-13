import { describe, expect, it } from "vitest";
import { renderHome } from "../src/features/home/home";
import { renderQuestions } from "../src/features/questions/questions";
import { renderLivret } from "../src/features/livret/livret";
import { renderOral } from "../src/features/oral/oral";
import { renderBilan } from "../src/features/bilan/bilan";

function host(): HTMLElement {
  const node = document.createElement("div");
  document.body.append(node);
  return node;
}

describe("every screen renders without throwing", () => {
  it("home", () => {
    const root = host();
    renderHome(root);
    expect(root.querySelector(".app-shell")).not.toBeNull();
  });

  it("theme grid, theme detail and question card", () => {
    const grid = host();
    renderQuestions(grid, new URLSearchParams());
    expect(grid.querySelectorAll(".theme-card").length).toBeGreaterThan(0);

    const theme = host();
    renderQuestions(theme, new URLSearchParams("theme=REPUBLIQUE"));
    expect(theme.querySelector(".theme-banner")).not.toBeNull();

    const detail = host();
    renderQuestions(detail, new URLSearchParams("q=1"));
    expect(detail.querySelector(".flip")).not.toBeNull();
  });

  it("livret list and chapter", () => {
    const list = host();
    renderLivret(list, new URLSearchParams());
    expect(list.querySelectorAll(".list-item").length).toBeGreaterThan(0);
  });

  it("oral and bilan", () => {
    const oral = host();
    renderOral(oral, new URLSearchParams());
    expect(oral.querySelector("textarea")).not.toBeNull();

    const bilan = host();
    renderBilan(bilan);
    expect(bilan.querySelector(".ring")).not.toBeNull();
  });
});
