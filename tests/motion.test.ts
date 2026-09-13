import { describe, expect, it } from "vitest";
import { easeOutExpo, parallaxOffset, revealDelay, revealOnScroll, valueAtProgress } from "../src/lib/motion";
import { ringGeometry, ringOffset } from "../src/lib/ring";
import { tiltTransform } from "../src/lib/tilt";
import { splitWords } from "../src/lib/kinetic";

describe("motion primitives", () => {
  it("clamps and eases progress between 0 and 1", () => {
    expect(easeOutExpo(0)).toBe(0);
    expect(easeOutExpo(1)).toBe(1);
    expect(easeOutExpo(-3)).toBe(0);
    expect(easeOutExpo(9)).toBe(1);
    expect(easeOutExpo(0.5)).toBeGreaterThan(0.5);
  });

  it("interpolates counter values and lands exactly on the target", () => {
    expect(valueAtProgress(0, 100, 0)).toBe(0);
    expect(valueAtProgress(0, 100, 1)).toBe(100);
    expect(valueAtProgress(20, 40, 1)).toBe(40);
    expect(valueAtProgress(0, 100, 0.5)).toBeGreaterThan(50);
  });

  it("staggers reveal delays without exceeding the cap", () => {
    expect(revealDelay(0)).toBe(0);
    expect(revealDelay(2, 70)).toBe(140);
    expect(revealDelay(99, 70, 480)).toBe(480);
    expect(revealDelay(-5)).toBe(0);
  });

  it("computes a bounded parallax offset around the viewport centre", () => {
    expect(parallaxOffset(400, 800, 18)).toBe(0);
    expect(parallaxOffset(800, 800, 18)).toBeGreaterThan(0);
    expect(parallaxOffset(0, 800, 18)).toBeLessThan(0);
    expect(Math.abs(parallaxOffset(100000, 800, 18))).toBeLessThanOrEqual(18);
    expect(parallaxOffset(100, 0, 18)).toBe(0);
  });

  it("reveals elements immediately when IntersectionObserver is unavailable", () => {
    const host = document.createElement("div");
    const child = document.createElement("p");
    child.setAttribute("data-reveal", "");
    host.append(child);

    const dispose = revealOnScroll(host);
    expect(child.classList.contains("is-revealed")).toBe(true);
    dispose();
  });
});

describe("progress ring geometry", () => {
  it("derives radius and circumference from size and thickness", () => {
    const { radius, circumference } = ringGeometry(100, 10);
    expect(radius).toBe(45);
    expect(circumference).toBeCloseTo(2 * Math.PI * 45, 1);
  });

  it("maps a percentage to a dash offset, clamped at both ends", () => {
    const { circumference } = ringGeometry(100, 10);
    expect(ringOffset(circumference, 0)).toBeCloseTo(circumference, 1);
    expect(ringOffset(circumference, 100)).toBe(0);
    expect(ringOffset(circumference, 150)).toBe(0);
    expect(ringOffset(circumference, -20)).toBeCloseTo(circumference, 1);
  });
});

describe("pointer tilt", () => {
  it("returns opposite angles on opposite corners", () => {
    const topLeft = tiltTransform(0, 0, 200, 200);
    const bottomRight = tiltTransform(200, 200, 200, 200);
    expect(topLeft.rotateX).toBe(-bottomRight.rotateX);
    expect(topLeft.rotateY).toBe(-bottomRight.rotateY);
  });

  it("is neutral at the centre and safe on zero-sized elements", () => {
    const centre = tiltTransform(100, 100, 200, 200);
    expect(Math.abs(centre.rotateX)).toBe(0);
    expect(Math.abs(centre.rotateY)).toBe(0);
    expect(tiltTransform(10, 10, 0, 0)).toEqual({ rotateX: 0, rotateY: 0 });
  });
});

describe("kinetic title", () => {
  it("splits a sentence into words without empty entries", () => {
    expect(splitWords("  Préparez   votre entretien ")).toEqual(["Préparez", "votre", "entretien"]);
    expect(splitWords("   ")).toEqual([]);
  });
});
