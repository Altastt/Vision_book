package com.example.myapplication.secondScreens

import BookCameraPreview
import android.content.Context
import android.net.Uri
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import java.io.File
import com.example.myapplication.R
import com.example.myapplication.itemsOfScreen.BackButton
import com.example.myapplication.itemsOfScreen.ButtonCaptureImage
import com.example.myapplication.models.FaceAnalyser
import java.util.concurrent.ExecutionException



@Composable
fun CameraProfile(
    directory: File,
    navController: NavController,
    context: Context,
    lifecycleOwner: LifecycleOwner,
    isCameraPermissionGranted: MutableState<Boolean>) {

    if (isCameraPermissionGranted.value) {
        ProfileCameraPreview(
            navController = navController,
            modifier = Modifier.fillMaxSize(),
            context = context,
            lifecycleOwner = lifecycleOwner,
            outputDirectory = directory,
            onMediaCaptured = { url -> }
        )
    } else {
        BackButton(navController = navController)
        CanceledPermissonScreen()
    }
}
@Composable
fun CameraBook(
    directory: File,
    navController: NavController,
    context: Context,
    lifecycleOwner: LifecycleOwner,
    isCameraPermissionGranted: MutableState<Boolean>) {

    if (isCameraPermissionGranted.value) {
        BookCameraPreview(
            navController = navController,
            modifier = Modifier.fillMaxSize(),
            context = context,
            lifecycleOwner = lifecycleOwner,
            outputDirectory = directory,
            onMediaCaptured = { url -> }
        )
    } else {
        BackButton(navController = navController)
        CanceledPermissonScreen()
    }
}
// конкретная камера
@Composable
fun ProfileCameraPreview(
    navController: NavController,
    modifier: Modifier = Modifier,
    context: Context,
    lifecycleOwner: LifecycleOwner,
    outputDirectory: File,
    onMediaCaptured: (Uri?) -> Unit
) {
    var imageCapture: ImageCapture? by remember { mutableStateOf(null) }
    var preview by remember { mutableStateOf<Preview?>(null) }
    val camera: Camera? = null
    var lensFacing by remember { mutableStateOf(CameraSelector.LENS_FACING_BACK) }
    val executor = ContextCompat.getMainExecutor(context)
    var cameraSelector: CameraSelector?

    // Camera Provider
    val cameraProviderFuture = ProcessCameraProvider.getInstance(context)

    val cameraProvider by produceState<ProcessCameraProvider?>(initialValue = null) {
        value = try {
            cameraProviderFuture.get()
        } catch (e: ExecutionException) {
            null
        }
    }
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

                    val cameraSelector = CameraSelector.Builder()
                        .requireLensFacing(CameraSelector.LENS_FACING_BACK)
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
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(25.dp)
                .align(Alignment.BottomCenter)
        ) {
            IconButton(
                onClick = {
                   // Написать логику для работы с галереей
                }
            ) {
                Icon(painter = painterResource(R.drawable.gallery),
                    contentDescription = "",
                    modifier = Modifier.size(35.dp))
            }
            ButtonCaptureImage(context, outputDirectory, onMediaCaptured, imageCapture, executor)
            IconButton(
                onClick = {
                    lensFacing = if (lensFacing == CameraSelector.LENS_FACING_BACK) CameraSelector.LENS_FACING_FRONT
                    else CameraSelector.LENS_FACING_BACK

                    cameraSelector = CameraSelector.Builder()
                        .requireLensFacing(lensFacing)
                        .build()
                    cameraProvider?.unbindAll()
                    cameraProvider?.bindToLifecycle(
                        lifecycleOwner,
                        cameraSelector as CameraSelector,
                        imageCapture,
                        preview
                    )
                }
            ) {
                Icon(
                    painter = painterResource(R.drawable.rotate),
                    contentDescription = "rotate",
                    modifier = Modifier.size(35.dp),
                )
            }
        }
    }
}













