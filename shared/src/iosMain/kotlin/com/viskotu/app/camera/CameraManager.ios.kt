package com.viskotu.app.camera

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.readBytes
import kotlinx.cinterop.refTo
import kotlinx.cinterop.reinterpret
import platform.Foundation.getBytes
import platform.UIKit.UIImage
import platform.UIKit.UIImageJPEGRepresentation
import platform.UIKit.UIImagePickerController
import platform.UIKit.UIImagePickerControllerDelegateProtocol
import platform.UIKit.UINavigationControllerDelegateProtocol
import platform.darwin.NSObject


@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun rememberCameraLauncher(onPhotoCaptured: (ByteArray?) -> Unit): CameraLauncher {
    val delegate = remember{
        object : NSObject(), UIImagePickerControllerDelegateProtocol, UINavigationControllerDelegateProtocol{
            override fun imagePickerController(
                picker: UIImagePickerController,
                didFinishPickingImage: UIImage,
                editingInfo: Map<Any?, *>?
            ) {
                val imageData = UIImageJPEGRepresentation(didFinishPickingImage, 0.9)

                if (imageData != null) {
                    // Modern KMP reads the raw C-memory pointer directly and safely!
                    val bytes = imageData.bytes?.let { pointer ->
                        val bytePointer = pointer.reinterpret<kotlinx.cinterop.ByteVar>()
                        bytePointer.readBytes(imageData.length.toInt())
                    }
                    onPhotoCaptured(bytes)
                } else {
                    onPhotoCaptured(null)
                }
                picker.dismissViewControllerAnimated(true, null)
            }
            override fun imagePickerControllerDidCancel(picker: UIImagePickerController) {
                onPhotoCaptured(null) // Send back null because no photo was taken
                picker.dismissViewControllerAnimated(true, null) // Close the camera
            }
        }
    }

    return remember {
        object : CameraLauncher {
            override fun capturePhoto() {
                val picker = UIImagePickerController()
                picker.delegate = delegate
                
                if (UIImagePickerController.isSourceTypeAvailable(platform.UIKit.UIImagePickerControllerSourceType.UIImagePickerControllerSourceTypeCamera)) {
                    picker.sourceType = platform.UIKit.UIImagePickerControllerSourceType.UIImagePickerControllerSourceTypeCamera
                } else {
                    picker.sourceType = platform.UIKit.UIImagePickerControllerSourceType.UIImagePickerControllerSourceTypePhotoLibrary
                }

                platform.UIKit.UIApplication.sharedApplication.keyWindow?.rootViewController?.presentViewController(
                    picker, animated = true, completion = null
                )
            }
        }
    }
}
