package com.viskotu.app.designsystem

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

/**
 * Typography definitions matching `tokens.ts` font size & weight scale.
 * Note: In Compose Multiplatform, custom font resources (like Inter) can be loaded
 * via `FontFamily(Font(Res.font.inter_regular))` or default sans-serif for initial structure.
 */
val ViskotuTypography = Typography(
    // 4xl / 3xl Heading (`Welcome back`)
    headlineLarge = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp,
        lineHeight = 38.sp,
        color = ViskotuColors.Neutral900
    ),
    // 2xl Heading
    headlineMedium = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        lineHeight = 32.sp,
        color = ViskotuColors.Neutral900
    ),
    // Section Title / Subheading
    headlineSmall = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
        lineHeight = 28.sp,
        color = ViskotuColors.Neutral900
    ),
    // Body Large (Subtitle text under Welcome back)
    bodyLarge = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        color = ViskotuColors.Neutral600
    ),
    // Body Medium (Standard form inputs & general text)
    bodyMedium = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        color = ViskotuColors.Neutral800
    ),
    // Body Small / Captions
    bodySmall = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        color = ViskotuColors.Neutral500
    ),
    // Label Large (Buttons: `Sign in`, input field labels `Email address`)
    labelLarge = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        color = ViskotuColors.Neutral900
    ),
    // Label Medium (Over-titles like `Viskotu FOR SPACES`)
    labelMedium = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 1.2.sp, // Tracking wider
        color = ViskotuColors.Primary600
    ),
    // Label Small
    labelSmall = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 14.sp,
        color = ViskotuColors.Neutral500
    )
)
