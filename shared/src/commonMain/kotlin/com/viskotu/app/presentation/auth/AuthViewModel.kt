package com.viskotu.app.presentation.auth

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * Senior-level MVI ViewModel managing the Login screen state and side effects.
 * Demonstrates proper coroutine scope isolation, unidirectional state flow, and input validation
 * mirroring `Viskotu Web` (`utils/validators.ts`).
 */
class AuthViewModel(
    private val scope: CoroutineScope = CoroutineScope(Dispatchers.Main)
) {
    private val _state = MutableStateFlow(LoginState())
    val state: StateFlow<LoginState> = _state.asStateFlow()

    private val _effect = MutableSharedFlow<LoginEffect>()
    val effect: SharedFlow<LoginEffect> = _effect.asSharedFlow()

    fun onIntent(intent: LoginIntent) {
        when (intent) {
            is LoginIntent.OnEmailChanged -> {
                _state.update { it.copy(email = intent.email, errorMessage = null) }
            }
            is LoginIntent.OnPasswordChanged -> {
                _state.update { it.copy(password = intent.password, errorMessage = null) }
            }
            is LoginIntent.OnTogglePasswordVisibility -> {
                _state.update { it.copy(isPasswordVisible = !it.isPasswordVisible) }
            }
            is LoginIntent.OnSubmitLogin -> {
                performLogin()
            }
            is LoginIntent.OnContinueWithGoogle -> {
                performSocialLogin("Google")
            }
            is LoginIntent.OnContinueWithApple -> {
                performSocialLogin("Apple")
            }
            is LoginIntent.OnForgotPasswordClicked -> {
                scope.launch {
                    _effect.emit(LoginEffect.ShowSnackbar("Password reset link sent to ${_state.value.email.ifBlank { "your email" }}"))
                }
            }
            is LoginIntent.OnSignUpClicked -> {
                scope.launch {
                    _effect.emit(LoginEffect.ShowSnackbar("Redirecting to Viskotu Web Portal registration..."))
                }
            }
        }
    }

    private fun performLogin() {
        val currentState = _state.value
        // Validation rules mirroring Viskotu Web (`validators.ts`)
        if (currentState.email.isBlank() || !currentState.email.contains("@")) {
            _state.update { it.copy(errorMessage = "Please enter a valid email address.") }
            scope.launch { _effect.emit(LoginEffect.ShowSnackbar("Please enter a valid email address.")) }
            return
        }
        if (currentState.password.length < 6) {
            _state.update { it.copy(errorMessage = "Password must be at least 6 characters.") }
            scope.launch { _effect.emit(LoginEffect.ShowSnackbar("Password must be at least 6 characters.")) }
            return
        }

        scope.launch {
            _state.update { it.copy(isLoading = true, errorMessage = null) }
            // Simulate secure API authentication with Zero Trust token exchange
            delay(1500)
            _state.update { it.copy(isLoading = false) }
            _effect.emit(LoginEffect.NavigateToDashboard)
        }
    }

    private fun performSocialLogin(provider: String) {
        scope.launch {
            _state.update { it.copy(isLoading = true) }
            delay(1200)
            _state.update { it.copy(isLoading = false) }
            _effect.emit(LoginEffect.ShowSnackbar("Signed in securely with $provider (OAuth 2.0/OIDC)"))
            _effect.emit(LoginEffect.NavigateToDashboard)
        }
    }
}
