import { oralQuestions } from "../../data";
import { requestOralScore } from "../../lib/api";
import { el } from "../../lib/dom";
import { renderShell } from "../../lib/shell";
import { loadProgress, recordOralAttempt, saveProgress } from "../../state/progress";
import { isSpeechRecognitionSupported, startDictation } from "./speech";

export function renderOral(root: HTMLElement, params: URLSearchParams): void {
  const questionIdParam = params.get("q");
  const question =
    (questionIdParam ? oralQuestions.find((q) => q.id === Number.parseInt(questionIdParam, 10)) : null) ??
    oralQuestions[0];

  const content = renderShell(root, "oral", "Simulation orale", "Entraînez-vous à répondre à voix haute");

  if (!question) {
    content.append(el("div", { className: "empty-state" }, ["Aucune question disponible."]));
    return;
  }

  const textarea = el("textarea", { rows: "5", placeholder: "Rédigez ou dictez votre réponse ici…" });
  const resultBox = el("div", {});
  let stopDictation: (() => void) | null = null;

  const dictateButton = el(
    "button",
    {
      className: "button-secondary",
      onclick: () => {
        if (stopDictation) {
          stopDictation();
          stopDictation = null;
          dictateButton.textContent = "🎙️ Dicter ma réponse";
          return;
        }
        dictateButton.textContent = "⏹️ Arrêter";
        stopDictation = startDictation(
          (text) => {
            textarea.value = text;
          },
          () => {
            dictateButton.textContent = "🎙️ Dicter ma réponse";
            stopDictation = null;
          }
        );
      }
    },
    ["🎙️ Dicter ma réponse"]
  );

  if (!isSpeechRecognitionSupported()) {
    dictateButton.setAttribute("disabled", "true");
    dictateButton.title = "Dictée vocale non disponible sur cet appareil";
  }

  const submitButton = el(
    "button",
    {
      className: "button-primary",
      onclick: async () => {
        const userAnswer = textarea.value.trim();
        if (!userAnswer) return;
        submitButton.setAttribute("disabled", "true");
        submitButton.textContent = "Analyse en cours…";

        const result = await requestOralScore({
          questionId: question.id,
          question: question.questionText,
          sampleAnswer: question.sampleAnswer,
          keywords: question.keywords,
          userAnswer
        });

        saveProgress(
          recordOralAttempt(loadProgress(), {
            questionId: question.id,
            score: result.score,
            timestamp: new Date().toISOString()
          })
        );

        resultBox.replaceChildren(
          el("div", { className: "card" }, [
            el("div", { className: "meta-row" }, [
              el("h2", {}, ["Résultat"]),
              el("span", { className: "tag" }, [`${result.score}/100`])
            ]),
            el("p", {}, [result.feedback]),
            el(
              "div",
              {},
              question.keywords.map((keyword) =>
                el(
                  "span",
                  { className: `keyword-chip${result.matchedKeywords.includes(keyword) ? " hit" : ""}` },
                  [keyword]
                )
              )
            )
          ])
        );

        submitButton.removeAttribute("disabled");
        submitButton.textContent = "Analyser ma réponse";
      }
    },
    ["Analyser ma réponse"]
  );

  const nextButton = el(
    "a",
    {
      href: `#/oral?q=${oralQuestions[(oralQuestions.indexOf(question) + 1) % oralQuestions.length].id}`,
      className: "button-secondary"
    },
    ["Question suivante →"]
  );

  content.append(
    el("div", { className: "card" }, [
      el("span", { className: "tag" }, [question.indexText]),
      el("h2", {}, [question.questionText]),
      el("p", { style: "font-style:italic" }, [question.instructorAdvice])
    ]),
    el("div", { className: "field" }, [
      el("label", {}, ["Votre réponse"]),
      textarea,
      el("div", { className: "meta-row" }, [dictateButton, submitButton])
    ]),
    resultBox,
    nextButton
  );
}
