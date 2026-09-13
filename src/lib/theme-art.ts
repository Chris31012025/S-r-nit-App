/**
 * Œuvres génératives animées servant d'illustration à chaque thème.
 * Aucune image externe : tout est dessiné en SVG, de façon déterministe,
 * à partir d'une graine textuelle (le thème) — même thème, même visuel.
 */
import { svgEl } from "./dom";

export type ArtOrb = {
  cx: number;
  cy: number;
  r: number;
  color: string;
  opacity: number;
  driftX: number;
  driftY: number;
  scale: number;
  duration: number;
  delay: number;
};

export type ArtStroke = {
  cy: number;
  amplitude: number;
  opacity: number;
};

export type ArtSpec = {
  base: readonly [string, string];
  orbs: ArtOrb[];
  strokes: ArtStroke[];
};

export const ART_VIEWBOX = 200;

/** Hachage FNV-1a 32 bits, stable entre exécutions. */
export function hashString(input: string): number {
  let hash = 0x811c9dc5;
  for (let i = 0; i < input.length; i += 1) {
    hash ^= input.charCodeAt(i);
    hash = Math.imul(hash, 0x01000193);
  }
  return hash >>> 0;
}

/** Générateur pseudo-aléatoire déterministe (mulberry32). */
export function createRandom(seed: number): () => number {
  let state = seed >>> 0;
  return () => {
    state = (state + 0x6d2b79f5) >>> 0;
    let t = state;
    t = Math.imul(t ^ (t >>> 15), t | 1);
    t ^= t + Math.imul(t ^ (t >>> 7), t | 61);
    return ((t ^ (t >>> 14)) >>> 0) / 4294967296;
  };
}

const round = (value: number): number => Math.round(value * 10) / 10;

export function createArtSpec(
  seed: string,
  palette: readonly string[],
  base: readonly [string, string],
  orbCount = 5
): ArtSpec {
  if (palette.length === 0) throw new Error("createArtSpec requires at least one colour");
  const random = createRandom(hashString(seed));

  const orbs = Array.from({ length: orbCount }, (_, index): ArtOrb => {
    const color = palette[index % palette.length] as string;
    return {
      cx: round(random() * ART_VIEWBOX),
      cy: round(random() * ART_VIEWBOX),
      r: round(38 + random() * 62),
      color,
      opacity: round(0.35 + random() * 0.45),
      driftX: round(-14 + random() * 28),
      driftY: round(-14 + random() * 28),
      scale: round(1.05 + random() * 0.3),
      duration: round(11 + random() * 12),
      delay: round(-random() * 10)
    };
  });

  const strokes = Array.from({ length: 3 }, (_, index): ArtStroke => ({
    cy: round(56 + index * 44 + random() * 14),
    amplitude: round(12 + random() * 24),
    opacity: round(0.08 + random() * 0.12)
  }));

  return { base, orbs, strokes };
}

function strokePath(stroke: ArtStroke): string {
  const { cy, amplitude } = stroke;
  return [
    `M -20 ${round(cy)}`,
    `C ${round(ART_VIEWBOX * 0.2)} ${round(cy - amplitude)}`,
    `${round(ART_VIEWBOX * 0.45)} ${round(cy + amplitude)}`,
    `${round(ART_VIEWBOX * 0.62)} ${round(cy)}`,
    `S ${round(ART_VIEWBOX * 0.9)} ${round(cy - amplitude)}`,
    `${ART_VIEWBOX + 20} ${round(cy + amplitude / 2)}`
  ].join(" ");
}

/** Construit l'élément SVG animé correspondant à une spécification. */
export function createArtElement(spec: ArtSpec, idPrefix: string): SVGSVGElement {
  const baseId = `${idPrefix}-base`;

  const defs = svgEl("defs", {}, [
    svgEl("linearGradient", { id: baseId, x1: "0", y1: "0", x2: "0.6", y2: "1" }, [
      svgEl("stop", { offset: "0", "stop-color": spec.base[0] }),
      svgEl("stop", { offset: "1", "stop-color": spec.base[1] })
    ]),
    ...spec.orbs.map((orb, index) =>
      svgEl("radialGradient", { id: `${idPrefix}-orb-${index}`, cx: "0.5", cy: "0.5", r: "0.5" }, [
        svgEl("stop", { offset: "0", "stop-color": orb.color, "stop-opacity": orb.opacity }),
        svgEl("stop", { offset: "1", "stop-color": orb.color, "stop-opacity": "0" })
      ])
    )
  ]);

  const orbs = spec.orbs.map((orb, index) =>
    svgEl("circle", {
      cx: orb.cx,
      cy: orb.cy,
      r: orb.r,
      fill: `url(#${idPrefix}-orb-${index})`,
      style: [
        `--drift-x:${orb.driftX}px`,
        `--drift-y:${orb.driftY}px`,
        `--drift-scale:${orb.scale}`,
        `transform-origin:${orb.cx}px ${orb.cy}px`,
        `animation:blob-float ${orb.duration}s ease-in-out ${orb.delay}s infinite`
      ].join(";")
    })
  );

  const strokes = spec.strokes.map((stroke) =>
    svgEl("path", {
      d: strokePath(stroke),
      fill: "none",
      stroke: "#ffffff",
      "stroke-opacity": stroke.opacity,
      "stroke-width": "0.8"
    })
  );

  return svgEl(
    "svg",
    {
      class: "theme-art",
      viewBox: `0 0 ${ART_VIEWBOX} ${ART_VIEWBOX}`,
      preserveAspectRatio: "xMidYMid slice",
      "aria-hidden": "true",
      focusable: "false"
    },
    [
      defs,
      svgEl("rect", { x: "0", y: "0", width: ART_VIEWBOX, height: ART_VIEWBOX, fill: `url(#${baseId})` }),
      ...orbs,
      ...strokes
    ]
  );
}
