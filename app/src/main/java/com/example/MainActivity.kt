package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.data.CivicRepository
import com.example.ui.components.AiTutorDialog
import com.example.ui.components.AppHeader
import com.example.ui.components.BottomFloatingDock
import com.example.ui.components.ExerciseDetailDialog
import com.example.ui.components.Guide707Dialog
import com.example.ui.components.LivretDuCitoyenDialog
import com.example.ui.components.OfficialPdfDialog
import com.example.ui.components.PillarDetailDialog
import com.example.ui.components.ProfileDialog
import com.example.ui.components.QuizDialog
import com.example.ui.screens.BilanScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.LivretScreen
import com.example.ui.screens.OralScreen
import com.example.ui.theme.MyApplicationTheme
import com.example.ui.theme.Surface
import com.example.viewmodel.AppTab
import com.example.viewmodel.AppViewModel

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    try {
      enableEdgeToEdge()
    } catch (e: Throwable) {
      android.util.Log.w("MainActivity", "Failed to enable edge-to-edge", e)
    }
    setContent {
      MyApplicationTheme {
        CivicApp()
      }
    }
  }
}

@Composable
fun CivicApp(
  viewModel: AppViewModel = viewModel()
) {
  val uiState by viewModel.uiState.collectAsState()

  val headerTitle = when (uiState.currentTab) {
    AppTab.ACCUEIL -> "Accueil"
    AppTab.LIVRET -> "Thématiques"
    AppTab.ORAL -> "Entretien En Direct"
    AppTab.BILAN -> "Bilan Et Progrès"
  }

  val showBackButton = uiState.currentTab == AppTab.ORAL || uiState.currentTab == AppTab.BILAN

  Scaffold(
    contentWindowInsets = WindowInsets.safeDrawing,
    topBar = {
      AppHeader(
        title = headerTitle,
        avatarUrl = uiState.profile.avatarUrl,
        logoUrl = uiState.profile.rfLogoUrl,
        showBackButton = showBackButton,
        onBackClick = { viewModel.selectTab(AppTab.ACCUEIL) },
        onAvatarClick = { viewModel.openProfile(true) }
      )
    },
    bottomBar = {
      BottomFloatingDock(
        currentTab = uiState.currentTab,
        onTabSelected = { viewModel.selectTab(it) }
      )
    },
    modifier = Modifier
      .fillMaxSize()
      .background(Surface)
  ) { innerPadding ->
    Box(
      modifier = Modifier
        .fillMaxSize()
        .padding(innerPadding)
    ) {
      AnimatedContent(
        targetState = uiState.currentTab,
        transitionSpec = { fadeIn() togetherWith fadeOut() },
        label = "tabTransition"
      ) { tab ->
        when (tab) {
          AppTab.ACCUEIL -> {
            HomeScreen(
              uiState = uiState,
              onToggleDay = { isToday -> viewModel.toggleDay(isToday) },
              onNavigateTab = { targetTab -> viewModel.selectTab(targetTab) },
              onOpenQuiz = { title ->
                if (title == "Devise & Symboles") {
                  viewModel.openQuiz("Devise & Symboles", CivicRepository.devisesQuiz)
                } else {
                  viewModel.openQuiz("Laïcité en mairie", CivicRepository.piegeMairieQuiz)
                }
              },
              onOpenSchedule = { viewModel.openScheduleSimulation(true) },
              onOpenGuide707 = { viewModel.openGuide707(true) }
            )
          }
          AppTab.LIVRET -> {
            LivretScreen(
              uiState = uiState,
              onPillarClick = { pillar -> viewModel.openPillarDetail(pillar) },
              onPillarAudioToggle = { pillar -> viewModel.togglePillarAudio(pillar) },
              onRevisionSheetClick = { sheet ->
                if (sheet.isQuiz) {
                  viewModel.openQuiz("Questions pièges", CivicRepository.piegeMairieQuiz)
                } else {
                  viewModel.openOfficialPdf(true)
                }
              },
              onOpenAiTutor = { viewModel.openAiTutor(true) },
              onOpenPdf = { viewModel.openOfficialPdf(true) },
              onOpenGuide707 = { chapter, onlyTips ->
                if (onlyTips) {
                  if (!uiState.guideOnlyTips) viewModel.toggleGuideOnlyTips()
                }
                viewModel.openGuide707(true, chapter)
              },
              onOpenLivretViewer = { onlyDates -> viewModel.openLivretViewer(true, onlyDates) },
              onToggleTheme = { viewModel.toggleThemeMode() }
            )
          }
          AppTab.ORAL -> {
            OralScreen(
              uiState = uiState,
              viewModel = viewModel
            )
          }
          AppTab.BILAN -> {
            BilanScreen(
              uiState = uiState,
              onNavigateTab = { targetTab -> viewModel.selectTab(targetTab) },
              onExerciseClick = { exercise -> viewModel.openExerciseDetail(exercise) },
              onOpenAiTutor = { viewModel.openAiTutor(true) },
              onOpenHumanSupport = { viewModel.openAiTutor(true) }
            )
          }
        }
      }
    }
  }

  // Active Modals & Dialogs
  if (uiState.activeQuiz != null) {
    QuizDialog(uiState = uiState, viewModel = viewModel)
  }

  if (uiState.activePillarDetail != null) {
    PillarDetailDialog(
      pillar = uiState.activePillarDetail,
      isAudioPlaying = uiState.isPillarAudioPlaying,
      onAudioToggle = {
        uiState.activePillarDetail?.let { viewModel.togglePillarAudio(it) }
      },
      onDismiss = { viewModel.closePillarDetail() }
    )
  }

  if (uiState.activeExerciseDetail != null) {
    ExerciseDetailDialog(
      exercise = uiState.activeExerciseDetail,
      onDismiss = { viewModel.closeExerciseDetail() }
    )
  }

  if (uiState.isProfileDialogOpen) {
    ProfileDialog(
      profile = uiState.profile,
      themeMode = uiState.themeMode,
      onSelectThemeMode = { mode -> viewModel.setThemeMode(mode) },
      onDismiss = { viewModel.openProfile(false) }
    )
  }

  if (uiState.isAiTutorOpen) {
    AiTutorDialog(
      onDismiss = { viewModel.openAiTutor(false) }
    )
  }

  if (uiState.isOfficialPdfOpen) {
    OfficialPdfDialog(
      profile = uiState.profile,
      onDismiss = { viewModel.openOfficialPdf(false) }
    )
  }

  if (uiState.isScheduleSimulationOpen) {
    ExerciseDetailDialog(
      exercise = CivicRepository.recommendedExercises.last(),
      onDismiss = { viewModel.openScheduleSimulation(false) }
    )
  }

  if (uiState.isGuide707Open) {
    Guide707Dialog(
      uiState = uiState,
      viewModel = viewModel,
      onDismiss = { viewModel.openGuide707(false) }
    )
  }

  if (uiState.isLivretViewerOpen) {
    LivretDuCitoyenDialog(
      uiState = uiState,
      viewModel = viewModel,
      onDismiss = { viewModel.openLivretViewer(false) }
    )
  }
}

