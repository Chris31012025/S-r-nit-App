/** Active les comportements animés sur un arbre fraîchement rendu. */
import { parallaxOnScroll, revealOnScroll } from "./motion";
import { enableTilt } from "./tilt";

export function activateMotion(scope: ParentNode): () => void {
  const disposers = [revealOnScroll(scope), parallaxOnScroll(scope), enableTilt(scope)];
  return () => {
    for (const dispose of disposers) dispose();
  };
}
