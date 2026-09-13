package com.example.viewmodel

import android.app.Application
import android.speech.tts.TextToSpeech
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.CandidateProfile
import com.example.data.CivicChapter
import com.example.data.CivicPillar
import com.example.data.CivicRepository
import com.example.data.GuideQuestion
import com.example.data.HistoricDateMnemonic
import com.example.data.HistoricPeriod
import com.example.data.LivretCategory
import com.example.data.OralQuestion
import com.example.data.QuizQuestion
import com.example.data.RecommendedExercise
import com.example.data.RevisionSheet
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.util.Locale

enum class AppTab(val title: String, val route: String) {
    ACCUEIL("Accueil", "accueil"),
    LIVRET("Livret", "thematiques"),
    ORAL("Oral IA", "simulation-orale"),
    BILAN("Bilan", "bilan-et-progres")
}

enum class AppThemeMode(val label: String) {
    LIGHT("Mode Clair"),
    DARK("Mode Sombre"),
    SYSTEM("Système")
}

data class AppUiState(
    val currentTab: AppTab = AppTab.ACCUEIL,
    val themeMode: AppThemeMode = AppThemeMode.LIGHT,
    val profile: CandidateProfile = CivicRepository.defaultProfile,
    val selectedDayIsToday: Boolean = true,
    // Audio synthesis in pillars
    val activePlayingPillarId: String? = null,
    val isPillarAudioPlaying: Boolean = false,
    // Oral IA Simulation State
    val oralQuestionIndex: Int = 2, // Question 03 sur 08 (index 2) by default like mock
    val oralTimerSeconds: Int = 257, // 04:17
    val isRecording: Boolean = true,
    val isTimerRunning: Boolean = true,
    val transcriptionText: String = "Je souhaite devenir française car je partage pleinement les valeurs républicaines, notamment l'égalité des chances et la laïcité qui me permettent de m'épanouir au quotidien...",
    val isAdviceExpanded: Boolean = false,
    val elocutionStatus: String = "Fluide & posée",
    // Dialogs / Modals
    val activeQuiz: List<QuizQuestion>? = null,
    val activeQuizTitle: String = "",
    val activeQuizCurrentQuestion: Int = 0,
    val activeQuizSelectedOption: Int? = null,
    val activeQuizScore: Int = 0,
    val isQuizFinished: Boolean = false,
    val activePillarDetail: CivicPillar? = null,
    val activeExerciseDetail: RecommendedExercise? = null,
    val isProfileDialogOpen: Boolean = false,
    val isAiTutorOpen: Boolean = false,
    val isOfficialPdfOpen: Boolean = false,
    val isScheduleSimulationOpen: Boolean = false,
    val ttsReady: Boolean = false,
    // Guide 707 Questions State
    val isGuide707Open: Boolean = false,
    val guideSelectedChapter: CivicChapter? = null,
    val guideSearchQuery: String = "",
    val guideOnlyTips: Boolean = false,
    val guideOnlyCrucial: Boolean = false,
    val masteredQuestionIds: Set<Int> = setOf(1, 3, 4, 116, 171, 195, 206, 218, 226, 244, 403, 533),
    val isSpeakingQuestionId: Int? = null,
    // Livret du Citoyen Full Content & Mnemonic Dates
    val isLivretViewerOpen: Boolean = false,
    val livretSelectedCategory: LivretCategory? = null,
    val livretSelectedPeriod: HistoricPeriod? = null,
    val livretSearchQuery: String = "",
    val livretOnlyDatesMode: Boolean = false,
    val masteredDateIds: Set<Int> = setOf(1, 4, 7, 13, 15),
    val speakingDateId: Int? = null
)

class AppViewModel(application: Application) : AndroidViewModel(application) {

    private val _uiState = MutableStateFlow(AppUiState())
    val uiState: StateFlow<AppUiState> = _uiState.asStateFlow()

    private var timerJob: Job? = null
    private var tts: TextToSpeech? = null

    init {
        startTimer()
        initTts(application)
    }

    private fun initTts(app: Application) {
        try {
            tts = TextToSpeech(app) { status ->
                try {
                    if (status == TextToSpeech.SUCCESS) {
                        tts?.language = Locale.FRENCH
                        _uiState.update { it.copy(ttsReady = true) }
                    }
                } catch (e: Throwable) {
                    android.util.Log.w("AppViewModel", "TTS language init error", e)
                }
            }
        } catch (e: Throwable) {
            android.util.Log.w("AppViewModel", "TTS service unavailable", e)
            tts = null
        }
    }

    private fun startTimer() {
        timerJob?.cancel()
        timerJob = viewModelScope.launch {
            while (isActive) {
                delay(1000)
                if (_uiState.value.currentTab == AppTab.ORAL && _uiState.value.isTimerRunning && _uiState.value.isRecording) {
                    _uiState.update { it.copy(oralTimerSeconds = it.oralTimerSeconds + 1) }
                }
            }
        }
    }

    fun selectTab(tab: AppTab) {
        _uiState.update { it.copy(currentTab = tab) }
    }

    fun toggleDay(isToday: Boolean) {
        _uiState.update { it.copy(selectedDayIsToday = isToday) }
    }

    fun toggleAdviceExpanded() {
        _uiState.update { it.copy(isAdviceExpanded = !it.isAdviceExpanded) }
    }

    fun toggleRecording() {
        val newState = !_uiState.value.isRecording
        _uiState.update { it.copy(isRecording = newState, isTimerRunning = newState) }
    }

    fun pauseRecording() {
        _uiState.update { it.copy(isRecording = false, isTimerRunning = false) }
    }

    fun resetAnswer() {
        val currentQ = currentOralQuestion()
        _uiState.update {
            it.copy(
                transcriptionText = "",
                oralTimerSeconds = 0,
                isRecording = true,
                isTimerRunning = true
            )
        }
    }

    fun restoreSampleAnswer() {
        val currentQ = currentOralQuestion()
        _uiState.update {
            it.copy(
                transcriptionText = currentQ.sampleAnswer,
                isRecording = true
            )
        }
    }

    fun nextOralQuestion() {
        val currentIdx = _uiState.value.oralQuestionIndex
        val nextIdx = (currentIdx + 1) % CivicRepository.oralQuestions.size
        val nextQ = CivicRepository.oralQuestions[nextIdx]
        _uiState.update {
            it.copy(
                oralQuestionIndex = nextIdx,
                transcriptionText = nextQ.sampleAnswer,
                isRecording = true,
                isTimerRunning = true,
                isAdviceExpanded = false
            )
        }
        speakText(nextQ.questionText)
    }

    fun speakCurrentQuestion() {
        val q = currentOralQuestion()
        speakText(q.questionText)
    }

    fun speakText(text: String) {
        try {
            tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, "civic_tts")
        } catch (_: Exception) {}
    }

    fun stopSpeaking() {
        try {
            tts?.stop()
        } catch (_: Exception) {}
        _uiState.update { it.copy(isSpeakingQuestionId = null, speakingDateId = null) }
    }

    fun togglePillarAudio(pillar: CivicPillar) {
        val currentlyPlaying = _uiState.value.activePlayingPillarId == pillar.id && _uiState.value.isPillarAudioPlaying
        if (currentlyPlaying) {
            stopSpeaking()
            _uiState.update { it.copy(activePlayingPillarId = null, isPillarAudioPlaying = false) }
        } else {
            speakText(pillar.audioTranscript)
            _uiState.update { it.copy(activePlayingPillarId = pillar.id, isPillarAudioPlaying = true) }
        }
    }

    fun currentOralQuestion(): OralQuestion {
        val idx = _uiState.value.oralQuestionIndex.coerceIn(0, CivicRepository.oralQuestions.lastIndex)
        return CivicRepository.oralQuestions[idx]
    }

    fun openQuiz(title: String, questions: List<QuizQuestion>) {
        _uiState.update {
            it.copy(
                activeQuiz = questions,
                activeQuizTitle = title,
                activeQuizCurrentQuestion = 0,
                activeQuizSelectedOption = null,
                activeQuizScore = 0,
                isQuizFinished = false
            )
        }
    }

    fun selectQuizOption(optionIdx: Int) {
        _uiState.update { it.copy(activeQuizSelectedOption = optionIdx) }
    }

    fun validateQuizAnswer() {
        val state = _uiState.value
        val quiz = state.activeQuiz ?: return
        val currentQ = quiz[state.activeQuizCurrentQuestion]
        val isCorrect = state.activeQuizSelectedOption == currentQ.correctIndex
        val newScore = if (isCorrect) state.activeQuizScore + 1 else state.activeQuizScore

        if (state.activeQuizCurrentQuestion < quiz.lastIndex) {
            _uiState.update {
                it.copy(
                    activeQuizScore = newScore,
                    activeQuizCurrentQuestion = it.activeQuizCurrentQuestion + 1,
                    activeQuizSelectedOption = null
                )
            }
        } else {
            _uiState.update {
                it.copy(
                    activeQuizScore = newScore,
                    isQuizFinished = true
                )
            }
        }
    }

    fun closeQuiz() {
        _uiState.update { it.copy(activeQuiz = null, isQuizFinished = false) }
    }

    fun openPillarDetail(pillar: CivicPillar) {
        _uiState.update { it.copy(activePillarDetail = pillar) }
    }

    fun closePillarDetail() {
        _uiState.update { it.copy(activePillarDetail = null) }
    }

    fun openExerciseDetail(exercise: RecommendedExercise) {
        _uiState.update { it.copy(activeExerciseDetail = exercise) }
    }

    fun closeExerciseDetail() {
        _uiState.update { it.copy(activeExerciseDetail = null) }
    }

    fun setThemeMode(mode: AppThemeMode) {
        _uiState.update { it.copy(themeMode = mode) }
    }

    fun toggleThemeMode() {
        _uiState.update { current ->
            val nextMode = when (current.themeMode) {
                AppThemeMode.LIGHT -> AppThemeMode.DARK
                AppThemeMode.DARK -> AppThemeMode.LIGHT
                AppThemeMode.SYSTEM -> AppThemeMode.DARK
            }
            current.copy(themeMode = nextMode)
        }
    }

    fun openProfile(open: Boolean) {
        _uiState.update { it.copy(isProfileDialogOpen = open) }
    }

    fun openAiTutor(open: Boolean) {
        _uiState.update { it.copy(isAiTutorOpen = open) }
    }

    fun openOfficialPdf(open: Boolean) {
        _uiState.update { it.copy(isOfficialPdfOpen = open) }
    }

    fun openScheduleSimulation(open: Boolean) {
        _uiState.update { it.copy(isScheduleSimulationOpen = open) }
    }

    fun updateTranscription(newText: String) {
        _uiState.update { it.copy(transcriptionText = newText) }
    }

    // Guide 707 Methods
    fun openGuide707(open: Boolean, chapter: CivicChapter? = null) {
        _uiState.update {
            it.copy(
                isGuide707Open = open,
                guideSelectedChapter = if (open) chapter ?: it.guideSelectedChapter else it.guideSelectedChapter
            )
        }
    }

    fun setGuideSelectedChapter(chapter: CivicChapter?) {
        _uiState.update { it.copy(guideSelectedChapter = chapter) }
    }

    fun setGuideSearchQuery(query: String) {
        _uiState.update { it.copy(guideSearchQuery = query) }
    }

    fun toggleGuideOnlyTips() {
        _uiState.update { it.copy(guideOnlyTips = !it.guideOnlyTips) }
    }

    fun toggleGuideOnlyCrucial() {
        _uiState.update { it.copy(guideOnlyCrucial = !it.guideOnlyCrucial) }
    }

    fun toggleQuestionMastered(id: Int) {
        _uiState.update {
            val current = it.masteredQuestionIds
            val updated = if (current.contains(id)) current - id else current + id
            it.copy(masteredQuestionIds = updated)
        }
    }

    fun speakGuideQuestion(question: GuideQuestion) {
        if (_uiState.value.isSpeakingQuestionId == question.id) {
            stopSpeaking()
            return
        }
        stopSpeaking()
        val textToSpeak = "${question.question}. Réponse : ${question.answer}" +
            if (!question.dateTip.isNullOrBlank()) ". ${question.dateTip}" else ""
        try {
            tts?.speak(textToSpeak, TextToSpeech.QUEUE_FLUSH, null, "GuideQuestion_${question.id}")
            _uiState.update { it.copy(isSpeakingQuestionId = question.id) }
        } catch (e: Throwable) {
            android.util.Log.w("AppViewModel", "TTS speak failed", e)
        }
    }

    fun openLivretViewer(open: Boolean, onlyDates: Boolean = false) {
        _uiState.update {
            it.copy(
                isLivretViewerOpen = open,
                livretOnlyDatesMode = onlyDates
            )
        }
    }

    fun setLivretCategory(category: LivretCategory?) {
        _uiState.update { it.copy(livretSelectedCategory = category) }
    }

    fun setLivretPeriod(period: HistoricPeriod?) {
        _uiState.update { it.copy(livretSelectedPeriod = period) }
    }

    fun setLivretSearchQuery(query: String) {
        _uiState.update { it.copy(livretSearchQuery = query) }
    }

    fun toggleLivretOnlyDatesMode() {
        _uiState.update { it.copy(livretOnlyDatesMode = !it.livretOnlyDatesMode) }
    }

    fun toggleDateMastered(id: Int) {
        _uiState.update {
            val current = it.masteredDateIds
            val updated = if (current.contains(id)) current - id else current + id
            it.copy(masteredDateIds = updated)
        }
    }

    fun speakHistoricDate(date: HistoricDateMnemonic) {
        if (_uiState.value.speakingDateId == date.id) {
            stopSpeaking()
            return
        }
        stopSpeaking()
        val textToSpeak = "${date.exactDate} : ${date.title}. ${date.summary}. Astuce mnémonique : ${date.mnemonicTip}"
        try {
            tts?.speak(textToSpeak, TextToSpeech.QUEUE_FLUSH, null, "DateTip_${date.id}")
            _uiState.update { it.copy(speakingDateId = date.id) }
        } catch (e: Throwable) {
            android.util.Log.w("AppViewModel", "TTS speak failed", e)
        }
    }

    override fun onCleared() {
        super.onCleared()
        timerJob?.cancel()
        try {
            tts?.stop()
            tts?.shutdown()
        } catch (e: Throwable) {
            android.util.Log.w("AppViewModel", "TTS shutdown failed", e)
        }
        tts = null
    }
}
