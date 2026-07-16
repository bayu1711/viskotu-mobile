package com.viskotu.app.presentation.auth

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.viskotu.app.designsystem.ViskotuColors
import com.viskotu.app.designsystem.ViskotuTypography

/**
 * Pixel-Perfect Login Screen matching the Seepra for Spaces / Viskotu Prototype.
 * Demonstrates clean MVI handling, custom design token integration (`tokens.ts`),
 * and enterprise password security UX.
 */
@Composable
fun LoginScreen(
    state: LoginState,
    onIntent: (LoginIntent) -> Unit,
    modifier: Modifier = Modifier
) {
    val focusManager = LocalFocusManager.current
    val scrollState = rememberScrollState()

    Surface(
        modifier = modifier.fillMaxSize(),
        color = ViskotuColors.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(horizontal = 24.dp, vertical = 32.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // Top Circular Brand Badge (Dark Blue/Grey circle with building icon)
            BrandBuildingBadge()

            Spacer(modifier = Modifier.height(28.dp))

            // Over-Title: SEEPRA FOR SPACES
            Text(
                text = "SEEPRA FOR SPACES",
                style = ViskotuTypography.labelMedium,
                color = ViskotuColors.Primary600
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Title: Welcome back
            Text(
                text = "Welcome back",
                style = ViskotuTypography.headlineLarge,
                color = ViskotuColors.Neutral900
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Subtitle
            Text(
                text = "Manage your screen network and keep every proof of play on track.",
                style = ViskotuTypography.bodyLarge,
                color = ViskotuColors.Neutral600
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Email Field
            Text(
                text = "Email address",
                style = ViskotuTypography.labelLarge,
                color = ViskotuColors.Neutral900
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = state.email,
                onValueChange = { onIntent(LoginIntent.OnEmailChanged(it)) },
                placeholder = {
                    Text(
                        text = "jamie@mainstreetcafe.com",
                        style = ViskotuTypography.bodyMedium,
                        color = ViskotuColors.Neutral400
                    )
                },
                leadingIcon = {
                    EnvelopeIcon(color = ViskotuColors.Neutral500)
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                shape = RoundedCornerShape(16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = ViskotuColors.Primary500,
                    unfocusedBorderColor = ViskotuColors.Neutral200,
                    focusedContainerColor = ViskotuColors.White,
                    unfocusedContainerColor = ViskotuColors.White,
                    cursorColor = ViskotuColors.Primary500,
                    focusedTextColor = ViskotuColors.Neutral900,
                    unfocusedTextColor = ViskotuColors.Neutral900
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                )
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Password Field
            Text(
                text = "Password",
                style = ViskotuTypography.labelLarge,
                color = ViskotuColors.Neutral900
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = state.password,
                onValueChange = { onIntent(LoginIntent.OnPasswordChanged(it)) },
                placeholder = {
                    Text(
                        text = "Enter your password",
                        style = ViskotuTypography.bodyMedium,
                        color = ViskotuColors.Neutral400
                    )
                },
                leadingIcon = {
                    LockIcon(color = ViskotuColors.Neutral500)
                },
                trailingIcon = {
                    val iconColor = if (state.isPasswordVisible) ViskotuColors.Primary500 else ViskotuColors.Neutral400
                    EyeIcon(
                        visible = state.isPasswordVisible,
                        color = iconColor,
                        modifier = Modifier
                            .clip(CircleShape)
                            .clickable { onIntent(LoginIntent.OnTogglePasswordVisibility) }
                            .padding(8.dp)
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                shape = RoundedCornerShape(16.dp),
                visualTransformation = if (state.isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = ViskotuColors.Primary500,
                    unfocusedBorderColor = ViskotuColors.Neutral200,
                    focusedContainerColor = ViskotuColors.White,
                    unfocusedContainerColor = ViskotuColors.White,
                    cursorColor = ViskotuColors.Primary500,
                    focusedTextColor = ViskotuColors.Neutral900,
                    unfocusedTextColor = ViskotuColors.Neutral900
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        focusManager.clearFocus()
                        onIntent(LoginIntent.OnSubmitLogin)
                    }
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Forgot Password?
            Text(
                text = "Forgot password?",
                style = ViskotuTypography.labelLarge.copy(color = ViskotuColors.Primary600),
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .clickable { onIntent(LoginIntent.OnForgotPasswordClicked) }
                    .padding(vertical = 4.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Sign In Button
            Button(
                onClick = {
                    focusManager.clearFocus()
                    onIntent(LoginIntent.OnSubmitLogin)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = ViskotuColors.Primary500,
                    contentColor = ViskotuColors.White
                ),
                enabled = !state.isLoading
            ) {
                if (state.isLoading) {
                    CircularProgressIndicator(
                        color = ViskotuColors.White,
                        modifier = Modifier.size(24.dp),
                        strokeWidth = 2.5.dp
                    )
                } else {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Sign in",
                            style = ViskotuTypography.labelLarge.copy(
                                fontWeight = FontWeight.Bold,
                                color = ViskotuColors.White
                            )
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        ArrowRightIcon(color = ViskotuColors.White)
                    }
                }
            }

            Spacer(modifier = Modifier.height(28.dp))

            // OR CONTINUE WITH Divider
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                HorizontalDivider(
                    modifier = Modifier.weight(1f),
                    color = ViskotuColors.Neutral200,
                    thickness = 1.dp
                )
                Text(
                    text = "OR CONTINUE WITH",
                    style = ViskotuTypography.labelSmall.copy(
                        color = ViskotuColors.Neutral400,
                        letterSpacing = 0.8.sp
                    ),
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                HorizontalDivider(
                    modifier = Modifier.weight(1f),
                    color = ViskotuColors.Neutral200,
                    thickness = 1.dp
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Google & Apple Social Buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedButton(
                    onClick = { onIntent(LoginIntent.OnContinueWithGoogle) },
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp),
                    shape = RoundedCornerShape(16.dp),
                    border = BorderStroke(1.dp, ViskotuColors.Neutral200),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = ViskotuColors.White,
                        contentColor = ViskotuColors.Neutral900
                    )
                ) {
                    Text(
                        text = "Google",
                        style = ViskotuTypography.labelLarge.copy(fontWeight = FontWeight.SemiBold)
                    )
                }

                OutlinedButton(
                    onClick = { onIntent(LoginIntent.OnContinueWithApple) },
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp),
                    shape = RoundedCornerShape(16.dp),
                    border = BorderStroke(1.dp, ViskotuColors.Neutral200),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = ViskotuColors.White,
                        contentColor = ViskotuColors.Neutral900
                    )
                ) {
                    Text(
                        text = "Apple",
                        style = ViskotuTypography.labelLarge.copy(fontWeight = FontWeight.SemiBold)
                    )
                }
            }

            Spacer(modifier = Modifier.height(36.dp))

            // Bottom Prompt: Don't have an account?
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Don't have an account?",
                    style = ViskotuTypography.bodyMedium,
                    color = ViskotuColors.Neutral600
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Sign up on the web portal",
                    style = ViskotuTypography.labelLarge.copy(
                        color = ViskotuColors.Primary600,
                        fontWeight = FontWeight.Bold
                    ),
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .clickable { onIntent(LoginIntent.OnSignUpClicked) }
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                )
            }
        }
    }
}

// -----------------------------------------------------------------------------
// Custom Compose Multiplatform Icons & Badges (No External Asset Dependency)
// -----------------------------------------------------------------------------

@Composable
private fun BrandBuildingBadge() {
    Box(
        modifier = Modifier
            .size(54.dp)
            .clip(CircleShape)
            .background(ViskotuColors.SurfaceDarkBadge),
        contentAlignment = Alignment.Center
    ) {
        // Building / Proof of play screen badge icon drawn via Canvas
        Canvas(modifier = Modifier.size(24.dp)) {
            val strokeWidth = 2.dp.toPx()
            val w = size.width
            val h = size.height

            // Outer building frame
            drawRect(
                color = ViskotuColors.White,
                topLeft = androidx.compose.ui.geometry.Offset(w * 0.2f, h * 0.1f),
                size = androidx.compose.ui.geometry.Size(w * 0.6f, h * 0.8f),
                style = Stroke(width = strokeWidth)
            )
            // Inner grid lines representing Ad/Screen windows
            drawLine(
                color = ViskotuColors.White,
                start = androidx.compose.ui.geometry.Offset(w * 0.2f, h * 0.4f),
                end = androidx.compose.ui.geometry.Offset(w * 0.8f, h * 0.4f),
                strokeWidth = strokeWidth
            )
            drawLine(
                color = ViskotuColors.White,
                start = androidx.compose.ui.geometry.Offset(w * 0.2f, h * 0.7f),
                end = androidx.compose.ui.geometry.Offset(w * 0.8f, h * 0.7f),
                strokeWidth = strokeWidth
            )
            drawLine(
                color = ViskotuColors.White,
                start = androidx.compose.ui.geometry.Offset(w * 0.5f, h * 0.1f),
                end = androidx.compose.ui.geometry.Offset(w * 0.5f, h * 0.9f),
                strokeWidth = strokeWidth
            )
        }
    }
}

@Composable
private fun EnvelopeIcon(color: Color) {
    Canvas(modifier = Modifier.size(20.dp)) {
        val stroke = Stroke(width = 1.6.dp.toPx())
        val w = size.width
        val h = size.height
        drawRect(
            color = color,
            topLeft = androidx.compose.ui.geometry.Offset(w * 0.1f, h * 0.2f),
            size = androidx.compose.ui.geometry.Size(w * 0.8f, h * 0.6f),
            style = stroke
        )
        val path = Path().apply {
            moveTo(w * 0.1f, h * 0.2f)
            lineTo(w * 0.5f, h * 0.55f)
            lineTo(w * 0.9f, h * 0.2f)
        }
        drawPath(path, color, style = stroke)
    }
}

@Composable
private fun LockIcon(color: Color) {
    Canvas(modifier = Modifier.size(20.dp)) {
        val stroke = Stroke(width = 1.6.dp.toPx())
        val w = size.width
        val h = size.height
        // Lock body
        drawRect(
            color = color,
            topLeft = androidx.compose.ui.geometry.Offset(w * 0.2f, h * 0.45f),
            size = androidx.compose.ui.geometry.Size(w * 0.6f, h * 0.45f),
            style = stroke
        )
        // Lock shackle
        val shacklePath = Path().apply {
            moveTo(w * 0.35f, h * 0.45f)
            lineTo(w * 0.35f, h * 0.3f)
            arcTo(
                rect = androidx.compose.ui.geometry.Rect(
                    left = w * 0.35f,
                    top = h * 0.15f,
                    right = w * 0.65f,
                    bottom = h * 0.45f
                ),
                startAngleDegrees = 180f,
                sweepAngleDegrees = 180f,
                forceMoveTo = false
            )
            lineTo(w * 0.65f, h * 0.45f)
        }
        drawPath(shacklePath, color, style = stroke)
    }
}

@Composable
private fun EyeIcon(visible: Boolean, color: Color, modifier: Modifier = Modifier) {
    Canvas(modifier = modifier.size(22.dp)) {
        val stroke = Stroke(width = 1.6.dp.toPx())
        val w = size.width
        val h = size.height
        val eyeOutline = Path().apply {
            moveTo(w * 0.1f, h * 0.5f)
            quadraticBezierTo(w * 0.5f, h * 0.1f, w * 0.9f, h * 0.5f)
            quadraticBezierTo(w * 0.5f, h * 0.9f, w * 0.1f, h * 0.5f)
        }
        drawPath(eyeOutline, color, style = stroke)
        drawCircle(
            color = color,
            radius = w * 0.15f,
            center = androidx.compose.ui.geometry.Offset(w * 0.5f, h * 0.5f),
            style = stroke
        )
        if (!visible) {
            drawLine(
                color = color,
                start = androidx.compose.ui.geometry.Offset(w * 0.15f, h * 0.85f),
                end = androidx.compose.ui.geometry.Offset(w * 0.85f, h * 0.15f),
                strokeWidth = 2.dp.toPx()
            )
        }
    }
}

@Composable
private fun ArrowRightIcon(color: Color) {
    Canvas(modifier = Modifier.size(18.dp)) {
        val stroke = Stroke(width = 2.dp.toPx())
        val w = size.width
        val h = size.height
        drawLine(
            color = color,
            start = androidx.compose.ui.geometry.Offset(w * 0.1f, h * 0.5f),
            end = androidx.compose.ui.geometry.Offset(w * 0.85f, h * 0.5f),
            strokeWidth = stroke.width
        )
        val arrowPath = Path().apply {
            moveTo(w * 0.6f, h * 0.25f)
            lineTo(w * 0.85f, h * 0.5f)
            lineTo(w * 0.6f, h * 0.75f)
        }
        drawPath(arrowPath, color, style = stroke)
    }
}
