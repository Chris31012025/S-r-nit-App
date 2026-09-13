package com.example.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme =
  darkColorScheme(
    primary = DarkCivicPalette.primary,
    onPrimary = DarkCivicPalette.onPrimary,
    primaryContainer = DarkCivicPalette.primaryContainer,
    onPrimaryContainer = DarkCivicPalette.onPrimaryContainer,
    inversePrimary = DarkCivicPalette.primaryFixedDim,
    secondary = DarkCivicPalette.secondary,
    onSecondary = DarkCivicPalette.onSecondary,
    secondaryContainer = DarkCivicPalette.secondaryContainer,
    onSecondaryContainer = DarkCivicPalette.onSecondaryContainer,
    tertiary = DarkCivicPalette.tertiary,
    onTertiary = DarkCivicPalette.onTertiary,
    tertiaryContainer = DarkCivicPalette.tertiaryContainer,
    onTertiaryContainer = DarkCivicPalette.onTertiaryContainer,
    background = DarkCivicPalette.surface,
    onBackground = DarkCivicPalette.onSurface,
    surface = DarkCivicPalette.surface,
    onSurface = DarkCivicPalette.onSurface,
    surfaceVariant = DarkCivicPalette.surfaceVariant,
    onSurfaceVariant = DarkCivicPalette.onSurfaceVariant,
    outline = DarkCivicPalette.outline,
    outlineVariant = DarkCivicPalette.outlineVariant,
  )

private val LightColorScheme =
  lightColorScheme(
    primary = LightCivicPalette.primary,
    onPrimary = LightCivicPalette.onPrimary,
    primaryContainer = LightCivicPalette.primaryContainer,
    onPrimaryContainer = LightCivicPalette.onPrimaryContainer,
    inversePrimary = LightCivicPalette.primaryFixedDim,
    secondary = LightCivicPalette.secondary,
    onSecondary = LightCivicPalette.onSecondary,
    secondaryContainer = LightCivicPalette.secondaryContainer,
    onSecondaryContainer = LightCivicPalette.onSecondaryContainer,
    tertiary = LightCivicPalette.tertiary,
    onTertiary = LightCivicPalette.onTertiary,
    tertiaryContainer = LightCivicPalette.tertiaryContainer,
    onTertiaryContainer = LightCivicPalette.onTertiaryContainer,
    background = LightCivicPalette.surface,
    onBackground = LightCivicPalette.onSurface,
    surface = LightCivicPalette.surface,
    onSurface = LightCivicPalette.onSurface,
    surfaceVariant = LightCivicPalette.surfaceVariant,
    onSurfaceVariant = LightCivicPalette.onSurfaceVariant,
    outline = LightCivicPalette.outline,
    outlineVariant = LightCivicPalette.outlineVariant,
  )

@Composable
fun MyApplicationTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  dynamicColor: Boolean = false,
  content: @Composable () -> Unit,
) {
  val palette = if (darkTheme) DarkCivicPalette else LightCivicPalette

  val colorScheme =
    when {
      dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
        val context = LocalContext.current
        if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
      }

      darkTheme -> DarkColorScheme
      else -> LightColorScheme
    }

  CompositionLocalProvider(LocalCivicPalette provides palette) {
    MaterialTheme(colorScheme = colorScheme, typography = Typography, content = content)
  }
}

