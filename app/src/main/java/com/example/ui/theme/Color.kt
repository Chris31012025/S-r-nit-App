package com.example.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class CivicPalette(
    val surface: Color,
    val surfaceDim: Color,
    val surfaceBright: Color,
    val surfaceContainerLowest: Color,
    val surfaceContainerLow: Color,
    val surfaceContainer: Color,
    val surfaceContainerHigh: Color,
    val surfaceContainerHighest: Color,
    val surfaceVariant: Color,
    val onSurface: Color,
    val onSurfaceVariant: Color,
    val outline: Color,
    val outlineVariant: Color,
    val surfaceTint: Color,
    val primary: Color,
    val onPrimary: Color,
    val primaryContainer: Color,
    val onPrimaryContainer: Color,
    val primaryFixed: Color,
    val primaryFixedDim: Color,
    val onPrimaryFixed: Color,
    val onPrimaryFixedVariant: Color,
    val secondary: Color,
    val onSecondary: Color,
    val secondaryContainer: Color,
    val onSecondaryContainer: Color,
    val secondaryFixed: Color,
    val secondaryFixedDim: Color,
    val onSecondaryFixed: Color,
    val onSecondaryFixedVariant: Color,
    val tertiary: Color,
    val onTertiary: Color,
    val tertiaryContainer: Color,
    val onTertiaryContainer: Color,
    val tertiaryFixed: Color,
    val tertiaryFixedDim: Color,
    val onTertiaryFixed: Color,
    val onTertiaryFixedVariant: Color,
    val coralAccent: Color,
    val sagePillBg: Color,
    val softLavenderBg: Color,
    val softBlueCardBg: Color,
    val softMintCardBg: Color
)

val LightCivicPalette = CivicPalette(
    surface = Color(0xFFFCF9F2),
    surfaceDim = Color(0xFFDCDAD3),
    surfaceBright = Color(0xFFFCF9F2),
    surfaceContainerLowest = Color(0xFFFFFFFF),
    surfaceContainerLow = Color(0xFFF6F3EC),
    surfaceContainer = Color(0xFFF1EEE7),
    surfaceContainerHigh = Color(0xFFEBE8E1),
    surfaceContainerHighest = Color(0xFFE5E2DB),
    surfaceVariant = Color(0xFFE5E2DB),
    onSurface = Color(0xFF1C1C18),
    onSurfaceVariant = Color(0xFF414846),
    outline = Color(0xFF717976),
    outlineVariant = Color(0xFFC1C8C5),
    surfaceTint = Color(0xFF44645F),
    primary = Color(0xFF032521),
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFF1B3B36),
    onPrimaryContainer = Color(0xFF84A59E),
    primaryFixed = Color(0xFFC7EAE2),
    primaryFixedDim = Color(0xFFABCEC6),
    onPrimaryFixed = Color(0xFF00201C),
    onPrimaryFixedVariant = Color(0xFF2D4C47),
    secondary = Color(0xFF43617C),
    onSecondary = Color(0xFFFFFFFF),
    secondaryContainer = Color(0xFFC1E0FF),
    onSecondaryContainer = Color(0xFF46647E),
    secondaryFixed = Color(0xFFCCE5FF),
    secondaryFixedDim = Color(0xFFABCAE8),
    onSecondaryFixed = Color(0xFF001D31),
    onSecondaryFixedVariant = Color(0xFF2B4963),
    tertiary = Color(0xFF430C03),
    onTertiary = Color(0xFFFFFFFF),
    tertiaryContainer = Color(0xFF602114),
    onTertiaryContainer = Color(0xFFE18672),
    tertiaryFixed = Color(0xFFFFDAD3),
    tertiaryFixedDim = Color(0xFFFFB4A4),
    onTertiaryFixed = Color(0xFF3C0701),
    onTertiaryFixedVariant = Color(0xFF763223),
    coralAccent = Color(0xFFE28773),
    sagePillBg = Color(0xFFD3E8E3),
    softLavenderBg = Color(0xFFE8E4F0),
    softBlueCardBg = Color(0xFFD8E9F8),
    softMintCardBg = Color(0xFFD4EBE4)
)

val DarkCivicPalette = CivicPalette(
    surface = Color(0xFF121514),
    surfaceDim = Color(0xFF0D100F),
    surfaceBright = Color(0xFF232826),
    surfaceContainerLowest = Color(0xFF171B19),
    surfaceContainerLow = Color(0xFF1E2321),
    surfaceContainer = Color(0xFF242A28),
    surfaceContainerHigh = Color(0xFF2B322F),
    surfaceContainerHighest = Color(0xFF343B38),
    surfaceVariant = Color(0xFF2E3532),
    onSurface = Color(0xFFEEF1EC),
    onSurfaceVariant = Color(0xFFB6C0BB),
    outline = Color(0xFF7A8581),
    outlineVariant = Color(0xFF3B4441),
    surfaceTint = Color(0xFF72D5C2),
    primary = Color(0xFF78D7C4),
    onPrimary = Color(0xFF003730),
    primaryContainer = Color(0xFF12433B),
    onPrimaryContainer = Color(0xFFA5F4E4),
    primaryFixed = Color(0xFF1F4841),
    primaryFixedDim = Color(0xFF183B35),
    onPrimaryFixed = Color(0xFFB7EFE3),
    onPrimaryFixedVariant = Color(0xFF8CD4C4),
    secondary = Color(0xFF90C4EC),
    onSecondary = Color(0xFF00334E),
    secondaryContainer = Color(0xFF193E5E),
    onSecondaryContainer = Color(0xFFC7E2FA),
    secondaryFixed = Color(0xFF1C405C),
    secondaryFixedDim = Color(0xFF16344B),
    onSecondaryFixed = Color(0xFFC3DFF8),
    onSecondaryFixedVariant = Color(0xFF92BFDE),
    tertiary = Color(0xFFFFB4A5),
    onTertiary = Color(0xFF5A1C12),
    tertiaryContainer = Color(0xFF6B2B20),
    onTertiaryContainer = Color(0xFFFFDAD3),
    tertiaryFixed = Color(0xFF4C211A),
    tertiaryFixedDim = Color(0xFF3B1812),
    onTertiaryFixed = Color(0xFFFFDAD3),
    onTertiaryFixedVariant = Color(0xFFF19787),
    coralAccent = Color(0xFFF38B76),
    sagePillBg = Color(0xFF1D3B35),
    softLavenderBg = Color(0xFF272436),
    softBlueCardBg = Color(0xFF1B2936),
    softMintCardBg = Color(0xFF173028)
)

val LocalCivicPalette = staticCompositionLocalOf { LightCivicPalette }

val Surface: Color @Composable get() = LocalCivicPalette.current.surface
val SurfaceDim: Color @Composable get() = LocalCivicPalette.current.surfaceDim
val SurfaceBright: Color @Composable get() = LocalCivicPalette.current.surfaceBright
val SurfaceContainerLowest: Color @Composable get() = LocalCivicPalette.current.surfaceContainerLowest
val SurfaceContainerLow: Color @Composable get() = LocalCivicPalette.current.surfaceContainerLow
val SurfaceContainer: Color @Composable get() = LocalCivicPalette.current.surfaceContainer
val SurfaceContainerHigh: Color @Composable get() = LocalCivicPalette.current.surfaceContainerHigh
val SurfaceContainerHighest: Color @Composable get() = LocalCivicPalette.current.surfaceContainerHighest
val SurfaceVariant: Color @Composable get() = LocalCivicPalette.current.surfaceVariant

val OnSurface: Color @Composable get() = LocalCivicPalette.current.onSurface
val OnSurfaceVariant: Color @Composable get() = LocalCivicPalette.current.onSurfaceVariant

val Outline: Color @Composable get() = LocalCivicPalette.current.outline
val OutlineVariant: Color @Composable get() = LocalCivicPalette.current.outlineVariant
val SurfaceTint: Color @Composable get() = LocalCivicPalette.current.surfaceTint

val Primary: Color @Composable get() = LocalCivicPalette.current.primary
val OnPrimary: Color @Composable get() = LocalCivicPalette.current.onPrimary
val PrimaryContainer: Color @Composable get() = LocalCivicPalette.current.primaryContainer
val OnPrimaryContainer: Color @Composable get() = LocalCivicPalette.current.onPrimaryContainer
val PrimaryFixed: Color @Composable get() = LocalCivicPalette.current.primaryFixed
val PrimaryFixedDim: Color @Composable get() = LocalCivicPalette.current.primaryFixedDim
val OnPrimaryFixed: Color @Composable get() = LocalCivicPalette.current.onPrimaryFixed
val OnPrimaryFixedVariant: Color @Composable get() = LocalCivicPalette.current.onPrimaryFixedVariant

val Secondary: Color @Composable get() = LocalCivicPalette.current.secondary
val OnSecondary: Color @Composable get() = LocalCivicPalette.current.onSecondary
val SecondaryContainer: Color @Composable get() = LocalCivicPalette.current.secondaryContainer
val OnSecondaryContainer: Color @Composable get() = LocalCivicPalette.current.onSecondaryContainer
val SecondaryFixed: Color @Composable get() = LocalCivicPalette.current.secondaryFixed
val SecondaryFixedDim: Color @Composable get() = LocalCivicPalette.current.secondaryFixedDim
val OnSecondaryFixed: Color @Composable get() = LocalCivicPalette.current.onSecondaryFixed
val OnSecondaryFixedVariant: Color @Composable get() = LocalCivicPalette.current.onSecondaryFixedVariant

val Tertiary: Color @Composable get() = LocalCivicPalette.current.tertiary
val OnTertiary: Color @Composable get() = LocalCivicPalette.current.onTertiary
val TertiaryContainer: Color @Composable get() = LocalCivicPalette.current.tertiaryContainer
val OnTertiaryContainer: Color @Composable get() = LocalCivicPalette.current.onTertiaryContainer
val TertiaryFixed: Color @Composable get() = LocalCivicPalette.current.tertiaryFixed
val TertiaryFixedDim: Color @Composable get() = LocalCivicPalette.current.tertiaryFixedDim
val OnTertiaryFixed: Color @Composable get() = LocalCivicPalette.current.onTertiaryFixed
val OnTertiaryFixedVariant: Color @Composable get() = LocalCivicPalette.current.onTertiaryFixedVariant

val CoralAccent: Color @Composable get() = LocalCivicPalette.current.coralAccent
val SagePillBg: Color @Composable get() = LocalCivicPalette.current.sagePillBg
val SoftLavenderBg: Color @Composable get() = LocalCivicPalette.current.softLavenderBg
val SoftBlueCardBg: Color @Composable get() = LocalCivicPalette.current.softBlueCardBg
val SoftMintCardBg: Color @Composable get() = LocalCivicPalette.current.softMintCardBg


