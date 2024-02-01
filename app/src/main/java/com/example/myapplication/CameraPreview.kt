import android.Manifest
import android.content.pm.PackageManager
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.runtime.LaunchedEffect
import androidx.core.content.ContextCompat
import coil.memory.MemoryCache
import com.example.myapplication.MainScreen
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
        when (PackageManager.PERMISSION_GRANTED) {
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.CAMERA
            ) -> {
                Log.d("CameraPreview", "Camera permission already granted")
                isCameraPermissionGranted = true
            }
            else -> {
                launcher.launch(Manifest.permission.CAMERA)
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


    AndroidView(
        modifier = modifier.pointerInput(camera, focusOnTap) {
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
}