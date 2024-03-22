package com.example.myapplication.ui.theme


import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = LightGreyText,
    onPrimary = DarkGrey,
    secondary = Orange,
    onSecondaryContainer = LightGreyText,
    onSecondary = Orange,
    secondaryContainer = DarkGrey,
    primaryContainer = LightOrange,
    background = DarkGrey
)

private val LightColorScheme = lightColorScheme(
    primary = DarkGrey,
    onPrimary = LightGreyText,
    secondary = Orange,
    onSecondaryContainer = LightGreyText,
    onSecondary = DarkGrey,
    secondaryContainer = Orange,
    primaryContainer = LightOrange,
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