export type Route = {
  path: string;
  render: (root: HTMLElement, params: URLSearchParams) => void | (() => void);
};

let cleanup: (() => void) | void;

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
  const renderCurrent = () => {
    if (typeof cleanup === "function") cleanup();
    const path = currentPath();
    const route = routes.find((r) => r.path === path);
    cleanup = route ? route.render(root, currentParams()) : notFound(root, currentParams());
  };

  window.addEventListener("hashchange", renderCurrent);
  if (!window.location.hash) window.location.hash = "/";
  renderCurrent();
}
