package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.AutoFixHigh
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.GraphicEq
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.PsychologyAlt
import androidx.compose.material.icons.filled.RestartAlt
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.ui.theme.OnPrimary
import com.example.ui.theme.OnSecondary
import com.example.ui.theme.OnSurface
import com.example.ui.theme.OnSurfaceVariant
import com.example.ui.theme.Primary
import com.example.ui.theme.PrimaryContainer
import com.example.ui.theme.PrimaryFixed
import com.example.ui.theme.Secondary
import com.example.ui.theme.SecondaryContainer
import com.example.ui.theme.SecondaryFixed
import com.example.ui.theme.SurfaceContainer
import com.example.ui.theme.SurfaceContainerHigh
import com.example.ui.theme.SurfaceContainerLow
import com.example.ui.theme.SurfaceContainerLowest
import com.example.viewmodel.AppUiState
import com.example.viewmodel.AppViewModel

@Composable
fun OralScreen(
    uiState: AppUiState,
    viewModel: AppViewModel,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    val currentQuestion = viewModel.currentOralQuestion()

    val formattedTime = remember(uiState.oralTimerSeconds) {
        val minutes = uiState.oralTimerSeconds / 60
        val seconds = uiState.oralTimerSeconds % 60
        String.format("%02d:%02d", minutes, seconds)
    }

    val wordCount = remember(uiState.transcriptionText) {
        uiState.transcriptionText
            .trim()
            .split("\\s+".toRegex())
            .filter { it.isNotEmpty() }
            .size
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(horizontal = 20.dp)
            .padding(top = 8.dp, bottom = 100.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Top Session Meta Bar
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                shape = RoundedCornerShape(9999.dp),
                color = PrimaryFixed
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .clip(CircleShape)
                            .background(if (uiState.isRecording) Primary else OnSurfaceVariant)
                    )
                    Text(
                        text = "ENTRETIEN SIMULÉ EN DIRECT",
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontSize = 10.sp,
                            letterSpacing = 1.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        color = Primary
                    )
                }
            }

            Surface(
                shape = RoundedCornerShape(9999.dp),
                color = SurfaceContainer
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Schedule,
                        contentDescription = null,
                        tint = Secondary,
                        modifier = Modifier.size(15.dp)
                    )
                    Text(
                        text = formattedTime,
                        style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold),
                        color = OnSurfaceVariant
                    )
                }
            }
        }

        // Examiner Question Card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(6.dp, RoundedCornerShape(24.dp), ambientColor = Primary.copy(alpha = 0.08f)),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(
                containerColor = SurfaceContainerLowest.copy(alpha = 0.95f)
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Examiner Badge Row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Box(
                            modifier = Modifier.size(44.dp)
                        ) {
                            AsyncImage(
                                model = uiState.profile.inspectorMaleUrl,
                                contentDescription = "Inspecteur",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(44.dp)
                                    .clip(RoundedCornerShape(14.dp))
                            )
                            Box(
                                modifier = Modifier
                                    .size(10.dp)
                                    .clip(CircleShape)
                                    .background(Primary)
                                    .align(Alignment.BottomEnd)
                                    .border(1.5.dp, Color.White, CircleShape)
                            )
                        }

                        Column {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Text(
                                    text = "INSPECTEUR PRÉFECTORAL IA",
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        letterSpacing = 1.sp,
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.Bold
                                    ),
                                    color = OnSurfaceVariant
                                )
                                Icon(
                                    imageVector = Icons.Default.Verified,
                                    contentDescription = null,
                                    tint = Secondary,
                                    modifier = Modifier.size(13.dp)
                                )
                            }
                            Text(
                                text = currentQuestion.indexText,
                                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                                color = OnSurface
                            )
                        }
                    }

                    IconButton(
                        onClick = { viewModel.speakCurrentQuestion() },
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape)
                            .background(SurfaceContainer)
                            .testTag("btn_listen_question")
                    ) {
                        Icon(
                            imageVector = Icons.Default.VolumeUp,
                            contentDescription = "Réécouter la question",
                            tint = Primary,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }

                // Core Question Prompt
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(
                        text = "Question posée :",
                        style = MaterialTheme.typography.labelSmall,
                        color = OnSurfaceVariant
                    )
                    Text(
                        text = "« ${currentQuestion.questionText} »",
                        style = MaterialTheme.typography.headlineLarge.copy(
                            fontSize = 20.sp,
                            lineHeight = 28.sp,
                            fontWeight = FontWeight.SemiBold
                        ),
                        color = Primary
                    )
                }

                // Soundwave Pill
                Surface(
                    shape = RoundedCornerShape(16.dp),
                    color = SurfaceContainer.copy(alpha = 0.7f)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        SoundWaveIndicator(isPlaying = uiState.isRecording)
                        Text(
                            text = if (uiState.isRecording) "L'examinateur vous écoute avec bienveillance..." else "Enregistrement en pause",
                            style = MaterialTheme.typography.labelSmall,
                            color = Secondary
                        )
                    }
                }
            }
        }

        // Live Transcription Section
        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.GraphicEq,
                        contentDescription = null,
                        tint = Primary,
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = "Votre réponse en temps réel",
                        style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Medium),
                        color = OnSurfaceVariant
                    )
                }
                Text(
                    text = if (uiState.isRecording) "Transcription active" else "Pause",
                    style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.SemiBold),
                    color = if (uiState.isRecording) Primary else OnSurfaceVariant
                )
            }

            // Speech stream bubble
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = SurfaceContainerLowest.copy(alpha = 0.95f)
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    BasicTextField(
                        value = uiState.transcriptionText,
                        onValueChange = { viewModel.updateTranscription(it) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("input_transcription"),
                        textStyle = MaterialTheme.typography.bodyMedium.copy(
                            color = OnSurface,
                            lineHeight = 22.sp
                        ),
                        cursorBrush = SolidColor(Primary),
                        decorationBox = { innerTextField ->
                            Box {
                                if (uiState.transcriptionText.isEmpty()) {
                                    Text(
                                        text = "Exprimez-vous ici avec vos propres mots...",
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = OnSurfaceVariant.copy(alpha = 0.5f)
                                    )
                                }
                                innerTextField()
                            }
                        }
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.AutoFixHigh,
                                contentDescription = null,
                                tint = OnSurfaceVariant,
                                modifier = Modifier.size(14.dp)
                            )
                            Text(
                                text = "Élocution : ${uiState.elocutionStatus}",
                                style = MaterialTheme.typography.labelSmall,
                                color = OnSurfaceVariant
                            )
                        }

                        Text(
                            text = "$wordCount mots prononcés",
                            style = MaterialTheme.typography.labelSmall,
                            color = OnSurfaceVariant
                        )
                    }
                }
            }
        }

        // Voice Controls & Mic Trigger
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            // Big Mic Capsule with animated halo
            Box(
                contentAlignment = Alignment.Center
            ) {
                if (uiState.isRecording) {
                    val infiniteTransition = rememberInfiniteTransition(label = "pulse")
                    val pulseScale by infiniteTransition.animateFloat(
                        initialValue = 1f,
                        targetValue = 1.25f,
                        animationSpec = infiniteRepeatable(
                            animation = tween(1200, easing = FastOutSlowInEasing),
                            repeatMode = RepeatMode.Reverse
                        ),
                        label = "pulseScale"
                    )
                    Box(
                        modifier = Modifier
                            .size(110.dp)
                            .scale(pulseScale)
                            .clip(CircleShape)
                            .background(SecondaryFixed.copy(alpha = 0.45f))
                    )
                }

                Surface(
                    modifier = Modifier
                        .clip(RoundedCornerShape(9999.dp))
                        .clickable { viewModel.toggleRecording() }
                        .shadow(8.dp, RoundedCornerShape(9999.dp))
                        .testTag("btn_main_record"),
                    shape = RoundedCornerShape(9999.dp),
                    color = if (uiState.isRecording) PrimaryContainer else SurfaceContainerHigh
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 24.dp, vertical = 14.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .clip(CircleShape)
                                .background(if (uiState.isRecording) PrimaryFixed else SurfaceContainerLowest),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = if (uiState.isRecording) Icons.Default.Mic else Icons.Default.PlayArrow,
                                contentDescription = null,
                                tint = Primary,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                        Column {
                            Text(
                                text = if (uiState.isRecording) "Parler maintenant" else "Reprendre l'élocution",
                                style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
                                color = if (uiState.isRecording) OnPrimary else OnSurface
                            )
                            Text(
                                text = if (uiState.isRecording) "Appuyez pour faire une pause" else "Appuyez pour parler",
                                style = MaterialTheme.typography.labelSmall.copy(fontSize = 11.sp),
                                color = if (uiState.isRecording) PrimaryFixed else OnSurfaceVariant
                            )
                        }
                    }
                }
            }

            // Quick action auxiliaries (Pause & Recommencer & Exemple)
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Surface(
                    shape = RoundedCornerShape(9999.dp),
                    color = SurfaceContainer,
                    modifier = Modifier
                        .clickable { viewModel.pauseRecording() }
                        .testTag("btn_pause")
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 14.dp, vertical = 7.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Pause,
                            contentDescription = null,
                            modifier = Modifier.size(15.dp),
                            tint = OnSurface
                        )
                        Text(
                            text = "Pause",
                            style = MaterialTheme.typography.labelMedium,
                            color = OnSurface
                        )
                    }
                }

                Surface(
                    shape = RoundedCornerShape(9999.dp),
                    color = SurfaceContainer,
                    modifier = Modifier
                        .clickable { viewModel.resetAnswer() }
                        .testTag("btn_reset")
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 14.dp, vertical = 7.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.RestartAlt,
                            contentDescription = null,
                            modifier = Modifier.size(15.dp),
                            tint = OnSurface
                        )
                        Text(
                            text = "Recommencer",
                            style = MaterialTheme.typography.labelMedium,
                            color = OnSurface
                        )
                    }
                }

                Surface(
                    shape = RoundedCornerShape(9999.dp),
                    color = SurfaceContainer,
                    modifier = Modifier
                        .clickable { viewModel.restoreSampleAnswer() }
                        .testTag("btn_sample")
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 14.dp, vertical = 7.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.AutoFixHigh,
                            contentDescription = null,
                            modifier = Modifier.size(15.dp),
                            tint = Secondary
                        )
                        Text(
                            text = "Exemple",
                            style = MaterialTheme.typography.labelMedium,
                            color = Secondary
                        )
                    }
                }

                Surface(
                    shape = RoundedCornerShape(9999.dp),
                    color = SurfaceContainer,
                    modifier = Modifier
                        .clickable { viewModel.openGuide707(true) }
                        .testTag("btn_oral_open_guide_707")
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 14.dp, vertical = 7.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Lightbulb,
                            contentDescription = null,
                            modifier = Modifier.size(15.dp),
                            tint = androidx.compose.ui.graphics.Color(0xFFD97706)
                        )
                        Text(
                            text = "Tips & 707 Q.",
                            style = MaterialTheme.typography.labelMedium,
                            color = OnSurface
                        )
                    }
                }
            }
        }

        // Collapsible Benevolent Examiner Tips (Accordion)
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = SurfaceContainerLow)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { viewModel.toggleAdviceExpanded() }
                        .testTag("accordion_tip_trigger"),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(SecondaryFixed),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.PsychologyAlt,
                                contentDescription = null,
                                tint = Secondary,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                        Text(
                            text = "Conseil bienveillant de l'instructeur",
                            style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.SemiBold),
                            color = OnSurface
                        )
                    }

                    Icon(
                        imageVector = if (uiState.isAdviceExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                        contentDescription = null,
                        tint = OnSurfaceVariant
                    )
                }

                AnimatedVisibility(visible = uiState.isAdviceExpanded) {
                    Column(
                        modifier = Modifier.padding(top = 8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = currentQuestion.instructorAdvice,
                            style = MaterialTheme.typography.bodyMedium.copy(lineHeight = 20.sp),
                            color = OnSurfaceVariant
                        )

                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            currentQuestion.keywords.forEach { keyword ->
                                Surface(
                                    shape = RoundedCornerShape(9999.dp),
                                    color = SurfaceContainer
                                ) {
                                    Text(
                                        text = keyword,
                                        style = MaterialTheme.typography.labelSmall,
                                        color = OnSurface,
                                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        // Bottom Forward Progression Action
        Button(
            onClick = { viewModel.nextOralQuestion() },
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
                .testTag("btn_next_question"),
            shape = RoundedCornerShape(9999.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Secondary,
                contentColor = OnSecondary
            )
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "Valider & Question suivante",
                    style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.SemiBold)
                )
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )
            }
        }
    }
}

@Composable
private fun SoundWaveIndicator(isPlaying: Boolean) {
    val infiniteTransition = rememberInfiniteTransition(label = "wave")
    val h1 by infiniteTransition.animateFloat(
        initialValue = 4f,
        targetValue = 14f,
        animationSpec = infiniteRepeatable(tween(400), RepeatMode.Reverse),
        label = "h1"
    )
    val h2 by infiniteTransition.animateFloat(
        initialValue = 12f,
        targetValue = 6f,
        animationSpec = infiniteRepeatable(tween(550), RepeatMode.Reverse),
        label = "h2"
    )
    val h3 by infiniteTransition.animateFloat(
        initialValue = 8f,
        targetValue = 16f,
        animationSpec = infiniteRepeatable(tween(350), RepeatMode.Reverse),
        label = "h3"
    )

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(3.dp),
        modifier = Modifier.height(16.dp)
    ) {
        Box(
            modifier = Modifier
                .width(3.dp)
                .height(if (isPlaying) h1.dp else 4.dp)
                .clip(CircleShape)
                .background(Secondary)
        )
        Box(
            modifier = Modifier
                .width(3.dp)
                .height(if (isPlaying) h2.dp else 8.dp)
                .clip(CircleShape)
                .background(Secondary)
        )
        Box(
            modifier = Modifier
                .width(3.dp)
                .height(if (isPlaying) h3.dp else 5.dp)
                .clip(CircleShape)
                .background(Secondary)
        )
    }
}
