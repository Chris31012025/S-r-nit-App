/** Anneau de progression animé (SVG), avec dégradé tricolore. */
import { el, svgEl } from "./dom";
import { animateNumber } from "./motion";

export type RingOptions = {
  size?: number;
  thickness?: number;
  caption?: string;
  suffix?: string;
};

let ringCount = 0;

export function ringGeometry(size: number, thickness: number): { radius: number; circumference: number } {
  const radius = Math.max(1, (size - thickness) / 2);
  return { radius, circumference: Math.round(2 * Math.PI * radius * 100) / 100 };
}

export function ringOffset(circumference: number, percent: number): number {
  const clamped = Math.min(100, Math.max(0, percent));
  return Math.round(circumference * (1 - clamped / 100) * 100) / 100;
}

export function createRing(percent: number, options: RingOptions = {}): HTMLElement {
  const { size = 104, thickness = 9, caption = "", suffix = "%" } = options;
  const { radius, circumference } = ringGeometry(size, thickness);
  ringCount += 1;
  const gradientId = `ring-gradient-${ringCount}`;
  const center = size / 2;

  const gradient = svgEl("linearGradient", { id: gradientId, x1: "0", y1: "0", x2: "1", y2: "1" }, [
    svgEl("stop", { offset: "0", "stop-color": "#3c63ff" }),
    svgEl("stop", { offset: "0.5", "stop-color": "#eef2ff" }),
    svgEl("stop", { offset: "1", "stop-color": "#d4394f" })
  ]);

  const svg = svgEl(
    "svg",
    { width: size, height: size, viewBox: `0 0 ${size} ${size}`, "aria-hidden": "true", focusable: "false" },
    [
      svgEl("defs", {}, [gradient]),
      svgEl("circle", { class: "ring-track", cx: center, cy: center, r: radius, "stroke-width": thickness }),
      svgEl("circle", {
        class: "ring-value",
        cx: center,
        cy: center,
        r: radius,
        "stroke-width": thickness,
        stroke: `url(#${gradientId})`
      })
    ]
  );

  const valueNode = el("strong", {}, ["0"]);
  const wrapper = el(
    "div",
    {
      className: "ring",
      style: `--ring-circumference:${circumference};--ring-offset:${ringOffset(circumference, percent)}`
    },
    [svg, el("div", { className: "ring-label" }, [valueNode, caption ? el("span", {}, [caption]) : null])]
  );

  animateNumber(valueNode, Math.round(percent), { duration: 1400, suffix });
  return wrapper;
}
