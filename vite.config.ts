import { defineConfig } from "vitest/config";
import { VitePWA } from "vite-plugin-pwa";

export default defineConfig({
  plugins: [
    VitePWA({
      registerType: "autoUpdate",
      includeAssets: ["icons/icon-192.svg", "icons/icon-512.svg"],
      manifest: {
        id: "/",
        name: "Sérénité Républicaine",
        short_name: "Sérénité",
        description:
          "Préparation sereine à l'entretien de naturalisation française : Livret du Citoyen, Guide 707, simulation orale et bilan de progression.",
        lang: "fr",
        start_url: "/",
        scope: "/",
        display: "standalone",
        background_color: "#0b1f3a",
        theme_color: "#0b1f3a",
        icons: [
          {
            src: "icons/icon-192.svg",
            sizes: "192x192",
            type: "image/svg+xml",
            purpose: "any"
          },
          {
            src: "icons/icon-512.svg",
            sizes: "512x512",
            type: "image/svg+xml",
            purpose: "any"
          },
          {
            src: "icons/icon-maskable.svg",
            sizes: "512x512",
            type: "image/svg+xml",
            purpose: "maskable"
          }
        ]
      },
      workbox: {
        globPatterns: ["**/*.{js,css,html,svg,json,woff2}"],
        navigateFallbackDenylist: [/^\/api\//]
      }
    })
  ],
  server: {
    host: true
  },
  test: {
    environment: "jsdom"
  }
});
