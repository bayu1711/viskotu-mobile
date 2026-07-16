package com.viskotu.app.designsystem

import androidx.compose.ui.graphics.Color

/**
 * Design System Colors mapped 1:1 from Viskotu Web (`tokens.ts` & `index.css`).
 * 
 * Primary Palette: Vibrant Orange (`#ff6b35`)
 * Neutral Palette: Deep Slate (`#0f172a` down to `#f8fafc`)
 */
object ViskotuColors {
    // Primary - Vibrant Orange Palette
    val Primary50 = Color(0xFFFFF5F0)
    val Primary100 = Color(0xFFFFE6DB)
    val Primary200 = Color(0xFFFFCCB8)
    val Primary300 = Color(0xFFFFAD8A)
    val Primary400 = Color(0xFFFF8C5F)
    val Primary500 = Color(0xFFFF6B35) // Main Brand Color
    val Primary600 = Color(0xFFF5521A) // Hover / Pressed
    val Primary700 = Color(0xFFD63F0F) // Deep Text / Accent
    val Primary800 = Color(0xFFB33310)
    val Primary900 = Color(0xFF942D13)

    // Neutral - Slate Palette
    val Neutral50 = Color(0xFFF8FAFC)  // Background Light
    val Neutral100 = Color(0xFFF1F5F9) // Surface / Input Background
    val Neutral200 = Color(0xFFE2E8F0) // Borders / Dividers
    val Neutral300 = Color(0xFFCBD5E1) // Disabled Border / Placeholder
    val Neutral400 = Color(0xFF94A3B8) // Secondary Icon / Muted Text
    val Neutral500 = Color(0xFF64748B) // Subtitle Text
    val Neutral600 = Color(0xFF475569) // Dark Subtitle Text
    val Neutral700 = Color(0xFF334155) // Body Text Dark
    val Neutral800 = Color(0xFF1E293B) // Surface Dark / Header Accent
    val Neutral900 = Color(0xFF0F172A) // Primary Heading Text (`Welcome back`)

    // Semantic Colors
    val Success500 = Color(0xFF22C55E)
    val Warning500 = Color(0xFFEAB308)
    val Error500 = Color(0xFFEF4444)
    val Info500 = Color(0xFF3B82F6)

    // Brand specific overrides & surfaces
    val White = Color(0xFFFFFFFF)
    val Black = Color(0xFF000000)
    val SurfaceDarkBadge = Color(0xFF111827) // For the top circular icon badge
}
