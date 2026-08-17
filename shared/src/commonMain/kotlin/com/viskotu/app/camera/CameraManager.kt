package com.viskotu.app.camera

import androidx.compose.runtime.Composable

interface CameraLauncher {
    fun capturePhoto()
}


@Composable
expect fun rememberCameraLauncher(
    onPhotoCaptured : (ByteArray?) -> Unit
) : CameraLauncher