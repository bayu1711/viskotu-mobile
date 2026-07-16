package com.viskotu.app.presentation.auth

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.viskotu.app.designsystem.ViskotuColors
import com.viskotu.app.designsystem.ViskotuTypography
import com.viskotu.app.presentation.components.ViskotuButton
import com.viskotu.app.presentation.components.ViskotuInput

enum class UserRole(val title: String, val subtitle: String) {
    ADVERTISER("Advertiser", "Create campaigns, book spaces, and track performance analytics."),
    SPACE_OWNER("Space Owner", "List unconventional ad spaces, set pricing rules, and earn revenue."),
    PRINTER("Production Partner", "Manage print queue, fulfill orders, and handle quality control.")
}

/**
 * Multi-step Register Screen matching `viskotu/src/pages/auth/SignupPage.tsx`.
 * Step 1: Role Selection (Advertiser, Space Owner, Printer).
 * Step 2: Account Details (Name, Email, Password, Confirm Password).
 */
@Composable
fun RegisterScreen(
    currentStep: Int = 1,
    selectedRole: UserRole = UserRole.ADVERTISER,
    onRoleSelected: (UserRole) -> Unit = {},
    onNextStep: () -> Unit = {},
    onPrevStep: () -> Unit = {},
    name: String = "",
    onNameChange: (String) -> Unit = {},
    email: String = "",
    onEmailChange: (String) -> Unit = {},
    password: String = "",
    onPasswordChange: (String) -> Unit = {},
    confirmPassword: String = "",
    onConfirmPasswordChange: (String) -> Unit = {},
    onSubmitRegister: () -> Unit = {},
    onGoToLogin: () -> Unit = {},
    isLoading: Boolean = false,
    errorMessage: String? = null,
    modifier: Modifier = Modifier
) {
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
            // Header & Navigation
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (currentStep > 1) {
                    Text(
                        text = "← Back to role selection",
                        style = ViskotuTypography.labelLarge.copy(color = ViskotuColors.Primary600),
                        modifier = Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .clickable { onPrevStep() }
                            .padding(vertical = 4.dp)
                    )
                } else {
                    Spacer(modifier = Modifier.width(1.dp))
                }
                Text(
                    text = "Step $currentStep of 2",
                    style = ViskotuTypography.labelSmall,
                    color = ViskotuColors.Neutral500
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            if (currentStep == 1) {
                // Step 1: Role Selection
                Text(
                    text = "Join AdManager",
                    style = ViskotuTypography.headlineLarge,
                    color = ViskotuColors.Neutral900
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Choose your primary role to customize your workspace experience.",
                    style = ViskotuTypography.bodyLarge,
                    color = ViskotuColors.Neutral600
                )

                Spacer(modifier = Modifier.height(28.dp))

                UserRole.values().forEach { role ->
                    RoleSelectionCard(
                        role = role,
                        isSelected = role == selectedRole,
                        onClick = { onRoleSelected(role) }
                    )
                    Spacer(modifier = Modifier.height(14.dp))
                }

                Spacer(modifier = Modifier.height(24.dp))

                ViskotuButton(
                    text = "Continue as ${selectedRole.title} →",
                    onClick = onNextStep
                )
            } else {
                // Step 2: Account Details
                Text(
                    text = "Create account",
                    style = ViskotuTypography.headlineLarge,
                    color = ViskotuColors.Neutral900
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Setting up your ${selectedRole.title} profile.",
                    style = ViskotuTypography.bodyLarge,
                    color = ViskotuColors.Neutral600
                )

                Spacer(modifier = Modifier.height(28.dp))

                ViskotuInput(
                    value = name,
                    onValueChange = onNameChange,
                    label = "Full Name",
                    placeholder = "Jamie Smith"
                )

                Spacer(modifier = Modifier.height(16.dp))

                ViskotuInput(
                    value = email,
                    onValueChange = onEmailChange,
                    label = "Work Email",
                    placeholder = "jamie@company.com",
                    errorMessage = errorMessage
                )

                Spacer(modifier = Modifier.height(16.dp))

                ViskotuInput(
                    value = password,
                    onValueChange = onPasswordChange,
                    label = "Password",
                    placeholder = "Create a strong password (6+ chars)",
                    visualTransformation = PasswordVisualTransformation()
                )

                Spacer(modifier = Modifier.height(16.dp))

                ViskotuInput(
                    value = confirmPassword,
                    onValueChange = onConfirmPasswordChange,
                    label = "Confirm Password",
                    placeholder = "Repeat your password",
                    visualTransformation = PasswordVisualTransformation()
                )

                Spacer(modifier = Modifier.height(28.dp))

                ViskotuButton(
                    text = "Create ${selectedRole.title} Account",
                    onClick = onSubmitRegister,
                    isLoading = isLoading
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Footer link to sign in
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Already have an account? ",
                    style = ViskotuTypography.bodyMedium,
                    color = ViskotuColors.Neutral600
                )
                Text(
                    text = "Sign in",
                    style = ViskotuTypography.labelLarge.copy(color = ViskotuColors.Primary600),
                    modifier = Modifier.clickable { onGoToLogin() }
                )
            }
        }
    }
}

@Composable
private fun RoleSelectionCard(
    role: UserRole,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val borderColor = if (isSelected) ViskotuColors.Primary500 else ViskotuColors.Neutral200
    val containerColor = if (isSelected) ViskotuColors.Primary50 else ViskotuColors.White

    Surface(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(if (isSelected) 2.dp else 1.dp, borderColor),
        color = containerColor
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = role.title,
                    style = ViskotuTypography.labelLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = if (isSelected) ViskotuColors.Primary700 else ViskotuColors.Neutral900
                    )
                )
                // Radio circle indicator
                Box(
                    modifier = Modifier
                        .size(20.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(if (isSelected) ViskotuColors.Primary500 else ViskotuColors.Neutral200),
                    contentAlignment = Alignment.Center
                ) {
                    if (isSelected) {
                        Box(
                            modifier = Modifier
                                .size(8.dp)
                                .clip(RoundedCornerShape(4.dp))
                                .background(ViskotuColors.White)
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = role.subtitle,
                style = ViskotuTypography.bodyMedium,
                color = ViskotuColors.Neutral600
            )
        }
    }
}
