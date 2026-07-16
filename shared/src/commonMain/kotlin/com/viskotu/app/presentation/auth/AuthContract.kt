package com.viskotu.app.presentation.auth

/**
 * MVI (Model-View-Intent) Contract for the Login Screen.
 * Demonstrates clean architecture separation required for EY Senior KMM roles.
 */
data class LoginState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val isPasswordVisible: Boolean = false
)

sealed interface LoginIntent {
    data class OnEmailChanged(val email: String) : LoginIntent
    data class OnPasswordChanged(val password: String) : LoginIntent
    object OnTogglePasswordVisibility : LoginIntent
    object OnSubmitLogin : LoginIntent
    object OnContinueWithGoogle : LoginIntent
    object OnContinueWithApple : LoginIntent
    object OnForgotPasswordClicked : LoginIntent
    object OnSignUpClicked : LoginIntent
}

sealed interface LoginEffect {
    object NavigateToDashboard : LoginEffect
    data class ShowSnackbar(val message: String) : LoginEffect
}
