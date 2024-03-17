package com.example.myapplication.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = LightGreyText,
    onPrimary = DarkGrey,
    secondary = Orange,
    onSecondaryContainer = LightGreyText,
    onSecondary = Orange,
    secondaryContainer = LightOrange,
    background = DarkGrey
)

private val LightColorScheme = lightColorScheme(
    primary = DarkGrey,
    onPrimary = LightGreyText,
    secondary = Orange,
    onSecondaryContainer = LightGreyText,
    onSecondary = DarkGrey,
    secondaryContainer = LightOrange,
    background = BackgroundWhite

)

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    /*   // Dynamic color is available on Android 12+
       dynamicColor: Boolean = true,*/
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme)
        DarkColorScheme
    else
        LightColorScheme


    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}