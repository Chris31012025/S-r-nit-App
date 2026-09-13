import { describe, expect, it } from "vitest";
import { ART_VIEWBOX, createArtElement, createArtSpec, createRandom, hashString } from "../src/lib/theme-art";
import { THEME_VISUALS } from "../src/lib/theme-visuals";
import { CIVIC_CHAPTERS, type CivicChapterKey } from "../src/data/enums";

const PALETTE = ["#ff0000", "#00ff00", "#0000ff"] as const;
const BASE = ["#000000", "#111111"] as const;

describe("generative theme art", () => {
  it("hashes strings deterministically and distinctly", () => {
    expect(hashString("REPUBLIQUE")).toBe(hashString("REPUBLIQUE"));
    expect(hashString("REPUBLIQUE")).not.toBe(hashString("HISTOIRE"));
  });

  it("produces a repeatable pseudo-random sequence in [0, 1)", () => {
    const randomA = createRandom(42);
    const randomB = createRandom(42);
    const valuesA = Array.from({ length: 8 }, () => randomA());
    const valuesB = Array.from({ length: 8 }, () => randomB());

    expect(valuesA).toEqual(valuesB);
    expect(new Set(valuesA).size).toBeGreaterThan(1);
    for (const value of valuesA) {
      expect(value).toBeGreaterThanOrEqual(0);
      expect(value).toBeLessThan(1);
    }
  });

  it("builds the same spec for the same seed and a different one otherwise", () => {
    const specA = createArtSpec("EUROPE", PALETTE, BASE);
    const specB = createArtSpec("EUROPE", PALETTE, BASE);
    const specC = createArtSpec("CULTURE", PALETTE, BASE);

    expect(specA).toEqual(specB);
    expect(specA.orbs).not.toEqual(specC.orbs);
  });

  it("keeps orbs inside sane bounds and only uses palette colours", () => {
    const spec = createArtSpec("HISTOIRE", PALETTE, BASE, 6);
    expect(spec.orbs).toHaveLength(6);
    for (const orb of spec.orbs) {
      expect(orb.cx).toBeGreaterThanOrEqual(0);
      expect(orb.cx).toBeLessThanOrEqual(ART_VIEWBOX);
      expect(orb.cy).toBeGreaterThanOrEqual(0);
      expect(orb.cy).toBeLessThanOrEqual(ART_VIEWBOX);
      expect(orb.r).toBeGreaterThan(0);
      expect(orb.opacity).toBeGreaterThan(0);
      expect(orb.opacity).toBeLessThanOrEqual(1);
      expect(PALETTE).toContain(orb.color);
    }
  });

  it("rejects an empty palette", () => {
    expect(() => createArtSpec("X", [], BASE)).toThrow(/palette|colour/i);
  });

  it("renders an SVG whose gradient ids are unique within the element", () => {
    const spec = createArtSpec("REPUBLIQUE", PALETTE, BASE);
    const svg = createArtElement(spec, "test-art");
    const ids = Array.from(svg.querySelectorAll("[id]")).map((node) => node.id);

    expect(svg.tagName.toLowerCase()).toBe("svg");
    expect(svg.getAttribute("aria-hidden")).toBe("true");
    expect(new Set(ids).size).toBe(ids.length);
    expect(svg.querySelectorAll("circle")).toHaveLength(spec.orbs.length);
  });

  it("defines a visual for every civic chapter", () => {
    const chapters = Object.keys(CIVIC_CHAPTERS) as CivicChapterKey[];
    for (const chapter of chapters) {
      const visual = THEME_VISUALS[chapter];
      expect(visual).toBeDefined();
      expect(visual.palette.length).toBeGreaterThan(0);
      expect(visual.base).toHaveLength(2);
    }
  });
});
