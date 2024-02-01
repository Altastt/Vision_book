package com.example.myapplication.screens

import android.Manifest
import android.content.pm.PackageManager
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.core.content.ContextCompat
import com.example.myapplication.R

@Composable
fun BooksScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        // parameters set to place the items in center
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(R.drawable.book),
            contentDescription = "search",
            tint = Color(0xFF0F9D58)
        )
        Text(text = "Search", color = Color.Black)
    }
}

@Composable
fun ExampleScreen() {
    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {
        isGranted: Boolean ->
        if (isGranted) {
            Log.d("ExampleScreen", "PERMISSION GRANTED")
        } else {
            Log.d("ExampleScreen", "PERMISSION DENIED")
        }
    }
    val context = LocalContext.current

    Button(
        onClick = {
            when(PackageManager.PERMISSION_GRANTED) {
                ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.CAMERA
                ) -> {
                    Log.d("ExampleScreen", "Code requires permission")
                } else -> {
                    launcher.launch(Manifest.permission.CAMERA)
                }
            }
        }
    ) {
        Text("Check and Request Permission")
    }
}
