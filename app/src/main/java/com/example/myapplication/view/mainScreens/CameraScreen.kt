package com.example.myapplication.view.mainScreens

import android.content.Context
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.navigation.NavController
import com.example.myapplication.models.FileHelper
import com.example.myapplication.models.checkCameraPermission
import com.example.myapplication.view.camerasBookNProfile.CameraBook
import com.example.myapplication.view.camerasBookNProfile.CameraProfile

@Composable
fun Camera(context: Context, navController: NavController) {
    val fileHelper = remember {
        FileHelper(context)
    }
    val lifecycleOwner = LocalLifecycleOwner.current
    val previousRoute = remember(navController) {
        navController.previousBackStackEntry?.destination?.route
    }
    // Permission State
    val isCameraPermissionGranted = remember { mutableStateOf(false) }

    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            Log.d("CameraPreview", "CAMERA PERMISSION GRANTED")
            isCameraPermissionGranted.value = true
        } else {
            Log.d("CameraPreview", "CAMERA PERMISSION DENIED")
        }
    }

    // Check Camera Permission
    LaunchedEffect(context) {
        checkCameraPermission(context, launcher, isCameraPermissionGranted)
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (previousRoute == "camerainprofile") {
            CameraProfile(
                fileHelper.getDirectory(),
                navController = navController,
                context = context,
                lifecycleOwner = lifecycleOwner,
                isCameraPermissionGranted = isCameraPermissionGranted
            )
        } else {
            CameraBook(
                fileHelper.getDirectory(),
                navController = navController,
                context = context,
                lifecycleOwner = lifecycleOwner,
                isCameraPermissionGranted = isCameraPermissionGranted
            )
        }
    }
}