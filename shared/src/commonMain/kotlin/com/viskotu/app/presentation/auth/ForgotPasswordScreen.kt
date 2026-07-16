package com.viskotu.app.presentation.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.viskotu.app.designsystem.ViskotuColors
import com.viskotu.app.designsystem.ViskotuTypography
import com.viskotu.app.presentation.components.ViskotuButton
import com.viskotu.app.presentation.components.ViskotuInput

/**
 * Forgot Password UI matching `viskotu/src/pages/auth/ForgotPasswordPage.tsx`.
 * Features an Email input state and a Success Confirmation card state (`isSuccess`).
 */
@Composable
fun ForgotPasswordScreen(
    email: String,
    onEmailChange: (String) -> Unit,
    onSubmit: () -> Unit,
    onBackToLogin: () -> Unit,
    isLoading: Boolean = false,
    errorMessage: String? = null,
    isSuccess: Boolean = false,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = ViskotuColors.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 40.dp),
            horizontalAlignment = Alignment.Start
        ) {
            // Back button link
            Text(
                text = "← Back to sign in",
                style = ViskotuTypography.labelLarge.copy(color = ViskotuColors.Primary600),
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .clickable { onBackToLogin() }
                    .padding(vertical = 8.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            if (!isSuccess) {
                // State 1: Email Input Form
                Text(
                    text = "Reset password",
                    style = ViskotuTypography.headlineLarge,
                    color = ViskotuColors.Neutral900
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "Enter your email address associated with your AdManager account and we will send you a link to reset your password.",
                    style = ViskotuTypography.bodyLarge,
                    color = ViskotuColors.Neutral600
                )

                Spacer(modifier = Modifier.height(32.dp))

                ViskotuInput(
                    value = email,
                    onValueChange = onEmailChange,
                    label = "Email address",
                    placeholder = "jamie@mainstreetcafe.com",
                    errorMessage = errorMessage
                )

                Spacer(modifier = Modifier.height(28.dp))

                ViskotuButton(
                    text = "Send reset link",
                    onClick = onSubmit,
                    isLoading = isLoading
                )
            } else {
                // State 2: Success Confirmation Box
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .background(ViskotuColors.Primary50)
                        .padding(24.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "Check your email",
                            style = ViskotuTypography.headlineMedium,
                            color = ViskotuColors.Neutral900
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = "We have sent password reset instructions to:",
                            style = ViskotuTypography.bodyMedium,
                            color = ViskotuColors.Neutral600,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = email,
                            style = ViskotuTypography.labelLarge.copy(fontWeight = FontWeight.Bold),
                            color = ViskotuColors.Neutral900
                        )
                        Spacer(modifier = Modifier.height(24.dp))
                        ViskotuButton(
                            text = "Back to sign in",
                            onClick = onBackToLogin
                        )
                    }
                }
            }
        }
    }
}
