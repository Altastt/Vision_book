package com.example.myapplication.secondScreens

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import com.example.myapplication.models.FileHelper
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import java.io.File
import com.example.myapplication.R
import com.example.myapplication.models.NavigationItems
import com.example.myapplication.ui.theme.Orange
import java.text.SimpleDateFormat
import java.util.*


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun CameraProfileScreen(context: Context, navController: NavController) {
    val fileHelper = remember {
        FileHelper(context)
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        val cameraPermissionState = rememberPermissionState(
            permission = Manifest.permission.CAMERA
        )
        Button(
            onClick = {
                cameraPermissionState.launchPermissionRequest()
            }
        ) {
            Text(text = "Permission")
        }

        Spacer(modifier = Modifier.height(30.dp))

        CameraOpen(fileHelper.getDirectory(), navController = navController)
    }
}
@Composable
fun CameraOpen(directory: File, navController: NavController) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    SimpleCameraPreview(
        navController = navController,
        modifier = Modifier.fillMaxSize(),
        context = context,
        lifecycleOwner = lifecycleOwner,
        outputDirectory = directory,
        onMediaCaptured = { url -> }
    )
}
@Composable
fun SimpleCameraPreview(
    navController: NavController,
    modifier: Modifier = Modifier,
    context: Context,
    lifecycleOwner: LifecycleOwner,
    outputDirectory: File,
    onMediaCaptured: (Uri?) -> Unit
) {
    val cameraProviderFuture = remember { ProcessCameraProvider.getInstance(context) }
    var imageCapture: ImageCapture? by remember { mutableStateOf(null) }
    var preview by remember { mutableStateOf<Preview?>(null) }
    val camera: Camera? = null
    var lensFacing by remember { mutableStateOf(CameraSelector.LENS_FACING_BACK) }
    var flashEnabled by remember { mutableStateOf(false) }
    var flashRes by remember { mutableStateOf(R.drawable.flash_on) }
    val executor = ContextCompat.getMainExecutor(context)
    var cameraSelector: CameraSelector?
    val cameraProvider = cameraProviderFuture.get()

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

                    cameraProvider.unbindAll()
                    cameraProvider.bindToLifecycle(
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
                .padding(15.dp)
                .align(Alignment.TopStart)
        ) {
            IconButton(
                onClick = {
                    navController.navigate(NavigationItems.Profile.route)
                },
                modifier = Modifier
                    .size(35.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.arrow_back),
                    contentDescription = "Back Arrow",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }

        Row (
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
                .clip(RoundedCornerShape(15.dp))
                .background(MaterialTheme.colorScheme.secondary, RoundedCornerShape(15.dp))
                .padding(0.dp)
                .align(Alignment.BottomCenter)
        ) {
            IconButton(
                onClick = {
                    camera?.let {
                        if (it.cameraInfo.hasFlashUnit()) {
                            flashEnabled = !flashEnabled
                            flashRes = if (flashEnabled) R.drawable.flash_off else
                                R.drawable.flash_on
                            it.cameraControl.enableTorch(flashEnabled)
                        }
                    }
                }
            ) {
                Icon(painter = painterResource(id = flashRes),
                    contentDescription = "",
                    modifier = Modifier.size(35.dp),
                    tint = MaterialTheme.colorScheme.primary
                    )
            }
            Button(
                onClick = {
                    val imgCapture = imageCapture ?: return@Button
                    val photoFile = File(
                        outputDirectory,
                        SimpleDateFormat("yyyyMMDD-HHmmss", Locale.US)
                            .format(System.currentTimeMillis()) + ".jpg"
                    )
                    val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()
                    imgCapture.takePicture(
                        outputOptions,
                        executor,
                        object : ImageCapture.OnImageSavedCallback {
                            override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                                onMediaCaptured(Uri.fromFile(photoFile))
                            }

                            override fun onError(exeption: ImageCaptureException) {
                                Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show()
                            }
                        }
                    )
                },
                modifier = Modifier
                    .size(70.dp)
                    .background(MaterialTheme.colorScheme.secondary, CircleShape)
                    .border(5.dp, MaterialTheme.colorScheme.primary, CircleShape),
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colorScheme.secondary),
            ) {

            }
            IconButton(
                onClick = {
                    lensFacing = if (lensFacing == CameraSelector.LENS_FACING_BACK) CameraSelector.LENS_FACING_FRONT
                    else CameraSelector.LENS_FACING_BACK

                    cameraSelector = CameraSelector.Builder()
                        .requireLensFacing(lensFacing)
                        .build()
                    cameraProvider.unbindAll()
                    cameraProvider.bindToLifecycle(
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
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

private class FaceAnalyser(): ImageAnalysis.Analyzer {
    @SuppressLint("UnsafeOptInUsageError")
    override fun analyze(image: ImageProxy) {
        val imagePic = image.image
        imagePic?.close()
    }
}








