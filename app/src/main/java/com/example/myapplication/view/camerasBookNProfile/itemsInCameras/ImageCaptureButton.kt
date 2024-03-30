package com.example.myapplication.view.camerasBookNProfile.itemsInCameras

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.Executor

@Composable
fun ButtonCaptureImage(
    context: Context,
    outputDirectory: File,
    onMediaCaptured: (Uri?) -> Unit,
    imageCapture: ImageCapture?,
    executor: Executor
) {
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
            .border(5.dp, MaterialTheme.colorScheme.primary, CircleShape)
    ) {
        Icon(painter = painterResource(R.drawable.circle), "ImageCaptureButton")
    }
}