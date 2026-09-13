/** Transitions de page via la View Transitions API, avec repli silencieux. */
import { prefersReducedMotion } from "./motion";

type TransitionCapableDocument = Document & {
  startViewTransition?: (callback: () => void) => { finished?: Promise<unknown> };
};

export function supportsViewTransitions(): boolean {
  if (typeof document === "undefined") return false;
  return typeof (document as TransitionCapableDocument).startViewTransition === "function";
}

export function withViewTransition(update: () => void): void {
  if (!supportsViewTransitions() || prefersReducedMotion()) {
    update();
    return;
  }

  try {
    (document as TransitionCapableDocument).startViewTransition?.(update);
  } catch (error) {
    console.warn("View transition unavailable, rendering directly:", error);
    update();
  }
}
