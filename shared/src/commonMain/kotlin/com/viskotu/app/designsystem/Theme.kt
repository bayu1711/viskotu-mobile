package com.viskotu.app.designsystem

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

/**
 * Light color scheme based on Viskotu Web tokens.
 */
private val LightColorScheme = lightColorScheme(
    primary = ViskotuColors.Primary500,
    onPrimary = ViskotuColors.White,
    primaryContainer = ViskotuColors.Primary50,
    onPrimaryContainer = ViskotuColors.Primary900,
    secondary = ViskotuColors.Neutral800,
    onSecondary = ViskotuColors.White,
    secondaryContainer = ViskotuColors.Neutral100,
    onSecondaryContainer = ViskotuColors.Neutral900,
    background = ViskotuColors.White,
    onBackground = ViskotuColors.Neutral900,
    surface = ViskotuColors.White,
    onSurface = ViskotuColors.Neutral900,
    surfaceVariant = ViskotuColors.Neutral100,
    onSurfaceVariant = ViskotuColors.Neutral600,
    outline = ViskotuColors.Neutral200,
    outlineVariant = ViskotuColors.Neutral300,
    error = ViskotuColors.Error500,
    onError = ViskotuColors.White
)

/**
 * Viskotu Theme wrapping Material3 with web design tokens (`tokens.ts` & `index.css`).
 */
@Composable
fun ViskotuTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = ViskotuTypography,
        shapes = ViskotuShapes,
        content = content
    )
}
