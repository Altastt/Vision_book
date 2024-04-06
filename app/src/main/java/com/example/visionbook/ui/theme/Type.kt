package com.example.visionbook.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = sourceSans,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = sourceSans,
        fontWeight = FontWeight.SemiBold,
        fontStyle = FontStyle.Italic,
        fontSize = 18.sp
    ),
    bodySmall = TextStyle(
        fontFamily = sourceSans,
        fontWeight = FontWeight.SemiBold,
        fontStyle = FontStyle.Italic,
        fontSize = 15.sp
    ),
    labelLarge = TextStyle(
        fontFamily = sourceSans,
        fontWeight = FontWeight.Bold,
        fontSize = 25.sp
    ),
    labelMedium = TextStyle(
        fontFamily = sourceSans,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp
    ),
    labelSmall = TextStyle(
        fontFamily = sourceSans,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    ),
    titleLarge = TextStyle(
        fontFamily = sourceSans,
        fontWeight = FontWeight.SemiBold,
        fontSize = 25.sp
    ),
    titleMedium = TextStyle(
        fontFamily = sourceSans,
        fontWeight = FontWeight.SemiBold,
        fontSize = 17.sp
    ),
    titleSmall = TextStyle(
        fontFamily = sourceSans,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp
    ),
    headlineLarge = TextStyle(
        fontFamily = sourceSans,
        fontWeight = FontWeight.SemiBold,
        fontSize = 30.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = sourceSans,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp
    ),
    // dont use
    headlineSmall = TextStyle(
        fontFamily = sourceSans,
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp
    ),
    // use
    displayLarge = TextStyle(
        fontFamily = sourceSans,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp
    ),
    // dont use
    displayMedium = TextStyle(
        fontFamily = sourceSans,
        fontWeight = FontWeight.Bold,
        fontSize = 25.sp
    ),
    displaySmall = TextStyle(
        fontFamily = sourceSans,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp
    ),

)