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
 * Email & MFA Verification Screen matching `viskotu/src/pages/auth/VerifyEmailPage.tsx`.
 * Supports 6-digit OTP entry and continuous verification checks required for Zero Trust auth.
 */
@Composable
fun VerifyEmailScreen(
    email: String = "jamie@mainstreetcafe.com",
    otpCode: String = "",
    onOtpChange: (String) -> Unit = {},
    onVerifySubmitted: () -> Unit = {},
    onResendCode: () -> Unit = {},
    onSkipForNow: () -> Unit = {},
    isLoading: Boolean = false,
    errorMessage: String? = null,
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
            Text(
                text = "Verify your email",
                style = ViskotuTypography.headlineLarge,
                color = ViskotuColors.Neutral900
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "We sent a 6-digit verification code to $email. Enter it below to secure and activate your workspace.",
                style = ViskotuTypography.bodyLarge,
                color = ViskotuColors.Neutral600
            )

            Spacer(modifier = Modifier.height(36.dp))

            ViskotuInput(
                value = otpCode,
                onValueChange = { if (it.length <= 6) onOtpChange(it) },
                label = "Verification Code (6 digits)",
                placeholder = "123456",
                errorMessage = errorMessage
            )

            Spacer(modifier = Modifier.height(28.dp))

            ViskotuButton(
                text = "Verify & Continue",
                onClick = onVerifySubmitted,
                isLoading = isLoading,
                enabled = otpCode.length == 6
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Resend and Skip options
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Didn't receive code? Resend",
                    style = ViskotuTypography.labelLarge.copy(color = ViskotuColors.Primary600),
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .clickable { onResendCode() }
                        .padding(4.dp)
                )

                Text(
                    text = "Skip for now",
                    style = ViskotuTypography.bodyMedium.copy(color = ViskotuColors.Neutral500),
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .clickable { onSkipForNow() }
                        .padding(4.dp)
                )
            }

            Spacer(modifier = Modifier.height(40.dp))

            // Zero Trust Security Notice Banner
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(ViskotuColors.Neutral100)
                    .padding(16.dp)
            ) {
                Column {
                    Text(
                        text = "🔒 Zero Trust Security Enforcement",
                        style = ViskotuTypography.labelLarge.copy(fontWeight = FontWeight.Bold),
                        color = ViskotuColors.Neutral800
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Verifying your email binds your device session to identity provider tokens (`EncryptedSharedPreferences` / `iOS Keychain`).",
                        style = ViskotuTypography.bodySmall,
                        color = ViskotuColors.Neutral600
                    )
                }
            }
        }
    }
}
