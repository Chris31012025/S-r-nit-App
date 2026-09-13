package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.FactCheck
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.SettingsBrightness
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil.compose.AsyncImage
import com.example.data.CandidateProfile
import com.example.data.CivicPillar
import com.example.data.RecommendedExercise
import com.example.ui.theme.OnPrimary
import com.example.ui.theme.OnSurface
import com.example.ui.theme.OnSurfaceVariant
import com.example.ui.theme.Primary
import com.example.ui.theme.PrimaryFixed
import com.example.ui.theme.Secondary
import com.example.ui.theme.SurfaceContainer
import com.example.ui.theme.SurfaceContainerHigh
import com.example.ui.theme.SurfaceContainerLow
import com.example.ui.theme.SurfaceContainerLowest
import com.example.ui.theme.Tertiary
import com.example.ui.theme.TertiaryContainer
import com.example.ui.theme.TertiaryFixed
import com.example.viewmodel.AppThemeMode
import com.example.viewmodel.AppUiState
import com.example.viewmodel.AppViewModel

@Composable
fun QuizDialog(
    uiState: AppUiState,
    viewModel: AppViewModel
) {
    val quiz = uiState.activeQuiz ?: return

    Dialog(
        onDismissRequest = { viewModel.closeQuiz() },
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth(0.92f)
                .fillMaxHeight(0.85f),
            shape = RoundedCornerShape(24.dp),
            color = SurfaceContainerLowest
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                // Header
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = uiState.activeQuizTitle,
                            style = MaterialTheme.typography.titleLarge,
                            color = Primary
                        )
                        if (!uiState.isQuizFinished) {
                            Text(
                                text = "Question ${uiState.activeQuizCurrentQuestion + 1} sur ${quiz.size}",
                                style = MaterialTheme.typography.labelSmall,
                                color = OnSurfaceVariant
                            )
                        }
                    }

                    IconButton(
                        onClick = { viewModel.closeQuiz() },
                        modifier = Modifier.testTag("btn_close_quiz")
                    ) {
                        Icon(Icons.Default.Close, contentDescription = "Fermer", tint = OnSurfaceVariant)
                    }
                }

                if (uiState.isQuizFinished) {
                    // Finished Screen
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(vertical = 20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .size(72.dp)
                                .clip(CircleShape)
                                .background(PrimaryFixed),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.FactCheck,
                                contentDescription = null,
                                tint = Primary,
                                modifier = Modifier.size(36.dp)
                            )
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "Entraînement validé !",
                            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                            color = Primary
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Score : ${uiState.activeQuizScore} / ${quiz.size}",
                            style = MaterialTheme.typography.titleLarge,
                            color = Secondary
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Bravo pour cette consolidation. Ces concepts sont fréquemment abordés lors de l'entretien préfectoral.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = OnSurfaceVariant,
                            modifier = Modifier.padding(horizontal = 24.dp),
                            lineHeight = 20.sp
                        )
                    }

                    Button(
                        onClick = { viewModel.closeQuiz() },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .testTag("btn_finish_quiz"),
                        shape = RoundedCornerShape(9999.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Primary)
                    ) {
                        Text("Terminer", style = MaterialTheme.typography.labelLarge)
                    }
                } else {
                    val currentQ = quiz[uiState.activeQuizCurrentQuestion]
                    val scroll = rememberScrollState()

                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .verticalScroll(scroll)
                            .padding(vertical = 14.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Text(
                            text = currentQ.question,
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
                            color = OnSurface,
                            lineHeight = 22.sp
                        )

                        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                            currentQ.options.forEachIndexed { index, option ->
                                val isSelected = uiState.activeQuizSelectedOption == index
                                Surface(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clip(RoundedCornerShape(14.dp))
                                        .clickable { viewModel.selectQuizOption(index) }
                                        .border(
                                            width = if (isSelected) 2.dp else 1.dp,
                                            color = if (isSelected) Primary else SurfaceContainerHigh,
                                            shape = RoundedCornerShape(14.dp)
                                        )
                                        .testTag("quiz_option_$index"),
                                    color = if (isSelected) PrimaryFixed.copy(alpha = 0.5f) else SurfaceContainerLow
                                ) {
                                    Row(
                                        modifier = Modifier.padding(14.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .size(24.dp)
                                                .clip(CircleShape)
                                                .background(if (isSelected) Primary else SurfaceContainerLowest),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            if (isSelected) {
                                                Icon(
                                                    imageVector = Icons.Default.Check,
                                                    contentDescription = null,
                                                    tint = OnPrimary,
                                                    modifier = Modifier.size(16.dp)
                                                )
                                            }
                                        }

                                        Text(
                                            text = option,
                                            style = MaterialTheme.typography.bodyMedium,
                                            color = OnSurface,
                                            modifier = Modifier.weight(1f),
                                            lineHeight = 20.sp
                                        )
                                    }
                                }
                            }
                        }
                    }

                    Button(
                        onClick = { viewModel.validateQuizAnswer() },
                        enabled = uiState.activeQuizSelectedOption != null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .testTag("btn_validate_quiz_answer"),
                        shape = RoundedCornerShape(9999.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Primary)
                    ) {
                        Text(
                            text = if (uiState.activeQuizCurrentQuestion < quiz.lastIndex) "Valider la réponse" else "Voir mon score",
                            style = MaterialTheme.typography.labelLarge
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun PillarDetailDialog(
    pillar: CivicPillar?,
    isAudioPlaying: Boolean,
    onAudioToggle: () -> Unit,
    onDismiss: () -> Unit
) {
    if (pillar == null) return

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth(0.92f)
                .fillMaxHeight(0.85f),
            shape = RoundedCornerShape(24.dp),
            color = SurfaceContainerLowest
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                // Header
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = pillar.title,
                            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                            color = Primary
                        )
                        Text(
                            text = pillar.subtitle,
                            style = MaterialTheme.typography.labelSmall,
                            color = OnSurfaceVariant
                        )
                    }
                    IconButton(onClick = onDismiss) {
                        Icon(Icons.Default.Close, contentDescription = "Fermer", tint = OnSurfaceVariant)
                    }
                }

                val scroll = rememberScrollState()
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .verticalScroll(scroll)
                        .padding(vertical = 12.dp),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    Text(
                        text = pillar.description,
                        style = MaterialTheme.typography.bodyMedium,
                        color = OnSurface
                    )

                    // Audio Card
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = SurfaceContainerLow)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(14.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = "Synthèse vocale du pilier",
                                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                                    color = Primary
                                )
                                Text(
                                    text = if (isAudioPlaying) "Lecture en cours..." else "Écouter l'essentiel en 1 minute",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = OnSurfaceVariant
                                )
                            }

                            Button(
                                onClick = onAudioToggle,
                                shape = CircleShape,
                                colors = ButtonDefaults.buttonColors(containerColor = Primary),
                                modifier = Modifier.size(42.dp)
                            ) {
                                Icon(
                                    imageVector = if (isAudioPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                                    contentDescription = null,
                                    tint = OnPrimary,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                        }
                    }

                    Text(
                        text = "Points clés à retenir pour l'entretien :",
                        style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.SemiBold),
                        color = OnSurface
                    )

                    pillar.keyFacts.forEach { fact ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            verticalAlignment = Alignment.Top
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(20.dp)
                                    .clip(CircleShape)
                                    .background(PrimaryFixed),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = null,
                                    tint = Primary,
                                    modifier = Modifier.size(13.dp)
                                )
                            }
                            Text(
                                text = fact,
                                style = MaterialTheme.typography.bodyMedium.copy(lineHeight = 20.sp),
                                color = OnSurface,
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                }

                Button(
                    onClick = onDismiss,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    shape = RoundedCornerShape(9999.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Primary)
                ) {
                    Text("J'ai compris", style = MaterialTheme.typography.labelLarge)
                }
            }
        }
    }
}

@Composable
fun ExerciseDetailDialog(
    exercise: RecommendedExercise?,
    onDismiss: () -> Unit
) {
    if (exercise == null) return

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth(0.92f)
                .fillMaxHeight(0.65f),
            shape = RoundedCornerShape(24.dp),
            color = SurfaceContainerLowest
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Surface(
                        shape = RoundedCornerShape(9999.dp),
                        color = PrimaryFixed
                    ) {
                        Text(
                            text = exercise.tag,
                            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                            color = Primary,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                        )
                    }

                    IconButton(onClick = onDismiss) {
                        Icon(Icons.Default.Close, contentDescription = "Fermer", tint = OnSurfaceVariant)
                    }
                }

                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text(
                        text = exercise.title,
                        style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                        color = Primary
                    )
                    Text(
                        text = "Durée estimée : ${exercise.durationText}",
                        style = MaterialTheme.typography.labelMedium,
                        color = Secondary
                    )
                    Text(
                        text = exercise.summary,
                        style = MaterialTheme.typography.bodyMedium.copy(lineHeight = 22.sp),
                        color = OnSurface
                    )
                }

                Button(
                    onClick = onDismiss,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    shape = RoundedCornerShape(9999.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Primary)
                ) {
                    Text("Lancer l'exercice", style = MaterialTheme.typography.labelLarge)
                }
            }
        }
    }
}

@Composable
fun ProfileDialog(
    profile: CandidateProfile,
    themeMode: AppThemeMode = AppThemeMode.LIGHT,
    onSelectThemeMode: (AppThemeMode) -> Unit = {},
    onDismiss: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth(0.92f)
                .fillMaxHeight(0.85f),
            shape = RoundedCornerShape(24.dp),
            color = SurfaceContainerLowest
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Dossier Candidat",
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                        color = Primary
                    )
                    IconButton(onClick = onDismiss) {
                        Icon(Icons.Default.Close, contentDescription = "Fermer", tint = OnSurfaceVariant)
                    }
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    AsyncImage(
                        model = profile.avatarUrl,
                        contentDescription = "Avatar",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(72.dp)
                            .clip(CircleShape)
                            .border(2.dp, Primary, CircleShape)
                    )

                    Text(
                        text = profile.name,
                        style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                        color = OnSurface
                    )
                    Text(
                        text = profile.prefecture,
                        style = MaterialTheme.typography.bodyMedium,
                        color = OnSurfaceVariant
                    )
                    Text(
                        text = profile.procedure,
                        style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.SemiBold),
                        color = Secondary
                    )
                }

                Surface(
                    shape = RoundedCornerShape(16.dp),
                    color = SurfaceContainerLow
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp),
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("Série actuelle :", style = MaterialTheme.typography.bodySmall, color = OnSurfaceVariant)
                            Text("${profile.streakDays} jours consécutifs", style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold), color = Primary)
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("Fiches assimilées :", style = MaterialTheme.typography.bodySmall, color = OnSurfaceVariant)
                            Text("${profile.masteredSheets} / ${profile.totalSheets}", style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold), color = Primary)
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("Maîtrise orale :", style = MaterialTheme.typography.bodySmall, color = OnSurfaceVariant)
                            Text("${profile.preparationScore} / 100", style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold), color = Secondary)
                        }
                    }
                }

                // THEME SELECTOR CARD (Mode Clair / Mode Sombre pour lecture confortable)
                Surface(
                    shape = RoundedCornerShape(16.dp),
                    color = SurfaceContainerLow,
                    border = androidx.compose.foundation.BorderStroke(1.dp, SurfaceContainerHigh)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Text(
                            text = "CONFORT DE LECTURE DU LIVRET",
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 1.sp
                            ),
                            color = Primary
                        )

                        Text(
                            text = "Basculez entre les modes clair et sombre pour soulager la fatigue oculaire lors des séances de révision.",
                            style = MaterialTheme.typography.bodySmall,
                            color = OnSurfaceVariant
                        )

                        // 3 Option Selector
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            val options = listOf(
                                Triple(AppThemeMode.LIGHT, "Clair", Icons.Default.LightMode),
                                Triple(AppThemeMode.DARK, "Sombre", Icons.Default.DarkMode),
                                Triple(AppThemeMode.SYSTEM, "Système", Icons.Default.SettingsBrightness)
                            )

                            options.forEach { (mode, label, icon) ->
                                val isSelected = themeMode == mode
                                Surface(
                                    shape = RoundedCornerShape(12.dp),
                                    color = if (isSelected) Primary else SurfaceContainerLowest,
                                    border = androidx.compose.foundation.BorderStroke(
                                        1.dp,
                                        if (isSelected) Primary else SurfaceContainerHigh
                                    ),
                                    modifier = Modifier
                                        .weight(1f)
                                        .height(52.dp)
                                        .clip(RoundedCornerShape(12.dp))
                                        .clickable { onSelectThemeMode(mode) }
                                        .testTag("theme_selector_${mode.name.lowercase()}")
                                ) {
                                    Column(
                                        modifier = Modifier.fillMaxSize(),
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.Center
                                    ) {
                                        Icon(
                                            imageVector = icon,
                                            contentDescription = label,
                                            tint = if (isSelected) OnPrimary else OnSurfaceVariant,
                                            modifier = Modifier.size(18.dp)
                                        )
                                        Spacer(modifier = Modifier.height(2.dp))
                                        Text(
                                            text = label,
                                            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                                            color = if (isSelected) OnPrimary else OnSurface
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                Button(
                    onClick = onDismiss,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    shape = RoundedCornerShape(9999.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Primary)
                ) {
                    Text("Fermer", style = MaterialTheme.typography.labelLarge)
                }
            }
        }
    }
}

data class ChatMessage(
    val isUser: Boolean,
    val text: String
)

@Composable
fun AiTutorDialog(
    onDismiss: () -> Unit
) {
    val messages = remember {
        mutableStateListOf(
            ChatMessage(
                isUser = false,
                text = "Bonjour ! Je suis votre tuteur civique républicain. Posez-moi vos questions sur le Livret du Citoyen, les grandes dates de France ou le déroulement de votre entretien en préfecture."
            )
        )
    }
    var inputText by remember { mutableStateOf("") }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth(0.94f)
                .fillMaxHeight(0.85f),
            shape = RoundedCornerShape(24.dp),
            color = SurfaceContainerLowest
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(18.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                // Header
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .clip(CircleShape)
                                .background(PrimaryFixed),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Psychology,
                                contentDescription = null,
                                tint = Primary,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                        Column {
                            Text(
                                text = "Tuteur Civique IA",
                                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                                color = Primary
                            )
                            Text(
                                text = "Conseils républicains & bienveillants",
                                style = MaterialTheme.typography.labelSmall,
                                color = OnSurfaceVariant
                            )
                        }
                    }

                    IconButton(onClick = onDismiss) {
                        Icon(Icons.Default.Close, contentDescription = "Fermer", tint = OnSurfaceVariant)
                    }
                }

                // Chat stream
                val scroll = rememberScrollState()
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .verticalScroll(scroll)
                        .padding(vertical = 12.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    messages.forEach { msg ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = if (msg.isUser) Arrangement.End else Arrangement.Start
                        ) {
                            Surface(
                                shape = RoundedCornerShape(16.dp),
                                color = if (msg.isUser) Primary else SurfaceContainerLow,
                                modifier = Modifier.widthIn(max = 280.dp)
                            ) {
                                Text(
                                    text = msg.text,
                                    style = MaterialTheme.typography.bodyMedium.copy(lineHeight = 20.sp),
                                    color = if (msg.isUser) OnPrimary else OnSurface,
                                    modifier = Modifier.padding(12.dp)
                                )
                            }
                        }
                    }
                }

                // Input bar
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedTextField(
                        value = inputText,
                        onValueChange = { inputText = it },
                        modifier = Modifier
                            .weight(1f)
                            .testTag("input_ai_tutor"),
                        placeholder = { Text("Posez votre question...") },
                        shape = RoundedCornerShape(9999.dp)
                    )

                    IconButton(
                        onClick = {
                            if (inputText.isNotBlank()) {
                                val query = inputText.trim()
                                messages.add(ChatMessage(isUser = true, text = query))
                                inputText = ""
                                // Smart civic canned response based on keywords
                                val reply = when {
                                    query.contains("laïcité", ignoreCase = true) ->
                                        "La laïcité garantit la neutralité religieuse de l'État et la liberté absolue de conscience (croire ou ne pas croire). Elle assure que tous les citoyens sont traités de manière strictement égale dans les services publics."
                                    query.contains("1789", ignoreCase = true) || query.contains("bastille", ignoreCase = true) ->
                                        "La prise de la Bastille le 14 juillet 1789 marque l'effondrement de l'Ancien Régime. Le 14 juillet est notre fête nationale car il célèbre également la Fête de la Fédération du 14 juillet 1790, symbole de l'union républicaine."
                                    query.contains("marianne", ignoreCase = true) ->
                                        "Marianne incarne la République et la liberté. Coiffée du bonnet phrygien, son buste veille sur les mairies de France et symbolise la souveraineté citoyenne."
                                    else ->
                                        "Très bonne question pour votre préparation ! Retenez que l'entretien vise à apprécier votre niveau de langue et votre adhésion sincère aux valeurs républicaines de Liberté, Égalité et Fraternité. Donnez toujours des exemples concrets tirés de votre quotidien."
                                }
                                messages.add(ChatMessage(isUser = false, text = reply))
                            }
                        },
                        modifier = Modifier
                            .size(46.dp)
                            .clip(CircleShape)
                            .background(Primary)
                            .testTag("btn_send_ai_tutor")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Send,
                            contentDescription = "Envoyer",
                            tint = OnPrimary,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun OfficialPdfDialog(
    profile: CandidateProfile,
    onDismiss: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth(0.94f)
                .fillMaxHeight(0.88f),
            shape = RoundedCornerShape(24.dp),
            color = SurfaceContainerLowest
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "Livret du Citoyen Officiel",
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                            color = Primary
                        )
                        Text(
                            text = "Ministère de l'Intérieur - République Française",
                            style = MaterialTheme.typography.labelSmall,
                            color = OnSurfaceVariant
                        )
                    }
                    IconButton(onClick = onDismiss) {
                        Icon(Icons.Default.Close, contentDescription = "Fermer", tint = OnSurfaceVariant)
                    }
                }

                // Dynamic HTML with image links as explicitly requested by user prompt!
                val htmlDoc = """
                    <span class="badge">RÉPUBLIQUE FRANÇAISE</span>
                    <h2>Les Principes Fondamentaux de la République</h2>
                    <p>La France est une République indivisible, laïque, démocratique et sociale. Elle assure l'égalité devant la loi de tous les citoyens sans distinction d'origine, de race ou de religion.</p>
                    
                    <img src="${profile.marianneUrl}" alt="Marianne allégorie républicaine" class="hero-img" />
                    
                    <div class="quote-box">
                        « La devise de la République est "Liberté, Égalité, Fraternité". Son principe est : gouvernement du peuple, par le peuple et pour le peuple. »
                        <br><strong>Article 2 de la Constitution de 1958</strong>
                    </div>

                    <h3>Les Symboles Nationaux</h3>
                    <p>L'emblème national est le drapeau tricolore bleu, blanc, rouge. L'hymne national est La Marseillaise, écrite par Rouget de Lisle en 1792.</p>
                    
                    <img src="${profile.documentsUrl}" alt="Charte des droits et devoirs du citoyen" class="hero-img" />

                    <h3>La Laïcité Républicaine</h3>
                    <p>La laïcité repose sur trois principes indissociables : la liberté de conscience, la séparation des institutions publiques et des organisations religieuses, et l'égalité de tous devant la loi.</p>
                """.trimIndent()

                CivicHtmlViewer(
                    htmlContent = htmlDoc,
                    modifier = Modifier
                        .weight(1f)
                        .padding(vertical = 8.dp)
                )

                Button(
                    onClick = onDismiss,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    shape = RoundedCornerShape(9999.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Primary)
                ) {
                    Text("Fermer le document", style = MaterialTheme.typography.labelLarge)
                }
            }
        }
    }
}
