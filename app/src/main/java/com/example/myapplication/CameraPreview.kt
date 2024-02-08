import android.Manifest
import android.content.pm.PackageManager
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.example.myapplication.R
import com.example.myapplication.ui.theme.LightGreyText
import com.example.myapplication.ui.theme.Orange
import java.io.File
import java.util.concurrent.ExecutionException


@Composable
fun CameraPreview(
    modifier : Modifier = Modifier,
    cameraSelector: CameraSelector = CameraSelector.DEFAULT_BACK_CAMERA,
    implementationMode: PreviewView.ImplementationMode = PreviewView.ImplementationMode.COMPATIBLE,
    scaleType: PreviewView.ScaleType = PreviewView.ScaleType.FILL_CENTER,
    imageAnalysis: ImageAnalysis? = null,
    imageCapture: ImageCapture? = null,
    preview: Preview = remember { Preview.Builder().build() },
    enableTorch: Boolean = false,
    focusOnTap: Boolean = false
) {
    var isCameraPermissionGranted by remember { mutableStateOf(false) }
    var isCapturingImage by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            Log.d("CameraPreview", "CAMERA PERMISSION GRANTED")
            isCameraPermissionGranted = true
        } else {
            Log.d("CameraPreview", "CAMERA PERMISSION DENIED")
        }
    }

    LaunchedEffect(context) {
        val camera = Manifest.permission.CAMERA
        when (PackageManager.PERMISSION_GRANTED) {
            ContextCompat.checkSelfPermission(
                context,
                camera
            ) -> {
                Log.d("CameraPreview", "Camera permission already granted")
                isCameraPermissionGranted = true
            }
            else -> {
                launcher.launch(camera)
            }
        }
    }

    val cameraProviderFuture = ProcessCameraProvider.getInstance(context)

    val cameraProvider by produceState<ProcessCameraProvider?>(initialValue = null) {
        value = try {
            cameraProviderFuture.get()
        } catch (e: ExecutionException) {
            // Обработка ошибки, если не удалось получить объект ProcessCameraProvider
            null
        }
    }

    val camera = remember(cameraProvider) {
        cameraProvider?.let {
            it.unbindAll()
            it.bindToLifecycle(
                lifecycleOwner,
                cameraSelector,
                *listOfNotNull(imageAnalysis, imageCapture, preview).toTypedArray()
            )
        }
    }

    LaunchedEffect(camera, enableTorch) {
        camera?.let {
            if (it.cameraInfo.hasFlashUnit()) {
                it.cameraControl.enableTorch(enableTorch)
            }
        }
    }

    Column(modifier = modifier.fillMaxWidth().fillMaxHeight(0.8f), verticalArrangement = Arrangement.Bottom, horizontalAlignment = Alignment.CenterHorizontally) {
        AndroidView(
            modifier = modifier
                .weight(1f)
                .pointerInput(camera, focusOnTap) {
                    if (!focusOnTap) return@pointerInput

                    detectTapGestures {
                        val meteringPointFactory = SurfaceOrientedMeteringPointFactory(
                            size.width.toFloat(),
                            size.height.toFloat()
                        )
                        val meteringAction = FocusMeteringAction.Builder(
                            meteringPointFactory.createPoint(it.x, it.y),
                            FocusMeteringAction.FLAG_AF
                        ).disableAutoCancel().build()

                        camera?.cameraControl?.startFocusAndMetering(meteringAction)
                    }
                },
            factory = { _ ->
                PreviewView(context).also {
                    it.scaleType = scaleType
                    it.implementationMode = implementationMode
                    preview.setSurfaceProvider(it.surfaceProvider)
                }
            }
        )

        Button(
            onClick = {
                isCapturingImage = true
                val file = File(context.filesDir, "${System.currentTimeMillis()}.jpg")
                val outputFileOptions = ImageCapture.OutputFileOptions.Builder(file).build()
                imageCapture?.takePicture(outputFileOptions, ContextCompat.getMainExecutor(context), object : ImageCapture.OnImageSavedCallback {
                    override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                        isCapturingImage = false
                        Toast.makeText(context, "Image captured successfully", Toast.LENGTH_SHORT).show()
                        Log.d("CameraPreview", "File path: ${file.path}")
                    }

                    override fun onError(exception: ImageCaptureException) {
                        isCapturingImage = false
                        Toast.makeText(context, "Error capturing image", Toast.LENGTH_SHORT).show()
                        Log.e("CameraPreview", "Error capturing image: ${exception.message}", exception)
                    }
                })
            },
            modifier = Modifier
                .padding(16.dp)
                .size(80.dp),
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(contentColor = LightGreyText, containerColor = Orange)
        ) {
            Icon(painter = painterResource(R.drawable.circle),
                contentDescription = "Take Picture")
        }
    }
}
