package com.example.visionbook.view.camerasBookNProfile

import android.content.Context
import android.net.Uri
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import com.example.visionbook.view.camerasBookNProfile.itemsInCameras.BackButton
import com.example.visionbook.view.camerasBookNProfile.itemsInCameras.ButtonCaptureImage
import com.example.visionbook.models.FaceAnalyser
import com.example.visionbook.view.camerasBookNProfile.secondCameraScreens.CanceledPermissonScreen
import java.io.File
import java.util.concurrent.ExecutionException
import java.util.concurrent.Executor



@Composable
fun CameraBook(
    directory: File,
    navController: NavController,
    context: Context,
    lifecycleOwner: LifecycleOwner,
    isCameraPermissionGranted: MutableState<Boolean>) {

    val camera: Camera? = null
    val executor = ContextCompat.getMainExecutor(context)
    if (isCameraPermissionGranted.value) {
        BookCameraPreview(
            navController = navController,
            modifier = Modifier.fillMaxSize(),
            context = context,
            lifecycleOwner = lifecycleOwner,
            outputDirectory = directory,
            onMediaCaptured = { url -> },
            camera = camera,
            executor = executor
        )
    } else {
        BackButton(navController = navController)
        CanceledPermissonScreen()
    }
}

@Composable
fun BookCameraPreview(
    navController: NavController,
    modifier: Modifier = Modifier,
    context: Context,
    lifecycleOwner: LifecycleOwner,
    outputDirectory: File,
    onMediaCaptured: (Uri?) -> Unit,
    camera: Camera?,
    executor: Executor,
    ) {
    var imageCapture: ImageCapture? by remember { mutableStateOf(null) }
    var preview by remember { mutableStateOf<Preview?>(null) }

    // Camera Provider
    val cameraProviderFuture = ProcessCameraProvider.getInstance(context)

    val cameraProvider by produceState<ProcessCameraProvider?>(initialValue = null) {
        value = try {
            cameraProviderFuture.get()
        } catch (e: ExecutionException) {
            null
        }
    }
    val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
    Box {
        AndroidView(
            modifier = Modifier.fillMaxSize(),
            factory = { ctx ->
                val previewView = PreviewView(ctx)
                cameraProviderFuture.addListener({
                    val imageAnalysis = ImageAnalysis.Builder()
                        .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                        .build()
                        .apply {
                            setAnalyzer(executor, FaceAnalyser())
                        }
                    imageCapture = ImageCapture.Builder()
                        .setTargetRotation(previewView.display.rotation)
                        .build()

                    cameraProvider?.unbindAll()
                    cameraProvider?.bindToLifecycle(
                        lifecycleOwner,
                        cameraSelector,
                        imageCapture,
                        preview
                    )
                }, executor)
                preview = Preview.Builder().build().also {
                    it.setSurfaceProvider(previewView.surfaceProvider)
                }
                previewView
            }
        )
        Row (
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopStart)
        ) {
            BackButton(navController = navController)
        }

        Row (
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(25.dp)
                .align(Alignment.BottomCenter)
        ) {
            ButtonCaptureImage(context, outputDirectory, onMediaCaptured, imageCapture, executor)
        }
    }
}
