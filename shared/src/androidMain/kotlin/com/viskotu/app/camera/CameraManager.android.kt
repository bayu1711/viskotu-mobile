package com.viskotu.app.camera

import android.graphics.Bitmap
import androidx.compose.runtime.Composable
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.remember
import java.io.ByteArrayOutputStream


@Composable
actual fun rememberCameraLauncher(onPhotoCaptured: (ByteArray?) -> Unit): CameraLauncher {
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview(),
        onResult = { bitmap : Bitmap? ->
            if (bitmap!= null){
                val stream = ByteArrayOutputStream()
                bitmap.compress(Bitmap.CompressFormat.JPEG, 90, stream)
                onPhotoCaptured(stream.toByteArray())
            } else {
                onPhotoCaptured(null)
            }
        }
    )
    return remember{
        object : CameraLauncher {
            override fun capturePhoto() {
                launcher.launch(null)
            }

        }
    }
}