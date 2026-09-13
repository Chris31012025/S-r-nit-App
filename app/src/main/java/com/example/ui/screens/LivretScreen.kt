package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.AutoStories
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PictureAsPdf
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material.icons.filled.WorkspacePremium
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.data.CivicChapter
import com.example.data.CivicPillar
import com.example.data.CivicRepository
import com.example.data.LivretDuCitoyenRepository
import com.example.data.RevisionSheet
import com.example.ui.theme.OnPrimary
import com.example.ui.theme.OnSecondary
import com.example.ui.theme.OnSurface
import com.example.ui.theme.OnSurfaceVariant
import com.example.ui.theme.Primary
import com.example.ui.theme.PrimaryContainer
import com.example.ui.theme.PrimaryFixed
import com.example.ui.theme.Secondary
import com.example.ui.theme.SecondaryContainer
import com.example.ui.theme.SoftBlueCardBg
import com.example.ui.theme.SoftLavenderBg
import com.example.ui.theme.SoftMintCardBg
import com.example.ui.theme.SurfaceContainer
import com.example.ui.theme.SurfaceContainerHigh
import com.example.ui.theme.SurfaceContainerLow
import com.example.ui.theme.SurfaceContainerLowest
import com.example.ui.theme.SurfaceDim
import com.example.ui.theme.SurfaceVariant
import com.example.ui.theme.TertiaryContainer
import com.example.viewmodel.AppThemeMode
import com.example.viewmodel.AppUiState

@Composable
fun LivretScreen(
    uiState: AppUiState,
    onPillarClick: (CivicPillar) -> Unit,
    onPillarAudioToggle: (CivicPillar) -> Unit,
    onRevisionSheetClick: (RevisionSheet) -> Unit,
    onOpenAiTutor: () -> Unit,
    onOpenPdf: () -> Unit,
    onOpenGuide707: (CivicChapter?, Boolean) -> Unit = { _, _ -> },
    onOpenLivretViewer: (Boolean) -> Unit = {},
    onToggleTheme: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(horizontal = 20.dp)
            .padding(top = 8.dp, bottom = 100.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        // Title Header block
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "ENTRETIEN DE NATURALISATION",
                    style = MaterialTheme.typography.labelSmall.copy(
                        letterSpacing = 1.4.sp,
                        fontWeight = FontWeight.SemiBold
                    ),
                    color = OnSurfaceVariant
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // Quick Reading Theme Toggle Button
                    Surface(
                        shape = RoundedCornerShape(9999.dp),
                        color = SurfaceContainerHigh,
                        modifier = Modifier
                            .clip(RoundedCornerShape(9999.dp))
                            .clickable(onClick = onToggleTheme)
                            .testTag("btn_toggle_livret_screen_theme")
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Icon(
                                imageVector = if (uiState.themeMode == AppThemeMode.DARK) Icons.Default.LightMode else Icons.Default.DarkMode,
                                contentDescription = if (uiState.themeMode == AppThemeMode.DARK) "Mode clair" else "Mode sombre",
                                tint = Primary,
                                modifier = Modifier.size(13.dp)
                            )
                            Text(
                                text = if (uiState.themeMode == AppThemeMode.DARK) "Sombre" else "Clair",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 11.sp
                                ),
                                color = Primary
                            )
                        }
                    }

                    Surface(
                        shape = RoundedCornerShape(9999.dp),
                        color = PrimaryFixed.copy(alpha = 0.8f)
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(3.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Verified,
                                contentDescription = null,
                                tint = Primary,
                                modifier = Modifier.size(13.dp)
                            )
                            Text(
                                text = "Officiel",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 11.sp
                                ),
                                color = Primary
                            )
                        }
                    }
                }
            }

            Text(
                text = "Livret du Citoyen",
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 24.sp
                ),
                color = Primary
            )

            Text(
                text = "Les 4 piliers indispensables pour réussir sereinement votre entretien d'assimilation républicaine.",
                style = MaterialTheme.typography.bodyMedium,
                color = OnSurfaceVariant,
                lineHeight = 20.sp
            )
        }

        // Global Civic Mastery Card (68%)
        GlobalMasteryCard(profile = uiState.profile)

        // Livret du Citoyen Officiel & Dates Mnémoniques Hero Card
        OfficialLivretReaderHeroCard(
            masteredDatesCount = uiState.masteredDateIds.size,
            totalDatesCount = LivretDuCitoyenRepository.historicDates.size,
            onOpenFullText = { onOpenLivretViewer(false) },
            onOpenDatesMnemonics = { onOpenLivretViewer(true) }
        )

        // Guide Officiel des +707 Questions (3e Édition 2026) Card
        Guide707BannerCard(
            masteredCount = uiState.masteredQuestionIds.size,
            onOpenGuide = onOpenGuide707
        )

        // Piliers d'apprentissage (4 cards grid 2x2)
        PillarsSection(
            pillars = CivicRepository.pillars,
            activePlayingPillarId = uiState.activePlayingPillarId,
            isAudioPlaying = uiState.isPillarAudioPlaying,
            onPillarClick = onPillarClick,
            onAudioToggle = onPillarAudioToggle
        )

        // Fiches de révision rapide (5 min chrono)
        RevisionSheetsSection(
            sheets = CivicRepository.revisionSheets,
            onSheetClick = onRevisionSheetClick
        )

        // Action CTA Row: Poser une question à l'IA & PDF Officiel
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Button(
                onClick = onOpenAiTutor,
                modifier = Modifier
                    .weight(1.3f)
                    .height(48.dp)
                    .testTag("btn_ask_ai"),
                shape = RoundedCornerShape(9999.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Primary,
                    contentColor = OnPrimary
                )
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Psychology,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                    Text(
                        text = "Poser une question à l'IA",
                        style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold)
                    )
                }
            }

            Surface(
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp)
                    .clip(RoundedCornerShape(9999.dp))
                    .clickable { onOpenLivretViewer(false) }
                    .testTag("btn_open_pdf"),
                shape = RoundedCornerShape(9999.dp),
                color = SurfaceContainerLowest.copy(alpha = 0.95f),
                shadowElevation = 2.dp
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.MenuBook,
                        contentDescription = null,
                        tint = Primary,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Livret officiel",
                        style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold),
                        color = OnSurface
                    )
                }
            }
        }

        // Instructor advice note
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            color = SurfaceContainerLow.copy(alpha = 0.7f)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(14.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(38.dp)
                        .clip(CircleShape)
                        .background(SurfaceContainerLowest),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Lightbulb,
                        contentDescription = null,
                        tint = Primary,
                        modifier = Modifier.size(20.dp)
                    )
                }
                Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                    Text(
                        text = "Conseil de l'instructeur",
                        style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                        color = OnSurface
                    )
                    Text(
                        text = "Ne récitez pas par cœur : montrez que ces valeurs guident votre vie quotidienne en France.",
                        style = MaterialTheme.typography.bodySmall.copy(lineHeight = 16.sp),
                        color = OnSurfaceVariant
                    )
                }
            }
        }
    }
}

@Composable
private fun GlobalMasteryCard(
    profile: com.example.data.CandidateProfile,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .shadow(4.dp, RoundedCornerShape(20.dp), ambientColor = Primary.copy(alpha = 0.08f)),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = SurfaceContainerLowest.copy(alpha = 0.92f)
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
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
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape)
                            .background(PrimaryFixed.copy(alpha = 0.5f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.WorkspacePremium,
                            contentDescription = null,
                            tint = Primary,
                            modifier = Modifier.size(20.dp)
                        )
                    }

                    Column {
                        Text(
                            text = "Maîtrise civique globale",
                            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                            color = OnSurface
                        )
                        Text(
                            text = "${profile.masteredSheets} fiches sur ${profile.totalSheets} assimilées",
                            style = MaterialTheme.typography.bodySmall,
                            color = OnSurfaceVariant
                        )
                    }
                }

                Text(
                    text = "${profile.masteryPercentage}%",
                    style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                    color = Primary
                )
            }

            // Progress bar
            LinearProgressIndicator(
                progress = { profile.masteryPercentage / 100f },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
                    .clip(RoundedCornerShape(9999.dp)),
                color = Primary,
                trackColor = SurfaceContainerHigh
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Objectif : ${profile.targetMastery}% avant convocation",
                    style = MaterialTheme.typography.labelSmall,
                    color = OnSurfaceVariant
                )
                Text(
                    text = profile.languageLevel,
                    style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.SemiBold),
                    color = Primary
                )
            }
        }
    }
}

@Composable
private fun PillarsSection(
    pillars: List<CivicPillar>,
    activePlayingPillarId: String?,
    isAudioPlaying: Boolean,
    onPillarClick: (CivicPillar) -> Unit,
    onAudioToggle: (CivicPillar) -> Unit,
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
                text = "Piliers d'apprentissage",
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.SemiBold),
                color = OnSurface
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.clickable { onPillarClick(pillars.first()) }
            ) {
                Text(
                    text = "Voir le plan détaillé",
                    style = MaterialTheme.typography.labelSmall,
                    color = Secondary
                )
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                    contentDescription = null,
                    tint = Secondary,
                    modifier = Modifier.size(14.dp)
                )
            }
        }

        // 2x2 Grid
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                PillarCard(
                    pillar = pillars[0],
                    isAudioPlaying = isAudioPlaying && activePlayingPillarId == pillars[0].id,
                    onClick = { onPillarClick(pillars[0]) },
                    onAudioToggle = { onAudioToggle(pillars[0]) },
                    modifier = Modifier.weight(1f)
                )
                PillarCard(
                    pillar = pillars[1],
                    isAudioPlaying = isAudioPlaying && activePlayingPillarId == pillars[1].id,
                    onClick = { onPillarClick(pillars[1]) },
                    onAudioToggle = { onAudioToggle(pillars[1]) },
                    modifier = Modifier.weight(1f)
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                PillarCard(
                    pillar = pillars[2],
                    isAudioPlaying = isAudioPlaying && activePlayingPillarId == pillars[2].id,
                    onClick = { onPillarClick(pillars[2]) },
                    onAudioToggle = { onAudioToggle(pillars[2]) },
                    modifier = Modifier.weight(1f)
                )
                PillarCard(
                    pillar = pillars[3],
                    isAudioPlaying = isAudioPlaying && activePlayingPillarId == pillars[3].id,
                    onClick = { onPillarClick(pillars[3]) },
                    onAudioToggle = { onAudioToggle(pillars[3]) },
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
private fun PillarCard(
    pillar: CivicPillar,
    isAudioPlaying: Boolean,
    onClick: () -> Unit,
    onAudioToggle: () -> Unit,
    modifier: Modifier = Modifier
) {
    val bgBrush = when (pillar.gradientIndex) {
        0 -> Brush.verticalGradient(listOf(Color(0xFFEBE5F2), Color(0xFFDDD5E6))) // Principes
        1 -> Brush.verticalGradient(listOf(Color(0xFFDBEAF7), Color(0xFFC8DCF0))) // Histoire
        2 -> Brush.verticalGradient(listOf(Color(0xFFECEAE3), Color(0xFFE0DDD5))) // Institutions
        else -> Brush.verticalGradient(listOf(Color(0xFFD6ECE5), Color(0xFFC3E3D9))) // Droits
    }

    Card(
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .clickable(onClick = onClick)
            .shadow(4.dp, RoundedCornerShape(20.dp))
            .testTag("pillar_card_${pillar.id}"),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        Box(
            modifier = Modifier
                .background(bgBrush)
                .fillMaxWidth()
                .padding(14.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(
                        text = pillar.meta,
                        style = MaterialTheme.typography.labelSmall.copy(fontSize = 11.sp),
                        color = Color(0xFF4D485E),
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        text = pillar.title,
                        style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
                        color = Primary,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = pillar.subtitle,
                        style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.sp, lineHeight = 15.sp),
                        color = Color(0xFF544E66),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Spacer(modifier = Modifier.height(18.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (pillar.tag != null) {
                        Surface(
                            shape = RoundedCornerShape(9999.dp),
                            color = Primary.copy(alpha = 0.1f)
                        ) {
                            Text(
                                text = pillar.tag,
                                style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp, fontWeight = FontWeight.Bold),
                                color = Primary,
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                            )
                        }
                    } else if (pillar.progressNote != null) {
                        Text(
                            text = pillar.progressNote,
                            style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp),
                            color = OnSurfaceVariant
                        )
                    } else {
                        Row(horizontalArrangement = Arrangement.spacedBy(3.dp)) {
                            Box(modifier = Modifier.size(6.dp).clip(CircleShape).background(Primary.copy(alpha = 0.8f)))
                            Box(modifier = Modifier.size(6.dp).clip(CircleShape).background(Primary.copy(alpha = 0.4f)))
                            Box(modifier = Modifier.size(6.dp).clip(CircleShape).background(Primary.copy(alpha = 0.2f)))
                        }
                    }

                    // Circular Play Audio Button
                    Surface(
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape)
                            .clickable { onAudioToggle() }
                            .testTag("pillar_audio_${pillar.id}"),
                        shape = CircleShape,
                        color = if (isAudioPlaying) Primary else SurfaceContainerLowest.copy(alpha = 0.85f),
                        shadowElevation = 2.dp
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Icon(
                                imageVector = if (isAudioPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                                contentDescription = if (isAudioPlaying) "Pause synthèse audio" else "Écouter la synthèse audio",
                                tint = if (isAudioPlaying) OnPrimary else Primary,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun RevisionSheetsSection(
    sheets: List<RevisionSheet>,
    onSheetClick: (RevisionSheet) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Fiches de révision rapide",
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.SemiBold),
                color = OnSurface
            )
            Text(
                text = "5 min chrono",
                style = MaterialTheme.typography.labelSmall,
                color = OnSurfaceVariant
            )
        }

        sheets.forEach { sheet ->
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(20.dp))
                    .clickable { onSheetClick(sheet) }
                    .testTag("revision_sheet_${sheet.id}"),
                shape = RoundedCornerShape(20.dp),
                color = SurfaceContainerLowest.copy(alpha = 0.85f),
                shadowElevation = 2.dp
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        modifier = Modifier.weight(1f),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(48.dp)
                                .clip(CircleShape)
                                .background(SurfaceContainerHigh),
                            contentAlignment = Alignment.Center
                        ) {
                            AsyncImage(
                                model = sheet.imageUrl,
                                contentDescription = sheet.title,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.size(48.dp)
                            )
                        }

                        Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                Text(
                                    text = sheet.tag,
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Bold
                                    ),
                                    color = if (sheet.isQuiz) TertiaryContainer else Secondary
                                )
                                Text(text = "•", color = OnSurfaceVariant, fontSize = 10.sp)
                                Text(
                                    text = sheet.subtitle,
                                    style = MaterialTheme.typography.labelSmall.copy(fontSize = 11.sp),
                                    color = OnSurfaceVariant
                                )
                            }
                            Text(
                                text = sheet.title,
                                style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.SemiBold),
                                color = OnSurface,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                            Text(
                                text = sheet.description,
                                style = MaterialTheme.typography.bodySmall,
                                color = OnSurfaceVariant,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }

                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .clip(CircleShape)
                            .background(SurfaceContainer),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                            contentDescription = null,
                            tint = OnSurfaceVariant,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Guide707BannerCard(
    masteredCount: Int,
    onOpenGuide: (CivicChapter?, Boolean) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .testTag("guide_707_banner_card"),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = SurfaceContainerLowest),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(18.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            // Header with badge
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
                            .background(Primary.copy(alpha = 0.1f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.AutoStories,
                            contentDescription = null,
                            tint = Primary,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                    Column {
                        Text(
                            text = "GUIDE EXHAUSTIF",
                            style = MaterialTheme.typography.labelSmall.copy(
                                letterSpacing = 1.2.sp,
                                fontWeight = FontWeight.Bold
                            ),
                            color = Primary
                        )
                        Text(
                            text = "Les +707 Questions",
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                            color = OnSurface
                        )
                    }
                }

                Surface(
                    shape = RoundedCornerShape(6.dp),
                    color = Color(0xFFE88E38).copy(alpha = 0.15f)
                ) {
                    Text(
                        text = "3e Éd. Actualisée",
                        style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                        color = Color(0xFF944500),
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                    )
                }
            }

            Text(
                text = "Toutes les questions réelles posées en préfecture réparties en 7 chapitres. Réponses actualisées aux dernières réformes et astuces mnémoniques pour chaque date clé.",
                style = MaterialTheme.typography.bodySmall,
                color = OnSurfaceVariant,
                lineHeight = 18.sp
            )

            // Mnemonic preview highlight pill
            Surface(
                shape = RoundedCornerShape(10.dp),
                color = Color(0xFFFDF7EE),
                border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE88E38).copy(alpha = 0.3f)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Lightbulb,
                        contentDescription = null,
                        tint = Color(0xFFD97706),
                        modifier = Modifier.size(18.dp)
                    )
                    Text(
                        text = "Tips mnémoniques inclus : IVG 1975 (V = Veil / Valéry), Ve Rép. 1958 (5-1=4/10/1958), 1789...",
                        style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Medium),
                        color = Color(0xFF78350F),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            // Buttons CTA
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Button(
                    onClick = { onOpenGuide(null, false) },
                    modifier = Modifier
                        .weight(1.2f)
                        .height(44.dp)
                        .testTag("btn_explore_707_questions"),
                    shape = RoundedCornerShape(9999.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Primary,
                        contentColor = OnPrimary
                    )
                ) {
                    Text(
                        text = "Consulter le Guide",
                        style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold)
                    )
                }

                OutlinedButton(
                    onClick = { onOpenGuide(null, true) },
                    modifier = Modifier
                        .weight(1f)
                        .height(44.dp)
                        .testTag("btn_explore_tips_only"),
                    shape = RoundedCornerShape(9999.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Lightbulb,
                            contentDescription = null,
                            tint = Color(0xFFD97706),
                            modifier = Modifier.size(16.dp)
                        )
                        Text(
                            text = "Tips Dates",
                            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold),
                            color = OnSurface
                        )
                    }
                }
            }

            // Quick Chapter Pills
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                CivicChapter.values().forEach { ch ->
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = SurfaceContainerLow,
                        modifier = Modifier.clickable { onOpenGuide(ch, false) }
                    ) {
                        Text(
                            text = "Ch.${ch.chapterNumber} ${ch.shortTitle}",
                            style = MaterialTheme.typography.labelSmall,
                            color = OnSurfaceVariant,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 5.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun OfficialLivretReaderHeroCard(
    masteredDatesCount: Int,
    totalDatesCount: Int,
    onOpenFullText: () -> Unit,
    onOpenDatesMnemonics: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .testTag("card_official_livret_reader"),
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(containerColor = SurfaceContainerLowest),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, PrimaryFixed.copy(alpha = 0.9f))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
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
                        modifier = Modifier
                            .size(42.dp)
                            .clip(CircleShape)
                            .background(Primary),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.MenuBook,
                            contentDescription = null,
                            tint = OnPrimary,
                            modifier = Modifier.size(22.dp)
                        )
                    }

                    Column {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Text(
                                text = "LIVRET DU CITOYEN",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    fontWeight = FontWeight.Bold,
                                    letterSpacing = 1.2.sp
                                ),
                                color = Primary
                            )
                            Surface(
                                shape = RoundedCornerShape(4.dp),
                                color = PrimaryFixed
                            ) {
                                Text(
                                    text = "Ministère Intérieur",
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        fontSize = 9.sp,
                                        fontWeight = FontWeight.Bold
                                    ),
                                    color = Primary,
                                    modifier = Modifier.padding(horizontal = 5.dp, vertical = 1.dp)
                                )
                            }
                        }

                        Text(
                            text = "Contenu Intégral & Tips Dates",
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                            color = OnSurface
                        )
                    }
                }

                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = Color(0xFFFDF7EE),
                    border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE88E38).copy(alpha = 0.35f))
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Lightbulb,
                            contentDescription = null,
                            tint = Color(0xFFD97706),
                            modifier = Modifier.size(14.dp)
                        )
                        Text(
                            text = "$masteredDatesCount/$totalDatesCount retenues",
                            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                            color = Color(0xFF78350F)
                        )
                    }
                }
            }

            Text(
                text = "Consultez les principes républicains, symboles, droits et devoirs, ainsi que les 20 dates historiques incontournables avec leurs astuces mnémoniques (IVG 1975, Ve République 1958, 1789...).",
                style = MaterialTheme.typography.bodySmall.copy(lineHeight = 18.sp),
                color = OnSurfaceVariant
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Button(
                    onClick = onOpenFullText,
                    modifier = Modifier
                        .weight(1.1f)
                        .height(44.dp)
                        .testTag("btn_read_livret_full"),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Primary,
                        contentColor = OnPrimary
                    )
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.MenuBook,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp)
                        )
                        Text(
                            text = "Lire le Livret",
                            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold)
                        )
                    }
                }

                Button(
                    onClick = onOpenDatesMnemonics,
                    modifier = Modifier
                        .weight(1.2f)
                        .height(44.dp)
                        .testTag("btn_open_dates_mnemonics"),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFDF7EE),
                        contentColor = Color(0xFF92400E)
                    ),
                    border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE88E38).copy(alpha = 0.5f))
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Lightbulb,
                            contentDescription = null,
                            tint = Color(0xFFD97706),
                            modifier = Modifier.size(16.dp)
                        )
                        Text(
                            text = "Dates & Tips (20)",
                            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold)
                        )
                    }
                }
            }
        }
    }
}

