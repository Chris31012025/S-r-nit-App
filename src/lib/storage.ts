const memoryFallback = new Map<string, string>();

function hasLocalStorage(): boolean {
  try {
    const key = "__serenite_probe__";
    window.localStorage.setItem(key, "1");
    window.localStorage.removeItem(key);
    return true;
  } catch {
    return false;
  }
}

const persistent = hasLocalStorage();

export function readJson<T>(key: string, fallback: T): T {
  try {
    const raw = persistent ? window.localStorage.getItem(key) : memoryFallback.get(key) ?? null;
    if (!raw) return fallback;
    return JSON.parse(raw) as T;
  } catch {
    return fallback;
  }
}

export function writeJson<T>(key: string, value: T): void {
  const raw = JSON.stringify(value);
  try {
    if (persistent) window.localStorage.setItem(key, raw);
    else memoryFallback.set(key, raw);
  } catch {
    memoryFallback.set(key, raw);
  }
}
