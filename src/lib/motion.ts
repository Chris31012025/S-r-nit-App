/**
 * Primitives d'animation : révélation au défilement, compteurs animés, parallaxe.
 * Toutes respectent `prefers-reduced-motion`.
 */

const REVEAL_STEP_MS = 70;
const REVEAL_MAX_DELAY_MS = 480;

export function prefersReducedMotion(): boolean {
  if (typeof window === "undefined" || typeof window.matchMedia !== "function") return false;
  return window.matchMedia("(prefers-reduced-motion: reduce)").matches;
}

export function easeOutExpo(progress: number): number {
  const clamped = Math.min(1, Math.max(0, progress));
  return clamped === 1 ? 1 : 1 - Math.pow(2, -10 * clamped);
}

export function valueAtProgress(from: number, to: number, progress: number): number {
  return Math.round(from + (to - from) * easeOutExpo(progress));
}

export function revealDelay(index: number, step = REVEAL_STEP_MS, max = REVEAL_MAX_DELAY_MS): number {
  return Math.min(max, Math.max(0, index) * step);
}

/**
 * Révèle progressivement les éléments porteurs de `data-reveal` lorsqu'ils
 * entrent dans le champ de vision. Renvoie une fonction de nettoyage.
 */
export function revealOnScroll(scope: ParentNode): () => void {
  const targets = Array.from(scope.querySelectorAll<HTMLElement>("[data-reveal]"));
  if (targets.length === 0) return () => undefined;

  const showAll = (): void => {
    for (const target of targets) target.classList.add("is-revealed");
  };

  if (prefersReducedMotion() || typeof IntersectionObserver === "undefined") {
    showAll();
    return () => undefined;
  }

  targets.forEach((target, index) => {
    const explicit = target.dataset.revealDelay;
    const delay = explicit ? Number.parseInt(explicit, 10) : revealDelay(index);
    target.style.setProperty("--reveal-delay", `${Number.isFinite(delay) ? delay : 0}ms`);
  });

  const observer = new IntersectionObserver(
    (entries) => {
      for (const entry of entries) {
        if (!entry.isIntersecting) continue;
        entry.target.classList.add("is-revealed");
        observer.unobserve(entry.target);
      }
    },
    { threshold: 0.08, rootMargin: "0px 0px -6% 0px" }
  );

  for (const target of targets) observer.observe(target);
  return () => observer.disconnect();
}

/** Anime un compteur numérique de `from` vers `to`. */
export function animateNumber(
  node: HTMLElement,
  to: number,
  options: { from?: number; duration?: number; suffix?: string } = {}
): () => void {
  const { from = 0, duration = 1200, suffix = "" } = options;
  const write = (value: number): void => {
    node.textContent = `${value}${suffix}`;
  };

  if (prefersReducedMotion() || typeof requestAnimationFrame === "undefined") {
    write(to);
    return () => undefined;
  }

  let frame = 0;
  const started = performance.now();
  const tick = (now: number): void => {
    const progress = duration <= 0 ? 1 : (now - started) / duration;
    write(valueAtProgress(from, to, progress));
    if (progress < 1) frame = requestAnimationFrame(tick);
  };

  write(from);
  frame = requestAnimationFrame(tick);
  return () => cancelAnimationFrame(frame);
}

/** Calcule le décalage de parallaxe d'un élément selon sa position à l'écran. */
export function parallaxOffset(elementCenter: number, viewportHeight: number, strength: number): number {
  if (viewportHeight <= 0) return 0;
  const ratio = (elementCenter - viewportHeight / 2) / viewportHeight;
  return Math.round(Math.min(1, Math.max(-1, ratio)) * strength * 10) / 10;
}

/**
 * Applique une parallaxe douce aux éléments `[data-parallax]` pendant le défilement.
 * Renvoie une fonction de nettoyage.
 */
export function parallaxOnScroll(scope: ParentNode, strength = 18): () => void {
  const targets = Array.from(scope.querySelectorAll<HTMLElement>("[data-parallax]"));
  if (targets.length === 0 || prefersReducedMotion() || typeof requestAnimationFrame === "undefined") {
    return () => undefined;
  }

  let queued = false;
  const apply = (): void => {
    queued = false;
    const viewportHeight = window.innerHeight;
    for (const target of targets) {
      const rect = target.getBoundingClientRect();
      const offset = parallaxOffset(rect.top + rect.height / 2, viewportHeight, strength);
      target.style.setProperty("--parallax", `${offset}px`);
    }
  };

  const onScroll = (): void => {
    if (queued) return;
    queued = true;
    requestAnimationFrame(apply);
  };

  apply();
  window.addEventListener("scroll", onScroll, { passive: true });
  window.addEventListener("resize", onScroll, { passive: true });
  return () => {
    window.removeEventListener("scroll", onScroll);
    window.removeEventListener("resize", onScroll);
  };
}
