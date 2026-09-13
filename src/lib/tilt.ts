/**
 * Inclinaison 3D suivant le pointeur, réservée aux appareils avec survol
 * (souris / trackpad). Sur écran tactile, la parallaxe au défilement prend le relais.
 */
import { prefersReducedMotion } from "./motion";

const MAX_ANGLE = 7;

export function tiltTransform(
  offsetX: number,
  offsetY: number,
  width: number,
  height: number,
  maxAngle = MAX_ANGLE
): { rotateX: number; rotateY: number } {
  if (width <= 0 || height <= 0) return { rotateX: 0, rotateY: 0 };
  const ratioX = Math.min(1, Math.max(-1, (offsetX / width) * 2 - 1));
  const ratioY = Math.min(1, Math.max(-1, (offsetY / height) * 2 - 1));
  return {
    rotateX: Math.round(-ratioY * maxAngle * 100) / 100,
    rotateY: Math.round(ratioX * maxAngle * 100) / 100
  };
}

function supportsHover(): boolean {
  if (typeof window === "undefined" || typeof window.matchMedia !== "function") return false;
  return window.matchMedia("(hover: hover) and (pointer: fine)").matches;
}

/** Active l'effet d'inclinaison sur les éléments `[data-tilt]`. Renvoie un nettoyage. */
export function enableTilt(scope: ParentNode): () => void {
  if (prefersReducedMotion() || !supportsHover()) return () => undefined;

  const targets = Array.from(scope.querySelectorAll<HTMLElement>("[data-tilt]"));
  if (targets.length === 0) return () => undefined;

  const disposers = targets.map((target) => {
    const onMove = (event: PointerEvent): void => {
      const rect = target.getBoundingClientRect();
      const { rotateX, rotateY } = tiltTransform(
        event.clientX - rect.left,
        event.clientY - rect.top,
        rect.width,
        rect.height
      );
      target.classList.add("is-tilting");
      target.style.transform = `perspective(900px) rotateX(${rotateX}deg) rotateY(${rotateY}deg) translateZ(6px)`;
    };

    const onLeave = (): void => {
      target.classList.remove("is-tilting");
      target.style.transform = "";
    };

    target.addEventListener("pointermove", onMove);
    target.addEventListener("pointerleave", onLeave);
    return () => {
      target.removeEventListener("pointermove", onMove);
      target.removeEventListener("pointerleave", onLeave);
      onLeave();
    };
  });

  return () => {
    for (const dispose of disposers) dispose();
  };
}
