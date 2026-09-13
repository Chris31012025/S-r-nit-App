package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.data.CivicChapter
import com.example.data.Guide707QuestionsData
import com.example.data.GuideQuestion
import com.example.ui.theme.OnPrimary
import com.example.ui.theme.OnSurface
import com.example.ui.theme.OnSurfaceVariant
import com.example.ui.theme.Primary
import com.example.ui.theme.PrimaryContainer
import com.example.ui.theme.Secondary
import com.example.ui.theme.SecondaryContainer
import com.example.ui.theme.Surface
import com.example.ui.theme.SurfaceContainer
import com.example.ui.theme.SurfaceContainerHigh
import com.example.ui.theme.SurfaceContainerLow
import com.example.ui.theme.SurfaceContainerLowest
import com.example.ui.theme.SurfaceVariant
import com.example.viewmodel.AppUiState
import com.example.viewmodel.AppViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Guide707Dialog(
    uiState: AppUiState,
    viewModel: AppViewModel,
    onDismiss: () -> Unit
) {
    val allQuestions = Guide707QuestionsData.allQuestions

    // Filter questions based on chapter, search query, date tips, and crucial flag
    val filteredQuestions by remember(
        uiState.guideSelectedChapter,
        uiState.guideSearchQuery,
        uiState.guideOnlyTips,
        uiState.guideOnlyCrucial
    ) {
        derivedStateOf {
            allQuestions.filter { q ->
                val matchesChapter = uiState.guideSelectedChapter == null || q.chapter == uiState.guideSelectedChapter
                val matchesSearch = uiState.guideSearchQuery.isBlank() ||
                    q.question.contains(uiState.guideSearchQuery, ignoreCase = true) ||
                    q.answer.contains(uiState.guideSearchQuery, ignoreCase = true) ||
                    q.keywords.any { it.contains(uiState.guideSearchQuery, ignoreCase = true) } ||
                    (!q.dateTip.isNullOrBlank() && q.dateTip.contains(uiState.guideSearchQuery, ignoreCase = true))

                val matchesTips = !uiState.guideOnlyTips || !q.dateTip.isNullOrBlank()
                val matchesCrucial = !uiState.guideOnlyCrucial || q.isCrucial

                matchesChapter && matchesSearch && matchesTips && matchesCrucial
            }
        }
    }

    val masteredCount by remember(uiState.masteredQuestionIds) {
        derivedStateOf { uiState.masteredQuestionIds.size }
    }

    Dialog(
        onDismissRequest = {
            viewModel.stopSpeaking()
            onDismiss()
        },
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(Surface),
            color = Surface
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 16.dp)
            ) {
                // Header Bar
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(
                                text = "Guide des +707 Questions",
                                style = MaterialTheme.typography.titleLarge.copy(
                                    fontWeight = FontWeight.Bold
                                ),
                                color = OnSurface
                            )
                            Surface(
                                shape = RoundedCornerShape(6.dp),
                                color = Primary
                            ) {
                                Text(
                                    text = "3e Éd. 2026",
                                    style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                                    color = OnPrimary,
                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                )
                            }
                        }
                        Text(
                            text = "Toutes les questions actualisées avec tips mnémoniques pour retenir les dates",
                            style = MaterialTheme.typography.bodySmall,
                            color = OnSurfaceVariant
                        )
                    }

                    IconButton(
                        onClick = {
                            viewModel.stopSpeaking()
                            onDismiss()
                        },
                        modifier = Modifier.testTag("close_guide_707_button")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Fermer le guide",
                            tint = OnSurface
                        )
                    }
                }

                // Search Box
                OutlinedTextField(
                    value = uiState.guideSearchQuery,
                    onValueChange = { viewModel.setGuideSearchQuery(it) },
                    placeholder = { Text("Rechercher (ex: IVG, 1958, laïcité, 49.3, fleuves, Europe...)") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = null,
                            tint = OnSurfaceVariant
                        )
                    },
                    trailingIcon = {
                        if (uiState.guideSearchQuery.isNotEmpty()) {
                            IconButton(onClick = { viewModel.setGuideSearchQuery("") }) {
                                Icon(Icons.Default.Close, contentDescription = "Effacer")
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 4.dp)
                        .testTag("guide_707_search_input"),
                    shape = RoundedCornerShape(12.dp),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = SurfaceContainerLowest,
                        unfocusedContainerColor = SurfaceContainerLowest,
                        focusedIndicatorColor = Primary,
                        unfocusedIndicatorColor = SurfaceVariant
                    ),
                    singleLine = true
                )

                // Quick Filter Chips (Date Tips / Crucial)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState())
                        .padding(horizontal = 16.dp, vertical = 6.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    FilterChip(
                        selected = uiState.guideOnlyTips,
                        onClick = { viewModel.toggleGuideOnlyTips() },
                        label = { Text("💡 Tips Dates Mnémoniques") },
                        leadingIcon = {
                            if (uiState.guideOnlyTips) {
                                Icon(Icons.Default.Check, contentDescription = null, modifier = Modifier.size(16.dp))
                            }
                        },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = Color(0xFFE88E38).copy(alpha = 0.2f),
                            selectedLabelColor = Color(0xFF944500)
                        )
                    )

                    FilterChip(
                        selected = uiState.guideOnlyCrucial,
                        onClick = { viewModel.toggleGuideOnlyCrucial() },
                        label = { Text("⚠️ Questions Pièges & Clés") },
                        leadingIcon = {
                            if (uiState.guideOnlyCrucial) {
                                Icon(Icons.Default.Check, contentDescription = null, modifier = Modifier.size(16.dp))
                            }
                        },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = Primary.copy(alpha = 0.15f),
                            selectedLabelColor = Primary
                        )
                    )

                    // Stats badge
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = SurfaceContainerHigh,
                        modifier = Modifier.padding(start = 4.dp)
                    ) {
                        Text(
                            text = "${filteredQuestions.size} questions • $masteredCount maîtrisées",
                            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Medium),
                            color = OnSurfaceVariant,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp)
                        )
                    }
                }

                // 7 Chapters Horizontal Bar
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState())
                        .padding(horizontal = 16.dp, vertical = 4.dp),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    val isAllSelected = uiState.guideSelectedChapter == null
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = if (isAllSelected) Primary else SurfaceContainerLow,
                        modifier = Modifier
                            .clickable { viewModel.setGuideSelectedChapter(null) }
                            .testTag("chapter_tab_all")
                    ) {
                        Text(
                            text = "Tous les chapitres",
                            style = MaterialTheme.typography.labelMedium.copy(
                                fontWeight = if (isAllSelected) FontWeight.Bold else FontWeight.Normal
                            ),
                            color = if (isAllSelected) OnPrimary else OnSurface,
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                        )
                    }

                    CivicChapter.values().forEach { chapter ->
                        val isSelected = uiState.guideSelectedChapter == chapter
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = if (isSelected) Primary else SurfaceContainerLow,
                            modifier = Modifier
                                .clickable { viewModel.setGuideSelectedChapter(chapter) }
                                .testTag("chapter_tab_${chapter.chapterNumber}")
                        ) {
                            Text(
                                text = "${chapter.chapterNumber}. ${chapter.shortTitle}",
                                style = MaterialTheme.typography.labelMedium.copy(
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                                ),
                                color = if (isSelected) OnPrimary else OnSurface,
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(6.dp))

                // Question Cards List
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    contentPadding = PaddingValues(bottom = 32.dp),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    items(filteredQuestions, key = { it.id }) { question ->
                        val isMastered = uiState.masteredQuestionIds.contains(question.id)
                        val isSpeaking = uiState.isSpeakingQuestionId == question.id

                        QuestionItemCard(
                            question = question,
                            isMastered = isMastered,
                            isSpeaking = isSpeaking,
                            onToggleMastered = { viewModel.toggleQuestionMastered(question.id) },
                            onAudioClick = { viewModel.speakGuideQuestion(question) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun QuestionItemCard(
    question: GuideQuestion,
    isMastered: Boolean,
    isSpeaking: Boolean,
    onToggleMastered: () -> Unit,
    onAudioClick: () -> Unit
) {
    val borderColor by animateColorAsState(
        targetValue = if (isMastered) Primary.copy(alpha = 0.5f) else Color.Transparent,
        label = "borderAnim"
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, borderColor, RoundedCornerShape(14.dp))
            .testTag("question_card_${question.id}"),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = SurfaceContainerLowest),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            // Badges & Action Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Question Number
                    Surface(
                        shape = RoundedCornerShape(6.dp),
                        color = SecondaryContainer
                    ) {
                        Text(
                            text = question.numberText,
                            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                            color = Secondary,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }

                    // Chapter Tag
                    Surface(
                        shape = RoundedCornerShape(6.dp),
                        color = SurfaceContainerHigh
                    ) {
                        Text(
                            text = "Ch.${question.chapter.chapterNumber} : ${question.chapter.shortTitle}",
                            style = MaterialTheme.typography.labelSmall,
                            color = OnSurfaceVariant,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }

                    if (question.isCrucial) {
                        Surface(
                            shape = RoundedCornerShape(6.dp),
                            color = Color(0xFFBA5D45).copy(alpha = 0.15f)
                        ) {
                            Text(
                                text = "Fréquent",
                                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                                color = Color(0xFFBA5D45),
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                            )
                        }
                    }
                }

                // Actions: Listen Audio & Mark as Mastered
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = onAudioClick,
                        modifier = Modifier
                            .size(32.dp)
                            .clip(CircleShape)
                            .background(if (isSpeaking) Primary else SurfaceContainerLow)
                    ) {
                        Icon(
                            imageVector = if (isSpeaking) Icons.Default.Stop else Icons.Default.PlayArrow,
                            contentDescription = "Écouter la question",
                            tint = if (isSpeaking) OnPrimary else OnSurface,
                            modifier = Modifier.size(18.dp)
                        )
                    }

                    IconButton(
                        onClick = onToggleMastered,
                        modifier = Modifier
                            .size(32.dp)
                            .clip(CircleShape)
                            .background(if (isMastered) PrimaryContainer else SurfaceContainerLow)
                    ) {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = "Marquer comme assimilée",
                            tint = if (isMastered) Primary else OnSurfaceVariant.copy(alpha = 0.5f),
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }

            // Question Text
            Text(
                text = question.question,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.SemiBold,
                    lineHeight = 22.sp
                ),
                color = OnSurface
            )

            // Answer container
            Surface(
                shape = RoundedCornerShape(10.dp),
                color = SurfaceContainerLow,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Text(
                        text = "RÉPONSE RECOMMANDÉE :",
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.sp
                        ),
                        color = Primary
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = question.answer,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            lineHeight = 20.sp
                        ),
                        color = OnSurface
                    )
                }
            }

            // Mnemonic Date Tips Container (if available)
            if (!question.dateTip.isNullOrBlank()) {
                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = Color(0xFFFBF4E8),
                    border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE88E38).copy(alpha = 0.4f)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.padding(12.dp),
                        verticalAlignment = Alignment.Top,
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Lightbulb,
                            contentDescription = null,
                            tint = Color(0xFFD97706),
                            modifier = Modifier.size(20.dp)
                        )
                        Column {
                            Text(
                                text = "ASTUCE MNÉMONIQUE :",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    fontWeight = FontWeight.Bold,
                                    letterSpacing = 0.8.sp
                                ),
                                color = Color(0xFFB45309)
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = question.dateTip,
                                style = MaterialTheme.typography.bodySmall.copy(
                                    fontWeight = FontWeight.Medium,
                                    lineHeight = 18.sp
                                ),
                                color = Color(0xFF78350F)
                            )
                        }
                    }
                }
            }

            // Keywords chips
            if (question.keywords.isNotEmpty()) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    question.keywords.take(4).forEach { keyword ->
                        Surface(
                            shape = RoundedCornerShape(4.dp),
                            color = SurfaceContainerHigh.copy(alpha = 0.6f)
                        ) {
                            Text(
                                text = "#$keyword",
                                style = MaterialTheme.typography.labelSmall,
                                color = OnSurfaceVariant,
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}
