package com.viskotu.app.camera

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

@Composable
actual fun rememberCameraLauncher(onPhotoCaptured: (ByteArray?) -> Unit): CameraLauncher {
    return remember {
        object : CameraLauncher {
            override fun capturePhoto() {
                // Desktop camera not supported in this tutorial
                println("Camera not supported on Desktop yet.")
                onPhotoCaptured(null)
            }
        }
    }
}
