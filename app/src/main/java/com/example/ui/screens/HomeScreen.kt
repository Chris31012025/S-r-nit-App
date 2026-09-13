package com.example.ui.screens

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AutoStories
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material.icons.filled.Whatshot
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.data.CivicRepository
import com.example.ui.theme.CoralAccent
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
import com.example.ui.theme.TertiaryContainer
import com.example.ui.theme.TertiaryFixed
import com.example.viewmodel.AppTab
import com.example.viewmodel.AppUiState

@Composable
fun HomeScreen(
    uiState: AppUiState,
    onToggleDay: (Boolean) -> Unit,
    onNavigateTab: (AppTab) -> Unit,
    onOpenQuiz: (String) -> Unit,
    onOpenSchedule: () -> Unit,
    onOpenGuide707: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(horizontal = 20.dp)
            .padding(top = 8.dp, bottom = 100.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Dynamic Atmospheric Greeting Banner
        AtmosphericGreetingCard(profile = uiState.profile)

        // Readiness Diagnostic Card
        ReadinessCard(
            selectedDayIsToday = uiState.selectedDayIsToday,
            onToggleDay = onToggleDay,
            onConsolidateClick = {
                onOpenQuiz("Devise & Symboles")
            }
        )

        // Interactive Quick Voice Capsules
        QuickActionCapsules(
            onVoiceClick = { onNavigateTab(AppTab.ORAL) },
            onScheduleClick = onOpenSchedule
        )

        // Highlight: Guide +707 Questions & Tips Mnémoniques du jour
        HomeMnemonicTipsCapsule(
            onOpenGuide = onOpenGuide707
        )

        // Section: Sélection recommandée (2-Column Micro Bento)
        RecommendedSelectionSection(
            onQuizClick = { onOpenQuiz("Devise & Symboles") },
            onScenarioClick = { onOpenQuiz("Laïcité en mairie") }
        )

        // Reassuring Examiner Advice
        ExaminerAdviceCapsule(
            avatarUrl = uiState.profile.examinerFemaleUrl
        )
    }
}

@Composable
private fun AtmosphericGreetingCard(
    profile: com.example.data.CandidateProfile,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .shadow(4.dp, RoundedCornerShape(24.dp), ambientColor = Primary.copy(alpha = 0.05f)),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = SurfaceContainerHigh.copy(alpha = 0.85f)
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Ritual pill
                Surface(
                    shape = RoundedCornerShape(9999.dp),
                    color = SurfaceContainerLowest.copy(alpha = 0.85f),
                    modifier = Modifier.shadow(1.dp, RoundedCornerShape(9999.dp))
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.WbSunny,
                            contentDescription = null,
                            tint = Primary,
                            modifier = Modifier.size(15.dp)
                        )
                        Text(
                            text = "Rituel du matin · 10 min",
                            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.SemiBold),
                            color = Primary
                        )
                    }
                }

                // Streak count
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = "Série de ${profile.streakDays} j",
                        style = MaterialTheme.typography.labelSmall,
                        color = OnSurfaceVariant
                    )
                    Icon(
                        imageVector = Icons.Default.Whatshot,
                        contentDescription = "Série",
                        tint = CoralAccent,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }

            Column(
                modifier = Modifier.padding(top = 2.dp)
            ) {
                Text(
                    text = "Bonjour ${profile.firstName}",
                    style = MaterialTheme.typography.labelMedium,
                    color = OnSurfaceVariant
                )
                Text(
                    text = "Aujourd’hui, faites un pas serein vers votre naturalisation.",
                    style = MaterialTheme.typography.headlineLarge.copy(
                        fontSize = 24.sp,
                        lineHeight = 32.sp,
                        fontWeight = FontWeight.SemiBold
                    ),
                    color = Primary,
                    modifier = Modifier.padding(top = 2.dp)
                )
            }

            Text(
                text = "« La République assure la liberté de conscience. Elle garantit le libre exercice des cultes. »\nLoi du 9 décembre 1905",
                style = MaterialTheme.typography.bodySmall.copy(
                    lineHeight = 18.sp
                ),
                color = OnSurfaceVariant,
                modifier = Modifier.padding(top = 2.dp)
            )
        }
    }
}

@Composable
private fun ReadinessCard(
    selectedDayIsToday: Boolean,
    onToggleDay: (Boolean) -> Unit,
    onConsolidateClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .shadow(6.dp, RoundedCornerShape(24.dp), ambientColor = Primary.copy(alpha = 0.08f)),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = SurfaceContainerLowest.copy(alpha = 0.92f)
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            // Header Row with Toggle
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Psychology,
                        contentDescription = null,
                        tint = Secondary,
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        text = "État de préparation",
                        style = MaterialTheme.typography.labelLarge,
                        color = OnSurface
                    )
                }

                // Segmented Toggle
                Surface(
                    shape = RoundedCornerShape(9999.dp),
                    color = SurfaceContainer
                ) {
                    Row(
                        modifier = Modifier.padding(2.dp)
                    ) {
                        Surface(
                            shape = RoundedCornerShape(9999.dp),
                            color = if (selectedDayIsToday) SurfaceContainerLowest else Color.Transparent,
                            modifier = Modifier
                                .clickable { onToggleDay(true) }
                                .testTag("btn_today")
                        ) {
                            Text(
                                text = "Aujourd’hui",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    fontWeight = if (selectedDayIsToday) FontWeight.Bold else FontWeight.Normal
                                ),
                                color = if (selectedDayIsToday) Primary else OnSurfaceVariant,
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                            )
                        }

                        Surface(
                            shape = RoundedCornerShape(9999.dp),
                            color = if (!selectedDayIsToday) SurfaceContainerLowest else Color.Transparent,
                            modifier = Modifier
                                .clickable { onToggleDay(false) }
                                .testTag("btn_yesterday")
                        ) {
                            Text(
                                text = "Hier",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    fontWeight = if (!selectedDayIsToday) FontWeight.Bold else FontWeight.Normal
                                ),
                                color = if (!selectedDayIsToday) Primary else OnSurfaceVariant,
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                            )
                        }
                    }
                }
            }

            // Date + Metric Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(14.dp),
                verticalAlignment = Alignment.Top
            ) {
                // Date box
                Surface(
                    modifier = Modifier.size(width = 58.dp, height = 64.dp),
                    shape = RoundedCornerShape(16.dp),
                    color = SurfaceContainerHigh.copy(alpha = 0.8f)
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = if (selectedDayIsToday) "18" else "17",
                            style = MaterialTheme.typography.headlineMedium.copy(
                                fontWeight = FontWeight.Bold,
                                lineHeight = 24.sp
                            ),
                            color = Primary
                        )
                        Text(
                            text = "MARS",
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontSize = 10.sp,
                                letterSpacing = 1.sp
                            ),
                            color = OnSurfaceVariant
                        )
                    }
                }

                // Metric + Text
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    val percentage = if (selectedDayIsToday) 78 else 76
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Indicateur d’assimilation",
                            style = MaterialTheme.typography.labelSmall,
                            color = OnSurfaceVariant
                        )
                        Text(
                            text = "$percentage%",
                            style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
                            color = Primary
                        )
                    }

                    LinearProgressIndicator(
                        progress = { percentage / 100f },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(6.dp)
                            .clip(RoundedCornerShape(9999.dp)),
                        color = Primary,
                        trackColor = SurfaceContainerHigh
                    )

                    AnimatedContent(
                        targetState = selectedDayIsToday,
                        transitionSpec = { fadeIn() togetherWith fadeOut() },
                        label = "diagnosticText"
                    ) { isToday ->
                        if (isToday) {
                            Text(
                                text = "Vos acquis sur les Institutions et la Laïcité sont solides. Nous vous suggérons 5 min d’ancrage sur la Révolution de 1789.",
                                style = MaterialTheme.typography.bodySmall.copy(lineHeight = 17.sp),
                                color = OnSurfaceVariant
                            )
                        } else {
                            Text(
                                text = "Hier : Séance de 12 min complétée avec succès. Vous avez validé les questions sur l’Union Européenne et les Droits de l’Homme.",
                                style = MaterialTheme.typography.bodySmall.copy(lineHeight = 17.sp),
                                color = OnSurfaceVariant
                            )
                        }
                    }
                }
            }

            // CTA Button
            Button(
                onClick = onConsolidateClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .testTag("btn_consolidate"),
                shape = RoundedCornerShape(9999.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Primary,
                    contentColor = OnPrimary
                )
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Verified,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                    Text(
                        text = "Consolider les points clés du jour",
                        style = MaterialTheme.typography.labelLarge
                    )
                }
            }
        }
    }
}

@Composable
private fun QuickActionCapsules(
    onVoiceClick: () -> Unit,
    onScheduleClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        // Voice Oral Prompt Pill
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(9999.dp),
            color = SurfaceContainerLowest.copy(alpha = 0.85f),
            shadowElevation = 2.dp
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 14.dp, end = 6.dp, top = 6.dp, bottom = 6.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier.weight(1f, fill = false)
                ) {
                    Box(
                        modifier = Modifier
                            .size(34.dp)
                            .clip(CircleShape)
                            .background(PrimaryFixed.copy(alpha = 0.6f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Mic,
                            contentDescription = null,
                            tint = Primary,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                    Text(
                        text = "Testez votre présentation en 60 s ?",
                        style = MaterialTheme.typography.bodyMedium,
                        color = OnSurface
                    )
                }

                Button(
                    onClick = onVoiceClick,
                    shape = RoundedCornerShape(9999.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Secondary,
                        contentColor = OnPrimary
                    ),
                    modifier = Modifier
                        .height(38.dp)
                        .testTag("btn_voice_test")
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Mic,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp)
                        )
                        Text(
                            text = "Parler",
                            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold)
                        )
                    }
                }
            }
        }

        // Quick Oral Simulation Booking Pill
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(9999.dp),
            color = SurfaceContainerLowest.copy(alpha = 0.85f),
            shadowElevation = 2.dp
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 14.dp, end = 6.dp, top = 6.dp, bottom = 6.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier.weight(1f, fill = false)
                ) {
                    Box(
                        modifier = Modifier
                            .size(34.dp)
                            .clip(CircleShape)
                            .background(com.example.ui.theme.SecondaryFixed.copy(alpha = 0.6f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Psychology,
                            contentDescription = null,
                            tint = Secondary,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                    Text(
                        text = "Simulation avec instructeur virtuel",
                        style = MaterialTheme.typography.bodyMedium,
                        color = OnSurface
                    )
                }

                IconButton(
                    onClick = onScheduleClick,
                    modifier = Modifier
                        .size(38.dp)
                        .clip(CircleShape)
                        .background(SurfaceContainerHigh)
                        .testTag("btn_schedule_simulation")
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Programmer une simulation",
                        tint = Primary,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun RecommendedSelectionSection(
    onQuizClick: () -> Unit,
    onScenarioClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Sélection recommandée",
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.SemiBold),
                color = Primary
            )
            Text(
                text = "Voir tout",
                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Medium),
                color = Secondary,
                modifier = Modifier.clickable { onQuizClick() }
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Card 1: Devise & Symboles
            Card(
                modifier = Modifier
                    .weight(1f)
                    .clickable { onQuizClick() }
                    .testTag("card_devise_symboles"),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = SurfaceContainerLow.copy(alpha = 0.9f)
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
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
                                text = "3 min",
                                style = MaterialTheme.typography.labelSmall.copy(fontSize = 11.sp),
                                color = Primary,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                            )
                        }

                        Box(
                            modifier = Modifier
                                .size(28.dp)
                                .clip(CircleShape)
                                .background(SurfaceContainerLowest),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.PlayArrow,
                                contentDescription = null,
                                tint = Primary,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }

                    Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                        Text(
                            text = "Devise & Symboles",
                            style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
                            color = OnSurface
                        )
                        Text(
                            text = "3 questions express sur la Marianne et le drapeau.",
                            style = MaterialTheme.typography.bodySmall,
                            color = OnSurfaceVariant,
                            lineHeight = 16.sp
                        )
                    }
                }
            }

            // Card 2: Question Piège
            Card(
                modifier = Modifier
                    .weight(1f)
                    .clickable { onScenarioClick() }
                    .testTag("card_question_piege"),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = SurfaceContainerLow.copy(alpha = 0.9f)
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Surface(
                            shape = RoundedCornerShape(9999.dp),
                            color = TertiaryFixed
                        ) {
                            Text(
                                text = "Question piège",
                                style = MaterialTheme.typography.labelSmall.copy(fontSize = 11.sp),
                                color = TertiaryContainer,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                            )
                        }

                        Box(
                            modifier = Modifier
                                .size(28.dp)
                                .clip(CircleShape)
                                .background(SurfaceContainerLowest),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.HelpOutline,
                                contentDescription = null,
                                tint = TertiaryContainer,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }

                    Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                        Text(
                            text = "Laïcité en mairie",
                            style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
                            color = OnSurface
                        )
                        Text(
                            text = "Comment réagir face au refus de saluer une officière ?",
                            style = MaterialTheme.typography.bodySmall,
                            color = OnSurfaceVariant,
                            lineHeight = 16.sp
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ExaminerAdviceCapsule(
    avatarUrl: String,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        color = SurfaceContainerHigh.copy(alpha = 0.6f)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            AsyncImage(
                model = avatarUrl,
                contentDescription = "Examinatrice",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                Text(
                    text = "Conseil de l’examinateur",
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                    color = Primary
                )
                Text(
                    text = "« Il ne s’agit pas d’un interrogatoire par cœur, mais d’un dialogue sur votre attachement aux libertés républicaines. »",
                    style = MaterialTheme.typography.bodySmall.copy(lineHeight = 17.sp),
                    color = OnSurfaceVariant
                )
            }
        }
    }
}

@Composable
private fun HomeMnemonicTipsCapsule(
    onOpenGuide: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onOpenGuide() },
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = androidx.compose.ui.graphics.Color(0xFFFDF7EE)
        ),
        border = androidx.compose.foundation.BorderStroke(
            1.dp,
            androidx.compose.ui.graphics.Color(0xFFE88E38).copy(alpha = 0.35f)
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
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
                            .size(32.dp)
                            .clip(CircleShape)
                            .background(androidx.compose.ui.graphics.Color(0xFFE88E38).copy(alpha = 0.2f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Lightbulb,
                            contentDescription = null,
                            tint = androidx.compose.ui.graphics.Color(0xFFD97706),
                            modifier = Modifier.size(18.dp)
                        )
                    }
                    Text(
                        text = "ASTUCES DATES DU JOUR",
                        style = MaterialTheme.typography.labelSmall.copy(
                            letterSpacing = 1.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        color = androidx.compose.ui.graphics.Color(0xFFB45309)
                    )
                }

                Surface(
                    shape = RoundedCornerShape(6.dp),
                    color = androidx.compose.ui.graphics.Color(0xFFE88E38).copy(alpha = 0.2f)
                ) {
                    Text(
                        text = "+707 Questions",
                        style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                        color = androidx.compose.ui.graphics.Color(0xFF944500),
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                    )
                }
            }

            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    Text(
                        text = "•",
                        style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold),
                        color = androidx.compose.ui.graphics.Color(0xFF78350F)
                    )
                    Text(
                        text = "Loi IVG de 1975 : Le « V » pour Simone Veil et le président Valéry Giscard d'Estaing.",
                        style = MaterialTheme.typography.bodySmall.copy(
                            lineHeight = 18.sp,
                            fontWeight = FontWeight.Medium
                        ),
                        color = androidx.compose.ui.graphics.Color(0xFF78350F)
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    Text(
                        text = "•",
                        style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold),
                        color = androidx.compose.ui.graphics.Color(0xFF78350F)
                    )
                    Text(
                        text = "Constitution 5e République : Le chiffre 5 en pilier : 5 - 1 = 4, 5 * 2 = 10, soit 4 / 10 / 1958.",
                        style = MaterialTheme.typography.bodySmall.copy(
                            lineHeight = 18.sp,
                            fontWeight = FontWeight.Medium
                        ),
                        color = androidx.compose.ui.graphics.Color(0xFF78350F)
                    )
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Ouvrir le guide complet des 7 chapitres →",
                    style = MaterialTheme.typography.labelSmall.copy(
                        fontWeight = FontWeight.Bold,
                        color = androidx.compose.ui.graphics.Color(0xFFB45309)
                    )
                )
            }
        }
    }
}

