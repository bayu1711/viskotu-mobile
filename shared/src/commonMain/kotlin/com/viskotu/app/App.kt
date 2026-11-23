package com.viskotu.app

import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.viskotu.app.designsystem.ViskotuTheme
import com.viskotu.app.presentation.auth.AuthViewModel
import com.viskotu.app.presentation.auth.LoginScreen
import com.viskotu.app.presentation.auth.LoginEffect
import com.viskotu.app.presentation.dashboard.DashboardScreen
import kotlinx.coroutines.flow.collectLatest

@Composable
fun App() {
    // 1. Create a NavController. This is the engine that moves us between screens.
    val navController = rememberNavController()

    ViskotuTheme {
        // 2. Create the NavHost and tell it to start at the "login" screen
        NavHost(navController = navController, startDestination = "login") {

            // 3. Define the "login" screen destination
            composable("login") {
                val authViewModel = remember { AuthViewModel() }
                val state by authViewModel.state.collectAsState()

                // 4. Listen for signals (effects) coming from the AuthViewModel
                LaunchedEffect(Unit) {
                    authViewModel.effect.collectLatest { effect ->
                        when (effect) {
                            // If the ViewModel says "Login Success", tell the NavController to move to the dashboard!
                            is LoginEffect.NavigateToDashboard -> {
                                navController.navigate("dashboard")
                            }
                            is LoginEffect.ShowSnackbar -> { /* Ignore for now */ }
                        }
                    }
                }

                LoginScreen(
                    state = state,
                    onIntent = authViewModel::onIntent
                )
            }

            // 5. Define the "dashboard" screen destination
            composable("dashboard") {
                DashboardScreen()
            }
        }
    }
}