type Child = string | Node | null | undefined | false;

type Props = Record<string, string | boolean | ((event: Event) => void) | undefined>;

export function el<K extends keyof HTMLElementTagNameMap>(
  tag: K,
  props: Props = {},
  children: Child[] = []
): HTMLElementTagNameMap[K] {
  const node = document.createElement(tag);
  for (const [key, value] of Object.entries(props)) {
    if (value === undefined) continue;
    if (key.startsWith("on") && typeof value === "function") {
      node.addEventListener(key.slice(2).toLowerCase(), value as EventListener);
    } else if (key === "className" && typeof value === "string") {
      node.setAttribute("class", value);
    } else if (typeof value === "boolean") {
      if (value) node.setAttribute(key, "");
    } else if (typeof value === "string") {
      node.setAttribute(key, value);
    }
  }
  for (const child of children) {
    if (child === null || child === undefined || child === false) continue;
    node.append(typeof child === "string" ? document.createTextNode(child) : child);
  }
  return node;
}

export function clear(node: Element): void {
  while (node.firstChild) node.removeChild(node.firstChild);
}

export function mount(root: Element, ...children: Child[]): void {
  clear(root);
  for (const child of children) {
    if (child === null || child === undefined || child === false) continue;
    root.append(typeof child === "string" ? document.createTextNode(child) : child);
  }
}
