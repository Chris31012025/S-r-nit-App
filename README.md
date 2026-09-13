# Sérénité Républicaine

PWA de préparation à l'entretien de naturalisation française : Livret du Citoyen,
questions classées par thème, simulation orale et bilan de progression.

Installable, utilisable hors ligne, sans compte ni serveur de données : toute la
progression reste dans le navigateur de l'utilisateur.

## Stack

- **Vite + TypeScript** (mode strict), sans framework d'interface
- **vite-plugin-pwa** (Workbox) pour le manifeste et le service worker
- **Vitest** pour les tests unitaires
- **Fonction serverless Vercel** (`api/oral.ts`) pour l'analyse des réponses orales

## Démarrage local

```bash
npm ci
npm run dev      # serveur de développement
npm test         # suite de tests
npm run build    # vérification des types + build de production dans dist/
npm run preview  # prévisualisation du build
```

## Déploiement sur Vercel

1. Sur [vercel.com](https://vercel.com), **Add New → Project**, puis importer
   le dépôt GitHub.
2. Laisser la configuration détectée telle quelle : `vercel.json` fixe déjà le
   framework (Vite), la commande de build (`npm run build`) et le dossier de
   sortie (`dist`).
3. **Facultatif** — dans *Settings → Environment Variables*, ajouter
   `GEMINI_API_KEY` (voir ci-dessous).
4. **Deploy**.

Les commits sur `main` redéploient automatiquement.

### Variable d'environnement

| Variable | Requise | Rôle |
| --- | --- | --- |
| `GEMINI_API_KEY` | non | Analyse des réponses orales par l'API Google Gemini |

La clé est lue **uniquement côté serveur**, dans la fonction `api/oral.ts` : elle
n'est jamais envoyée au navigateur ni incluse dans le bundle. Sans elle, l'app
reste pleinement fonctionnelle et bascule sur une notation locale par mots-clés.

Voir `.env.example` pour un usage local avec `vercel dev`.

## Sécurité

- Content Security Policy stricte déclarée dans `index.html`
  (`default-src 'self'`, pas de script tiers, pas de `frame-ancestors`)
- En-têtes de sécurité ajoutés par `vercel.json` (HSTS, `nosniff`, `X-Frame-Options`,
  `Permissions-Policy` limitant l'accès au micro à l'origine elle-même)
- Aucun `innerHTML` dans le code : le DOM est construit via un helper qui n'écrit
  que du texte, ce qui écarte les injections XSS par construction
- Le corps des requêtes de l'API est validé et borné en taille avant traitement

## Structure

```
api/          fonction serverless (analyse orale)
public/       icônes de l'application
src/data/     contenu embarqué (JSON) et types
src/features/ écrans : accueil, livret, questions, oral, bilan
src/lib/      helpers DOM, routeur, animations, visuels génératifs
src/state/    progression locale et calculs de maîtrise
src/styles/   tokens, animations, enveloppe, composants
tests/        tests unitaires et test de rendu de chaque écran
```

## Interface

L'app est pensée en sombre, avec un fond animé et des illustrations de thème
**générées en SVG** (aucune image externe, donc rien à télécharger et rien à
licencier). Toutes les animations sont désactivées automatiquement si le système
demande une réduction des animations (`prefers-reduced-motion`).
