/** Icônes de navigation, tracées en SVG inline (aucune dépendance externe). */
import { svgEl } from "./dom";

const PATHS = {
  home: ["M4 11.2 12 4l8 7.2", "M6.5 10v9.5h11V10"],
  livret: ["M5 4.5h9a3 3 0 0 1 3 3v12", "M5 4.5v15h12", "M8.5 9h6", "M8.5 12.5h4"],
  questions: ["M9.2 9a2.8 2.8 0 1 1 3.5 2.7c-.8.3-1.2 1-1.2 1.8v.4", "M11.5 17.6h.01", "M12 3.5a8.5 8.5 0 1 1 0 17 8.5 8.5 0 0 1 0-17Z"],
  oral: ["M12 4.5a2.6 2.6 0 0 1 2.6 2.6v4.6a2.6 2.6 0 0 1-5.2 0V7.1A2.6 2.6 0 0 1 12 4.5Z", "M6.5 11.4a5.5 5.5 0 0 0 11 0", "M12 16.9v3"],
  bilan: ["M4.5 19.5h15", "M7.5 19.5v-6", "M12 19.5V6.5", "M16.5 19.5v-9"]
} as const;

export type IconName = keyof typeof PATHS;

export function icon(name: IconName): SVGSVGElement {
  return svgEl(
    "svg",
    {
      viewBox: "0 0 24 24",
      fill: "none",
      stroke: "currentColor",
      "stroke-width": "1.6",
      "stroke-linecap": "round",
      "stroke-linejoin": "round",
      "aria-hidden": "true",
      focusable: "false"
    },
    PATHS[name].map((d) => svgEl("path", { d }))
  );
}
