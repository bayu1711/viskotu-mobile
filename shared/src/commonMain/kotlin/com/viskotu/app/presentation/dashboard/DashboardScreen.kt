package com.viskotu.app.presentation.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.viskotu.app.camera.rememberCameraLauncher
import com.viskotu.app.presentation.components.ViskotuButton


@Composable
fun DashboardScreen (){
    var photoBytes by remember { mutableStateOf<ByteArray?>(null) }

    val cameraLauncher = rememberCameraLauncher { bytes ->
        photoBytes = bytes
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Welcome"
        )
        Spacer(modifier = Modifier.height(24.dp))
        ViskotuButton(
            text = "Take Proof of Play Photo",
            onClick = { cameraLauncher.capturePhoto() }
        )

        if (photoBytes != null) {
            Text("Photo captured successfully! (${photoBytes!!.size} bytes)")
        }
    }

}