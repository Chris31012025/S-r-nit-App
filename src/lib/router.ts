import { activateMotion } from "./enhance";
import { withViewTransition } from "./transitions";

export type Route = {
  path: string;
  render: (root: HTMLElement, params: URLSearchParams) => void | (() => void);
};

let cleanup: (() => void) | null = null;

function currentPath(): string {
  const hash = window.location.hash.slice(1);
  const [path] = hash.split("?");
  return path || "/";
}

function currentParams(): URLSearchParams {
  const hash = window.location.hash.slice(1);
  const [, query] = hash.split("?");
  return new URLSearchParams(query ?? "");
}

export function navigate(path: string): void {
  window.location.hash = path;
}

export function startRouter(root: HTMLElement, routes: Route[], notFound: Route["render"]): void {
  const paint = (): void => {
    if (cleanup) cleanup();
    const path = currentPath();
    const route = routes.find((r) => r.path === path);

    let disposeRoute: (() => void) | void;
    try {
      disposeRoute = route ? route.render(root, currentParams()) : notFound(root, currentParams());
    } catch (error) {
      console.error("Route rendering failed:", error);
      throw new Error(`Impossible d'afficher la page « ${path} ».`);
    }

    const disposeMotion = activateMotion(root);
    cleanup = () => {
      disposeMotion();
      if (typeof disposeRoute === "function") disposeRoute();
      cleanup = null;
    };
    window.scrollTo({ top: 0, behavior: "auto" });
  };

  const renderCurrent = (): void => withViewTransition(paint);

  window.addEventListener("hashchange", renderCurrent);
  if (!window.location.hash) window.location.hash = "/";
  renderCurrent();
}
