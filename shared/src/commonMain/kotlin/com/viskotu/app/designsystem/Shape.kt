package com.viskotu.app.designsystem

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

/**
 * Shape tokens based on `tokens.ts` border radius scale.
 * - sm: 4.dp
 * - base: 6.dp
 * - md: 8.dp
 * - lg: 12.dp (`rounded-lg`)
 * - xl: 16.dp (`rounded-xl` for Inputs and Buttons in prototype)
 * - xxl: 24.dp (`rounded-2xl`)
 * - full: CircleShape
 */
val ViskotuShapes = Shapes(
    extraSmall = RoundedCornerShape(4.dp),
    small = RoundedCornerShape(8.dp),
    medium = RoundedCornerShape(12.dp),
    large = RoundedCornerShape(16.dp), // Standard input and primary button shape
    extraLarge = RoundedCornerShape(24.dp)
)
