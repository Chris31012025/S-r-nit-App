import { oralQuestions } from "../../data";
import { requestOralScore } from "../../lib/api";
import { el } from "../../lib/dom";
import { createRing } from "../../lib/ring";
import { renderShell } from "../../lib/shell";
import { loadProgress, recordOralAttempt, saveProgress } from "../../state/progress";
import { isSpeechRecognitionSupported, startDictation } from "./speech";

const WAVE_BARS = 5;

function waveform(): HTMLElement {
  return el(
    "span",
    { className: "waveform" },
    Array.from({ length: WAVE_BARS }, (_, index) =>
      el("i", { style: `--bar-delay:${index * 110}ms` })
    )
  );
}

export function renderOral(root: HTMLElement, params: URLSearchParams): void {
  const questionIdParam = params.get("q");
  const question =
    (questionIdParam ? oralQuestions.find((q) => q.id === Number.parseInt(questionIdParam, 10)) : null) ??
    oralQuestions[0];

  const content = renderShell(root, "oral", {
    eyebrow: "Simulation",
    title: "Entretien oral",
    subtitle: "Répondez à voix haute, l'analyse arrive juste après"
  });

  if (!question) {
    content.append(el("div", { className: "empty-state" }, ["Aucune question disponible."]));
    return;
  }

  const textarea = el("textarea", { rows: "5", placeholder: "Rédigez ou dictez votre réponse ici…" });
  const resultBox = el("div", {});

  const wave = waveform();
  const dictateLabel = el("span", {}, ["Dicter"]);
  const dictateButton = el("button", { className: "button-secondary", type: "button" }, [wave, dictateLabel]);
  let stopDictation: (() => void) | null = null;
  let halo: HTMLElement | null = null;

  const setListening = (listening: boolean): void => {
    wave.classList.toggle("active", listening);
    dictateLabel.textContent = listening ? "Arrêter" : "Dicter";
    if (listening && !halo) {
      halo = el("span", { className: "mic-halo", "aria-hidden": "true" });
      dictateButton.append(halo);
    } else if (!listening && halo) {
      halo.remove();
      halo = null;
    }
  };

  dictateButton.addEventListener("click", () => {
    if (stopDictation) {
      stopDictation();
      stopDictation = null;
      setListening(false);
      return;
    }
    setListening(true);
    stopDictation = startDictation(
      (text) => {
        textarea.value = text;
      },
      () => {
        setListening(false);
        stopDictation = null;
      }
    );
  });

  if (!isSpeechRecognitionSupported()) {
    dictateButton.setAttribute("disabled", "true");
    dictateButton.title = "Dictée vocale non disponible sur cet appareil";
  }

  const submitButton = el("button", { className: "button-primary", type: "button" }, ["Analyser ma réponse"]);

  submitButton.addEventListener("click", async () => {
    const userAnswer = textarea.value.trim();
    if (!userAnswer) {
      textarea.focus();
      return;
    }

    submitButton.setAttribute("disabled", "true");
    submitButton.textContent = "Analyse en cours…";

    try {
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

      const chips = question.keywords.map((keyword) =>
        el("span", { className: "keyword-chip" }, [keyword])
      );

      resultBox.replaceChildren(
        el("div", { className: "card page-enter" }, [
          el("div", { className: "meta-row" }, [
            el("div", {}, [el("h2", {}, ["Analyse de votre réponse"]), el("p", {}, [result.feedback])]),
            createRing(result.score, { caption: "Score", size: 88, suffix: "" })
          ]),
          el("div", { style: "margin-top:14px" }, chips)
        ])
      );

      chips.forEach((chip, index) => {
        if (!result.matchedKeywords.includes(chip.textContent ?? "")) return;
        window.setTimeout(() => chip.classList.add("hit"), 220 + index * 90);
      });
    } catch (error) {
      console.error("Oral scoring failed:", error);
      resultBox.replaceChildren(
        el("div", { className: "card page-enter" }, [
          el("h2", {}, ["Analyse indisponible"]),
          el("p", {}, ["Impossible d'analyser la réponse pour le moment. Réessayez dans un instant."])
        ])
      );
    } finally {
      submitButton.removeAttribute("disabled");
      submitButton.textContent = "Analyser ma réponse";
    }
  });

  const nextQuestion = oralQuestions[(oralQuestions.indexOf(question) + 1) % oralQuestions.length];

  content.append(
    el("div", { className: "card", "data-reveal": "" }, [
      el("div", { className: "meta-row" }, [
        el("span", { className: "tag" }, [question.indexText]),
        el("span", { className: "tag" }, [`${oralQuestions.length} questions`])
      ]),
      el("h2", { style: "margin-top:12px;font-size:1.2rem;line-height:1.35" }, [question.questionText]),
      el("p", { style: "margin-top:8px;font-style:italic" }, [question.instructorAdvice])
    ]),
    el("div", { className: "field", "data-reveal": "" }, [
      el("label", {}, ["Votre réponse"]),
      textarea,
      el("div", { className: "button-row" }, [dictateButton, submitButton])
    ]),
    resultBox,
    el("a", { href: `#/oral?q=${nextQuestion.id}`, className: "button-secondary", "data-reveal": "" }, [
      "Question suivante →"
    ])
  );
}
