import android.content.Context
import android.net.Uri
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import com.example.myapplication.R
import com.example.myapplication.itemsOfScreen.BackButton
import com.example.myapplication.itemsOfScreen.ButtonCaptureImage
import com.example.myapplication.models.FaceAnalyser
import java.io.File
import java.util.concurrent.ExecutionException
import java.util.concurrent.Executor

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
    var flashEnabled by remember { mutableStateOf(false) }
    var flashRes by remember { mutableStateOf(R.drawable.flash_on) }

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
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(25.dp)
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
                    modifier = Modifier.size(35.dp))
            }
            ButtonCaptureImage(context, outputDirectory, onMediaCaptured, imageCapture, executor)
            IconButton(
                onClick = {

                }
            ) {
                Icon(painter = painterResource(R.drawable.info),
                    contentDescription = "",
                    modifier = Modifier.size(35.dp))
            }
        }
    }
}
