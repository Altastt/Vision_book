package com.example.visionbook.models

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.runtime.MutableState
import androidx.core.content.ContextCompat

fun requestCameraPermission(
    launcher: ActivityResultLauncher<String>
) {
    launcher.launch(Manifest.permission.CAMERA)
}

fun checkCameraPermission(
    context: Context,
    launcher: ActivityResultLauncher<String>,
    isCameraPermissionGranted: MutableState<Boolean>
) {
    val permissionCheck = ContextCompat.checkSelfPermission(
        context,
        Manifest.permission.CAMERA
    )
    if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
        isCameraPermissionGranted.value = true
    } else {
        requestCameraPermission(launcher)
    }
}