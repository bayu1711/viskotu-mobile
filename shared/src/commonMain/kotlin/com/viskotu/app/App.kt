package com.viskotu.app

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.viskotu.app.designsystem.ViskotuTheme
import com.viskotu.app.presentation.auth.AuthViewModel
import com.viskotu.app.presentation.auth.LoginScreen

@Composable
@Preview
fun App() {
    val authViewModel = remember { AuthViewModel() }
    val state by authViewModel.state.collectAsState()

    ViskotuTheme {
        LoginScreen(
            state = state,
            onIntent = authViewModel::onIntent
        )
    }
}