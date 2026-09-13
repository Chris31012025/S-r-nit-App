package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.isSystemInDarkTheme
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.HistoryEdu
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil.compose.AsyncImage
import com.example.data.HistoricDateMnemonic
import com.example.data.HistoricPeriod
import com.example.data.LivretArticle
import com.example.data.LivretCategory
import com.example.data.LivretChapter
import com.example.data.LivretDuCitoyenRepository
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
import com.example.viewmodel.AppUiState
import com.example.viewmodel.AppViewModel

@Composable
fun LivretDuCitoyenDialog(
    uiState: AppUiState,
    viewModel: AppViewModel,
    onDismiss: () -> Unit
) {
    val focusManager = LocalFocusManager.current
    var activeTab by remember { mutableStateOf(if (uiState.livretOnlyDatesMode) 1 else 0) }

    // Filtered Historic Dates
    val filteredDates by remember(
        uiState.livretSearchQuery,
        uiState.livretSelectedPeriod
    ) {
        derivedStateOf {
            LivretDuCitoyenRepository.historicDates.filter { date ->
                val matchesPeriod = uiState.livretSelectedPeriod == null || date.period == uiState.livretSelectedPeriod
                val query = uiState.livretSearchQuery.trim().lowercase()
                val matchesQuery = query.isEmpty() ||
                    date.title.lowercase().contains(query) ||
                    date.exactDate.lowercase().contains(query) ||
                    date.year.toString().contains(query) ||
                    date.mnemonicTip.lowercase().contains(query) ||
                    date.summary.lowercase().contains(query) ||
                    date.tags.any { it.lowercase().contains(query) }

                matchesPeriod && matchesQuery
            }
        }
    }

    // Filtered Livret Chapters
    val filteredChapters by remember(
        uiState.livretSearchQuery,
        uiState.livretSelectedCategory
    ) {
        derivedStateOf {
            LivretDuCitoyenRepository.chapters.filter { chapter ->
                val matchesCategory = uiState.livretSelectedCategory == null || chapter.category == uiState.livretSelectedCategory
                val query = uiState.livretSearchQuery.trim().lowercase()
                val matchesQuery = query.isEmpty() ||
                    chapter.title.lowercase().contains(query) ||
                    chapter.subtitle.lowercase().contains(query) ||
                    chapter.articles.any { art ->
                        art.title.lowercase().contains(query) ||
                            art.content.lowercase().contains(query) ||
                            art.keyPoints.any { kp -> kp.lowercase().contains(query) }
                    }

                matchesCategory && matchesQuery
            }
        }
    }

    // Expanded chapters accordion map
    val expandedChapters = remember {
        mutableStateMapOf<String, Boolean>().apply {
            LivretDuCitoyenRepository.chapters.forEach { this[it.id] = true }
        }
    }

    val isDark = uiState.themeMode == com.example.viewmodel.AppThemeMode.DARK ||
        (uiState.themeMode == com.example.viewmodel.AppThemeMode.SYSTEM && isSystemInDarkTheme())

    Dialog(
        onDismissRequest = {
            viewModel.stopSpeaking()
            onDismiss()
        },
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth(0.96f)
                .fillMaxHeight(0.92f),
            shape = RoundedCornerShape(24.dp),
            color = SurfaceContainerLowest
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                // Top Header Bar
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
                                .size(40.dp)
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
                                        letterSpacing = 1.2.sp,
                                        fontWeight = FontWeight.Bold
                                    ),
                                    color = Primary
                                )
                                Surface(
                                    shape = RoundedCornerShape(4.dp),
                                    color = PrimaryFixed
                                ) {
                                    Text(
                                        text = "Édition Officielle",
                                        style = MaterialTheme.typography.labelSmall.copy(
                                            fontSize = 9.sp,
                                            fontWeight = FontWeight.Bold
                                        ),
                                        color = Primary,
                                        modifier = Modifier.padding(horizontal = 4.dp, vertical = 1.dp)
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

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        // Quick Theme Toggle for reading comfort
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = if (isDark) PrimaryFixed else SurfaceContainerHigh,
                            modifier = Modifier
                                .clip(RoundedCornerShape(12.dp))
                                .clickable { viewModel.toggleThemeMode() }
                                .testTag("btn_toggle_livret_reading_theme")
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                Icon(
                                    imageVector = if (isDark) Icons.Default.LightMode else Icons.Default.DarkMode,
                                    contentDescription = if (isDark) "Passer en mode clair" else "Passer en mode sombre pour confort de lecture",
                                    tint = Primary,
                                    modifier = Modifier.size(16.dp)
                                )
                                Text(
                                    text = if (isDark) "Sombre" else "Clair",
                                    style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                                    color = Primary
                                )
                            }
                        }

                        IconButton(
                            onClick = {
                                viewModel.stopSpeaking()
                                onDismiss()
                            },
                            modifier = Modifier.testTag("btn_close_livret_dialog")
                        ) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Fermer",
                                tint = OnSurfaceVariant
                            )
                        }
                    }
                }

                // Switcher Tabs: 📖 Livret Intégral vs 💡 Chronologie & Tips Mnémoniques
                TabRow(
                    selectedTabIndex = activeTab,
                    containerColor = SurfaceContainerLow,
                    contentColor = Primary,
                    indicator = { tabPositions ->
                        TabRowDefaults.SecondaryIndicator(
                            modifier = Modifier.tabIndicatorOffset(tabPositions[activeTab]),
                            color = Primary
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                ) {
                    Tab(
                        selected = activeTab == 0,
                        onClick = { activeTab = 0 },
                        text = {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                Icon(Icons.Default.MenuBook, contentDescription = null, modifier = Modifier.size(16.dp))
                                Text(
                                    text = "Texte Officiel du Livret",
                                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold)
                                )
                            }
                        },
                        modifier = Modifier.testTag("tab_livret_full_text")
                    )
                    Tab(
                        selected = activeTab == 1,
                        onClick = { activeTab = 1 },
                        text = {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                Icon(
                                    Icons.Default.Lightbulb,
                                    contentDescription = null,
                                    tint = Color(0xFFD97706),
                                    modifier = Modifier.size(16.dp)
                                )
                                Text(
                                    text = "Dates Clés & Tips (20)",
                                    style = MaterialTheme.typography.labelMedium.copy(
                                        fontWeight = FontWeight.Bold,
                                        color = if (activeTab == 1) Color(0xFFB45309) else OnSurfaceVariant
                                    )
                                )
                            }
                        },
                        modifier = Modifier.testTag("tab_livret_dates_mnemonics")
                    )
                }

                // Search Bar
                OutlinedTextField(
                    value = uiState.livretSearchQuery,
                    onValueChange = { viewModel.setLivretSearchQuery(it) },
                    placeholder = {
                        Text(
                            text = if (activeTab == 0) "Rechercher un principe, article, institution..." else "Rechercher une date, IVG, 1789, 1958, de Gaulle...",
                            style = MaterialTheme.typography.bodySmall,
                            color = OnSurfaceVariant
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = null,
                            tint = Primary,
                            modifier = Modifier.size(18.dp)
                        )
                    },
                    trailingIcon = {
                        if (uiState.livretSearchQuery.isNotEmpty()) {
                            IconButton(onClick = { viewModel.setLivretSearchQuery("") }) {
                                Icon(
                                    imageVector = Icons.Default.Close,
                                    contentDescription = "Effacer la recherche",
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .testTag("input_livret_search"),
                    shape = RoundedCornerShape(14.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Primary,
                        unfocusedBorderColor = SurfaceContainerHigh,
                        focusedContainerColor = SurfaceContainerLow,
                        unfocusedContainerColor = SurfaceContainerLow
                    ),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                    keyboardActions = KeyboardActions(onSearch = { focusManager.clearFocus() })
                )

                // Sub-filter Row
                if (activeTab == 0) {
                    // Category Chips for Livret Articles
                    LazyRow(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        item {
                            FilterChip(
                                selected = uiState.livretSelectedCategory == null,
                                onClick = { viewModel.setLivretCategory(null) },
                                label = { Text("Tous les chapitres", style = MaterialTheme.typography.labelSmall) },
                                colors = FilterChipDefaults.filterChipColors(
                                    selectedContainerColor = Primary,
                                    selectedLabelColor = OnPrimary
                                )
                            )
                        }
                        items(LivretCategory.values()) { category ->
                            val isSelected = uiState.livretSelectedCategory == category
                            FilterChip(
                                selected = isSelected,
                                onClick = {
                                    viewModel.setLivretCategory(if (isSelected) null else category)
                                },
                                label = { Text(category.displayName, style = MaterialTheme.typography.labelSmall) },
                                colors = FilterChipDefaults.filterChipColors(
                                    selectedContainerColor = Primary,
                                    selectedLabelColor = OnPrimary
                                )
                            )
                        }
                    }
                } else {
                    // Period Chips for Historic Dates & Mnemonic Tips
                    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        // Mastery Progress Bar
                        val masteredCount = uiState.masteredDateIds.size
                        val totalDates = LivretDuCitoyenRepository.historicDates.size
                        val progressFraction = (masteredCount.toFloat() / totalDates.toFloat()).coerceIn(0f, 1f)

                        Surface(
                            shape = RoundedCornerShape(10.dp),
                            color = if (isDark) Color(0xFF2C2116) else Color(0xFFFDF7EE),
                            border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE88E38).copy(alpha = if (isDark) 0.45f else 0.3f)),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 10.dp, vertical = 6.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Lightbulb,
                                        contentDescription = null,
                                        tint = Color(0xFFE58B24),
                                        modifier = Modifier.size(16.dp)
                                    )
                                    Text(
                                        text = "Mémorisation des dates : $masteredCount / $totalDates retenues",
                                        style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                                        color = if (isDark) Color(0xFFFDE68A) else Color(0xFF78350F)
                                    )
                                }
                                LinearProgressIndicator(
                                    progress = { progressFraction },
                                    modifier = Modifier
                                        .width(90.dp)
                                        .height(6.dp)
                                        .clip(CircleShape),
                                    color = Color(0xFFD97706),
                                    trackColor = if (isDark) Color(0xFF453018) else Color(0xFFFDE68A)
                                )
                            }
                        }

                        // Periods Filter Row
                        LazyRow(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            item {
                                FilterChip(
                                    selected = uiState.livretSelectedPeriod == null,
                                    onClick = { viewModel.setLivretPeriod(null) },
                                    label = { Text("Toutes les époques", style = MaterialTheme.typography.labelSmall) },
                                    colors = FilterChipDefaults.filterChipColors(
                                        selectedContainerColor = Color(0xFFD97706),
                                        selectedLabelColor = Color.White
                                    )
                                )
                            }
                            items(HistoricPeriod.values()) { period ->
                                val isSelected = uiState.livretSelectedPeriod == period
                                FilterChip(
                                    selected = isSelected,
                                    onClick = {
                                        viewModel.setLivretPeriod(if (isSelected) null else period)
                                    },
                                    label = { Text(period.displayName, style = MaterialTheme.typography.labelSmall) },
                                    colors = FilterChipDefaults.filterChipColors(
                                        selectedContainerColor = Color(0xFFD97706),
                                        selectedLabelColor = Color.White
                                    )
                                )
                            }
                        }
                    }
                }

                // Main Content List
                Box(modifier = Modifier.weight(1f)) {
                    if (activeTab == 0) {
                        // TAB 0: LIVRET ARTICLES
                        if (filteredChapters.isEmpty()) {
                            EmptySearchNotice(query = uiState.livretSearchQuery)
                        } else {
                            LazyColumn(
                                modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.spacedBy(14.dp)
                            ) {
                                items(filteredChapters, key = { it.id }) { chapter ->
                                    val isExpanded = expandedChapters[chapter.id] ?: true
                                    ChapterCard(
                                        chapter = chapter,
                                        isExpanded = isExpanded,
                                        isDark = isDark,
                                        onToggleExpand = {
                                            expandedChapters[chapter.id] = !isExpanded
                                        },
                                        onSelectRelatedDate = { dateId ->
                                            val targetDate = LivretDuCitoyenRepository.historicDates.find { it.id == dateId }
                                            if (targetDate != null) {
                                                activeTab = 1
                                                viewModel.setLivretPeriod(targetDate.period)
                                                viewModel.setLivretSearchQuery(targetDate.exactDate)
                                            }
                                        }
                                    )
                                }
                            }
                        }
                    } else {
                        // TAB 1: HISTORIC DATES WITH MNEMONIC TIPS
                        if (filteredDates.isEmpty()) {
                            EmptySearchNotice(query = uiState.livretSearchQuery)
                        } else {
                            LazyColumn(
                                modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                items(filteredDates, key = { it.id }) { dateItem ->
                                    val isMastered = uiState.masteredDateIds.contains(dateItem.id)
                                    val isSpeaking = uiState.speakingDateId == dateItem.id

                                    HistoricDateCard(
                                        date = dateItem,
                                        isMastered = isMastered,
                                        isSpeaking = isSpeaking,
                                        isDark = isDark,
                                        onToggleMastered = { viewModel.toggleDateMastered(dateItem.id) },
                                        onToggleAudio = { viewModel.speakHistoricDate(dateItem) }
                                    )
                                }
                            }
                        }
                    }
                }

                // Bottom Status Footer
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = SurfaceContainerLow,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 14.dp, vertical = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = if (activeTab == 0) "${filteredChapters.size} chapitres affichés" else "${filteredDates.size} dates historiques avec astuces",
                            style = MaterialTheme.typography.labelSmall,
                            color = OnSurfaceVariant
                        )

                        Text(
                            text = "Conforme Ministère de l'Intérieur",
                            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Medium),
                            color = Primary
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ChapterCard(
    chapter: LivretChapter,
    isExpanded: Boolean,
    isDark: Boolean = false,
    onToggleExpand: () -> Unit,
    onSelectRelatedDate: (Int) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = SurfaceContainerLow),
        border = androidx.compose.foundation.BorderStroke(1.dp, SurfaceContainerHigh)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            // Chapter Header Clickable
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onToggleExpand() }
                    .padding(14.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier.weight(1f),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .clip(CircleShape)
                            .background(PrimaryFixed),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "${chapter.number}",
                            style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
                            color = Primary
                        )
                    }

                    Column {
                        Text(
                            text = chapter.title,
                            style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                            color = Primary
                        )
                        Text(
                            text = chapter.subtitle,
                            style = MaterialTheme.typography.bodySmall,
                            color = OnSurfaceVariant,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }

                Icon(
                    imageVector = if (isExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                    contentDescription = if (isExpanded) "Replier" else "Déplier",
                    tint = OnSurfaceVariant
                )
            }

            AnimatedVisibility(visible = isExpanded) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 14.dp)
                        .padding(bottom = 14.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    chapter.articles.forEach { article ->
                        ArticleContentBlock(article = article)
                    }

                    // Related Dates Shortcuts
                    if (chapter.relatedDateIds.isNotEmpty()) {
                        Surface(
                            shape = RoundedCornerShape(10.dp),
                            color = if (isDark) Color(0xFF2C2116) else Color(0xFFFDF7EE),
                            border = androidx.compose.foundation.BorderStroke(
                                1.dp,
                                Color(0xFFE88E38).copy(alpha = if (isDark) 0.45f else 0.25f)
                            )
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp),
                                verticalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Lightbulb,
                                        contentDescription = null,
                                        tint = Color(0xFFE58B24),
                                        modifier = Modifier.size(16.dp)
                                    )
                                    Text(
                                        text = "Dates clés associées à ce chapitre :",
                                        style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                                        color = if (isDark) Color(0xFFFDE68A) else Color(0xFF78350F)
                                    )
                                }

                                Row(
                                    modifier = Modifier.horizontalScroll(rememberScrollState()),
                                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                                ) {
                                    chapter.relatedDateIds.forEach { dateId ->
                                        val date = LivretDuCitoyenRepository.historicDates.find { it.id == dateId }
                                        if (date != null) {
                                            Surface(
                                                shape = RoundedCornerShape(6.dp),
                                                color = if (isDark) Color(0xFF453018) else Color(0xFFFEF3C7),
                                                modifier = Modifier.clickable { onSelectRelatedDate(dateId) }
                                            ) {
                                                Row(
                                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                                    verticalAlignment = Alignment.CenterVertically,
                                                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                                                ) {
                                                    Text(
                                                        text = "${date.year} (${date.title})",
                                                        style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.SemiBold),
                                                        color = if (isDark) Color(0xFFFDE68A) else Color(0xFF92400E)
                                                    )
                                                    Icon(
                                                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                                        contentDescription = null,
                                                        tint = if (isDark) Color(0xFFFDE68A) else Color(0xFF92400E),
                                                        modifier = Modifier.size(12.dp)
                                                    )
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ArticleContentBlock(article: LivretArticle) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = SurfaceContainerLowest,
        border = androidx.compose.foundation.BorderStroke(1.dp, SurfaceContainerHigh)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = article.title,
                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                color = OnSurface
            )

            // Optional Image
            if (!article.imageUrl.isNullOrBlank()) {
                AsyncImage(
                    model = article.imageUrl,
                    contentDescription = article.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(130.dp)
                        .clip(RoundedCornerShape(8.dp))
                )
            }

            // Legal Quote callout
            if (!article.officialQuote.isNullOrBlank()) {
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = PrimaryFixed.copy(alpha = 0.5f),
                    border = androidx.compose.foundation.BorderStroke(1.dp, Primary.copy(alpha = 0.3f))
                ) {
                    Column(modifier = Modifier.padding(10.dp)) {
                        Text(
                            text = article.officialQuote,
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontStyle = FontStyle.Italic,
                                fontWeight = FontWeight.Medium
                            ),
                            color = Primary
                        )
                        if (!article.legalReference.isNullOrBlank()) {
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = "— ${article.legalReference}",
                                style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp),
                                color = OnSurfaceVariant
                            )
                        }
                    }
                }
            }

            Text(
                text = article.content,
                style = MaterialTheme.typography.bodySmall.copy(lineHeight = 18.sp),
                color = OnSurface
            )

            if (article.keyPoints.isNotEmpty()) {
                Column(
                    modifier = Modifier.padding(top = 4.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = "Points clés à retenir :",
                        style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                        color = Secondary
                    )
                    article.keyPoints.forEach { point ->
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(6.dp),
                            verticalAlignment = Alignment.Top
                        ) {
                            Text(
                                text = "•",
                                style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold),
                                color = Primary
                            )
                            Text(
                                text = point,
                                style = MaterialTheme.typography.bodySmall.copy(lineHeight = 16.sp),
                                color = OnSurfaceVariant
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun HistoricDateCard(
    date: HistoricDateMnemonic,
    isMastered: Boolean,
    isSpeaking: Boolean,
    isDark: Boolean = false,
    onToggleMastered: () -> Unit,
    onToggleAudio: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .testTag("card_historic_date_${date.id}"),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isMastered) (if (isDark) Color(0xFF142B21) else Color(0xFFF4FBF7)) else SurfaceContainerLow
        ),
        border = androidx.compose.foundation.BorderStroke(
            width = if (isMastered) 1.5.dp else 1.dp,
            color = if (isMastered) Color(0xFF10B981).copy(alpha = 0.5f) else SurfaceContainerHigh
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            // Top Row: Year badge & Period pill & Audio & Bookmark
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = Primary
                    ) {
                        Text(
                            text = "${date.year}",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = OnPrimary
                            ),
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                        )
                    }

                    Text(
                        text = date.exactDate,
                        style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold),
                        color = Primary
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    // Audio TTS Button
                    IconButton(
                        onClick = onToggleAudio,
                        modifier = Modifier
                            .size(34.dp)
                            .clip(CircleShape)
                            .background(if (isSpeaking) Primary else SurfaceContainer)
                            .testTag("btn_speak_date_${date.id}")
                    ) {
                        Icon(
                            imageVector = if (isSpeaking) Icons.Default.Stop else Icons.AutoMirrored.Filled.VolumeUp,
                            contentDescription = if (isSpeaking) "Arrêter" else "Écouter la date",
                            tint = if (isSpeaking) OnPrimary else Primary,
                            modifier = Modifier.size(16.dp)
                        )
                    }

                    // Mastered Checkbox Button
                    IconButton(
                        onClick = onToggleMastered,
                        modifier = Modifier
                            .size(34.dp)
                            .clip(CircleShape)
                            .background(if (isMastered) Color(0xFF10B981) else SurfaceContainer)
                            .testTag("btn_master_date_${date.id}")
                    ) {
                        Icon(
                            imageVector = if (isMastered) Icons.Default.Check else Icons.Default.CheckCircle,
                            contentDescription = if (isMastered) "Mémorisée" else "Marquer comme mémorisée",
                            tint = if (isMastered) Color.White else OnSurfaceVariant,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            }

            // Title
            Text(
                text = date.title,
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    lineHeight = 20.sp
                ),
                color = OnSurface
            )

            // Historical Summary
            Text(
                text = date.summary,
                style = MaterialTheme.typography.bodySmall.copy(lineHeight = 18.sp),
                color = OnSurfaceVariant
            )

            // HIGHLIGHTED MNEMONIC TIP BOX (Astuce Mnémonique pour retenir la date)
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = if (isDark) Color(0xFF2C2116) else Color(0xFFFDF7EE),
                border = androidx.compose.foundation.BorderStroke(
                    1.dp,
                    Color(0xFFE88E38).copy(alpha = if (isDark) 0.5f else 0.45f)
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    Box(
                        modifier = Modifier
                            .size(28.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFE88E38).copy(alpha = 0.2f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Lightbulb,
                            contentDescription = null,
                            tint = Color(0xFFE58B24),
                            modifier = Modifier.size(16.dp)
                        )
                    }

                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(3.dp)
                    ) {
                        Text(
                            text = "ASTUCE MNÉMONIQUE POUR RETENIR",
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 0.8.sp,
                                fontSize = 10.sp
                            ),
                            color = if (isDark) Color(0xFFFBBF24) else Color(0xFFB45309)
                        )
                        Text(
                            text = date.mnemonicTip,
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontWeight = FontWeight.SemiBold,
                                lineHeight = 18.sp
                            ),
                            color = if (isDark) Color(0xFFFDF1E6) else Color(0xFF78350F)
                        )
                    }
                }
            }

            // Official Context for Prefecture Interview
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Verified,
                    contentDescription = null,
                    tint = Secondary,
                    modifier = Modifier.size(15.dp)
                )
                Text(
                    text = "En préfecture : ${date.officialContext}",
                    style = MaterialTheme.typography.labelSmall.copy(lineHeight = 15.sp),
                    color = Secondary
                )
            }

            // Tags row
            if (date.tags.isNotEmpty()) {
                Row(
                    modifier = Modifier.horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    date.tags.forEach { tag ->
                        Surface(
                            shape = RoundedCornerShape(9999.dp),
                            color = SurfaceContainerHigh
                        ) {
                            Text(
                                text = "#$tag",
                                style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp),
                                color = OnSurfaceVariant,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun EmptySearchNotice(query: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Icon(
                imageVector = Icons.Default.FilterList,
                contentDescription = null,
                tint = OnSurfaceVariant,
                modifier = Modifier.size(36.dp)
            )
            Text(
                text = "Aucun résultat pour « $query »",
                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                color = OnSurface
            )
            Text(
                text = "Essayez un autre terme : 1789, IVG, 1958, laïcité, de Gaulle...",
                style = MaterialTheme.typography.bodySmall,
                color = OnSurfaceVariant,
                textAlign = TextAlign.Center
            )
        }
    }
}
