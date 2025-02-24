package com.example.visionbook.ui.theme
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import com.example.ivran.ui.theme.Typography
import com.example.ivran.ui.theme.backgroundDark
import com.example.ivran.ui.theme.backgroundDarkHighContrast
import com.example.ivran.ui.theme.backgroundDarkMediumContrast
import com.example.ivran.ui.theme.backgroundLight
import com.example.ivran.ui.theme.backgroundLightHighContrast
import com.example.ivran.ui.theme.backgroundLightMediumContrast
import com.example.ivran.ui.theme.errorContainerDark
import com.example.ivran.ui.theme.errorContainerDarkHighContrast
import com.example.ivran.ui.theme.errorContainerDarkMediumContrast
import com.example.ivran.ui.theme.errorContainerLight
import com.example.ivran.ui.theme.errorContainerLightHighContrast
import com.example.ivran.ui.theme.errorContainerLightMediumContrast
import com.example.ivran.ui.theme.errorDark
import com.example.ivran.ui.theme.errorDarkHighContrast
import com.example.ivran.ui.theme.errorDarkMediumContrast
import com.example.ivran.ui.theme.errorLight
import com.example.ivran.ui.theme.errorLightHighContrast
import com.example.ivran.ui.theme.errorLightMediumContrast
import com.example.ivran.ui.theme.inverseOnSurfaceDark
import com.example.ivran.ui.theme.inverseOnSurfaceDarkHighContrast
import com.example.ivran.ui.theme.inverseOnSurfaceDarkMediumContrast
import com.example.ivran.ui.theme.inverseOnSurfaceLight
import com.example.ivran.ui.theme.inverseOnSurfaceLightHighContrast
import com.example.ivran.ui.theme.inverseOnSurfaceLightMediumContrast
import com.example.ivran.ui.theme.inversePrimaryDark
import com.example.ivran.ui.theme.inversePrimaryDarkHighContrast
import com.example.ivran.ui.theme.inversePrimaryDarkMediumContrast
import com.example.ivran.ui.theme.inversePrimaryLight
import com.example.ivran.ui.theme.inversePrimaryLightHighContrast
import com.example.ivran.ui.theme.inversePrimaryLightMediumContrast
import com.example.ivran.ui.theme.inverseSurfaceDark
import com.example.ivran.ui.theme.inverseSurfaceDarkHighContrast
import com.example.ivran.ui.theme.inverseSurfaceDarkMediumContrast
import com.example.ivran.ui.theme.inverseSurfaceLight
import com.example.ivran.ui.theme.inverseSurfaceLightHighContrast
import com.example.ivran.ui.theme.inverseSurfaceLightMediumContrast
import com.example.ivran.ui.theme.onBackgroundDark
import com.example.ivran.ui.theme.onBackgroundDarkHighContrast
import com.example.ivran.ui.theme.onBackgroundDarkMediumContrast
import com.example.ivran.ui.theme.onBackgroundLight
import com.example.ivran.ui.theme.onBackgroundLightHighContrast
import com.example.ivran.ui.theme.onBackgroundLightMediumContrast
import com.example.ivran.ui.theme.onErrorContainerDark
import com.example.ivran.ui.theme.onErrorContainerDarkHighContrast
import com.example.ivran.ui.theme.onErrorContainerDarkMediumContrast
import com.example.ivran.ui.theme.onErrorContainerLight
import com.example.ivran.ui.theme.onErrorContainerLightHighContrast
import com.example.ivran.ui.theme.onErrorContainerLightMediumContrast
import com.example.ivran.ui.theme.onErrorDark
import com.example.ivran.ui.theme.onErrorDarkHighContrast
import com.example.ivran.ui.theme.onErrorDarkMediumContrast
import com.example.ivran.ui.theme.onErrorLight
import com.example.ivran.ui.theme.onErrorLightHighContrast
import com.example.ivran.ui.theme.onErrorLightMediumContrast
import com.example.ivran.ui.theme.onPrimaryContainerDark
import com.example.ivran.ui.theme.onPrimaryContainerDarkHighContrast
import com.example.ivran.ui.theme.onPrimaryContainerDarkMediumContrast
import com.example.ivran.ui.theme.onPrimaryContainerLight
import com.example.ivran.ui.theme.onPrimaryContainerLightHighContrast
import com.example.ivran.ui.theme.onPrimaryContainerLightMediumContrast
import com.example.ivran.ui.theme.onPrimaryDark
import com.example.ivran.ui.theme.onPrimaryDarkHighContrast
import com.example.ivran.ui.theme.onPrimaryDarkMediumContrast
import com.example.ivran.ui.theme.onPrimaryLight
import com.example.ivran.ui.theme.onPrimaryLightHighContrast
import com.example.ivran.ui.theme.onPrimaryLightMediumContrast
import com.example.ivran.ui.theme.onSecondaryContainerDark
import com.example.ivran.ui.theme.onSecondaryContainerDarkHighContrast
import com.example.ivran.ui.theme.onSecondaryContainerDarkMediumContrast
import com.example.ivran.ui.theme.onSecondaryContainerLight
import com.example.ivran.ui.theme.onSecondaryContainerLightHighContrast
import com.example.ivran.ui.theme.onSecondaryContainerLightMediumContrast
import com.example.ivran.ui.theme.onSecondaryDark
import com.example.ivran.ui.theme.onSecondaryDarkHighContrast
import com.example.ivran.ui.theme.onSecondaryDarkMediumContrast
import com.example.ivran.ui.theme.onSecondaryLight
import com.example.ivran.ui.theme.onSecondaryLightHighContrast
import com.example.ivran.ui.theme.onSecondaryLightMediumContrast
import com.example.ivran.ui.theme.onSurfaceDark
import com.example.ivran.ui.theme.onSurfaceDarkHighContrast
import com.example.ivran.ui.theme.onSurfaceDarkMediumContrast
import com.example.ivran.ui.theme.onSurfaceLight
import com.example.ivran.ui.theme.onSurfaceLightHighContrast
import com.example.ivran.ui.theme.onSurfaceLightMediumContrast
import com.example.ivran.ui.theme.onSurfaceVariantDark
import com.example.ivran.ui.theme.onSurfaceVariantDarkHighContrast
import com.example.ivran.ui.theme.onSurfaceVariantDarkMediumContrast
import com.example.ivran.ui.theme.onSurfaceVariantLight
import com.example.ivran.ui.theme.onSurfaceVariantLightHighContrast
import com.example.ivran.ui.theme.onSurfaceVariantLightMediumContrast
import com.example.ivran.ui.theme.onTertiaryContainerDark
import com.example.ivran.ui.theme.onTertiaryContainerDarkHighContrast
import com.example.ivran.ui.theme.onTertiaryContainerDarkMediumContrast
import com.example.ivran.ui.theme.onTertiaryContainerLight
import com.example.ivran.ui.theme.onTertiaryContainerLightHighContrast
import com.example.ivran.ui.theme.onTertiaryContainerLightMediumContrast
import com.example.ivran.ui.theme.onTertiaryDark
import com.example.ivran.ui.theme.onTertiaryDarkHighContrast
import com.example.ivran.ui.theme.onTertiaryDarkMediumContrast
import com.example.ivran.ui.theme.onTertiaryLight
import com.example.ivran.ui.theme.onTertiaryLightHighContrast
import com.example.ivran.ui.theme.onTertiaryLightMediumContrast
import com.example.ivran.ui.theme.outlineDark
import com.example.ivran.ui.theme.outlineDarkHighContrast
import com.example.ivran.ui.theme.outlineDarkMediumContrast
import com.example.ivran.ui.theme.outlineLight
import com.example.ivran.ui.theme.outlineLightHighContrast
import com.example.ivran.ui.theme.outlineLightMediumContrast
import com.example.ivran.ui.theme.outlineVariantDark
import com.example.ivran.ui.theme.outlineVariantDarkHighContrast
import com.example.ivran.ui.theme.outlineVariantDarkMediumContrast
import com.example.ivran.ui.theme.outlineVariantLight
import com.example.ivran.ui.theme.outlineVariantLightHighContrast
import com.example.ivran.ui.theme.outlineVariantLightMediumContrast
import com.example.ivran.ui.theme.primaryContainerDark
import com.example.ivran.ui.theme.primaryContainerDarkHighContrast
import com.example.ivran.ui.theme.primaryContainerDarkMediumContrast
import com.example.ivran.ui.theme.primaryContainerLight
import com.example.ivran.ui.theme.primaryContainerLightHighContrast
import com.example.ivran.ui.theme.primaryContainerLightMediumContrast
import com.example.ivran.ui.theme.primaryDark
import com.example.ivran.ui.theme.primaryDarkHighContrast
import com.example.ivran.ui.theme.primaryDarkMediumContrast
import com.example.ivran.ui.theme.primaryLight
import com.example.ivran.ui.theme.primaryLightHighContrast
import com.example.ivran.ui.theme.primaryLightMediumContrast
import com.example.ivran.ui.theme.scrimDark
import com.example.ivran.ui.theme.scrimDarkHighContrast
import com.example.ivran.ui.theme.scrimDarkMediumContrast
import com.example.ivran.ui.theme.scrimLight
import com.example.ivran.ui.theme.scrimLightHighContrast
import com.example.ivran.ui.theme.scrimLightMediumContrast
import com.example.ivran.ui.theme.secondaryContainerDark
import com.example.ivran.ui.theme.secondaryContainerDarkHighContrast
import com.example.ivran.ui.theme.secondaryContainerDarkMediumContrast
import com.example.ivran.ui.theme.secondaryContainerLight
import com.example.ivran.ui.theme.secondaryContainerLightHighContrast
import com.example.ivran.ui.theme.secondaryContainerLightMediumContrast
import com.example.ivran.ui.theme.secondaryDark
import com.example.ivran.ui.theme.secondaryDarkHighContrast
import com.example.ivran.ui.theme.secondaryDarkMediumContrast
import com.example.ivran.ui.theme.secondaryLight
import com.example.ivran.ui.theme.secondaryLightHighContrast
import com.example.ivran.ui.theme.secondaryLightMediumContrast
import com.example.ivran.ui.theme.surfaceBrightDark
import com.example.ivran.ui.theme.surfaceBrightDarkHighContrast
import com.example.ivran.ui.theme.surfaceBrightDarkMediumContrast
import com.example.ivran.ui.theme.surfaceBrightLight
import com.example.ivran.ui.theme.surfaceBrightLightHighContrast
import com.example.ivran.ui.theme.surfaceBrightLightMediumContrast
import com.example.ivran.ui.theme.surfaceContainerDark
import com.example.ivran.ui.theme.surfaceContainerDarkHighContrast
import com.example.ivran.ui.theme.surfaceContainerDarkMediumContrast
import com.example.ivran.ui.theme.surfaceContainerHighDark
import com.example.ivran.ui.theme.surfaceContainerHighDarkHighContrast
import com.example.ivran.ui.theme.surfaceContainerHighDarkMediumContrast
import com.example.ivran.ui.theme.surfaceContainerHighLight
import com.example.ivran.ui.theme.surfaceContainerHighLightHighContrast
import com.example.ivran.ui.theme.surfaceContainerHighLightMediumContrast
import com.example.ivran.ui.theme.surfaceContainerHighestDark
import com.example.ivran.ui.theme.surfaceContainerHighestDarkHighContrast
import com.example.ivran.ui.theme.surfaceContainerHighestDarkMediumContrast
import com.example.ivran.ui.theme.surfaceContainerHighestLight
import com.example.ivran.ui.theme.surfaceContainerHighestLightHighContrast
import com.example.ivran.ui.theme.surfaceContainerHighestLightMediumContrast
import com.example.ivran.ui.theme.surfaceContainerLight
import com.example.ivran.ui.theme.surfaceContainerLightHighContrast
import com.example.ivran.ui.theme.surfaceContainerLightMediumContrast
import com.example.ivran.ui.theme.surfaceContainerLowDark
import com.example.ivran.ui.theme.surfaceContainerLowDarkHighContrast
import com.example.ivran.ui.theme.surfaceContainerLowDarkMediumContrast
import com.example.ivran.ui.theme.surfaceContainerLowLight
import com.example.ivran.ui.theme.surfaceContainerLowLightHighContrast
import com.example.ivran.ui.theme.surfaceContainerLowLightMediumContrast
import com.example.ivran.ui.theme.surfaceContainerLowestDark
import com.example.ivran.ui.theme.surfaceContainerLowestDarkHighContrast
import com.example.ivran.ui.theme.surfaceContainerLowestDarkMediumContrast
import com.example.ivran.ui.theme.surfaceContainerLowestLight
import com.example.ivran.ui.theme.surfaceContainerLowestLightHighContrast
import com.example.ivran.ui.theme.surfaceContainerLowestLightMediumContrast
import com.example.ivran.ui.theme.surfaceDark
import com.example.ivran.ui.theme.surfaceDarkHighContrast
import com.example.ivran.ui.theme.surfaceDarkMediumContrast
import com.example.ivran.ui.theme.surfaceDimDark
import com.example.ivran.ui.theme.surfaceDimDarkHighContrast
import com.example.ivran.ui.theme.surfaceDimDarkMediumContrast
import com.example.ivran.ui.theme.surfaceDimLight
import com.example.ivran.ui.theme.surfaceDimLightHighContrast
import com.example.ivran.ui.theme.surfaceDimLightMediumContrast
import com.example.ivran.ui.theme.surfaceLight
import com.example.ivran.ui.theme.surfaceLightHighContrast
import com.example.ivran.ui.theme.surfaceLightMediumContrast
import com.example.ivran.ui.theme.surfaceVariantDark
import com.example.ivran.ui.theme.surfaceVariantDarkHighContrast
import com.example.ivran.ui.theme.surfaceVariantDarkMediumContrast
import com.example.ivran.ui.theme.surfaceVariantLight
import com.example.ivran.ui.theme.surfaceVariantLightHighContrast
import com.example.ivran.ui.theme.surfaceVariantLightMediumContrast
import com.example.ivran.ui.theme.tertiaryContainerDark
import com.example.ivran.ui.theme.tertiaryContainerDarkHighContrast
import com.example.ivran.ui.theme.tertiaryContainerDarkMediumContrast
import com.example.ivran.ui.theme.tertiaryContainerLight
import com.example.ivran.ui.theme.tertiaryContainerLightHighContrast
import com.example.ivran.ui.theme.tertiaryContainerLightMediumContrast
import com.example.ivran.ui.theme.tertiaryDark
import com.example.ivran.ui.theme.tertiaryDarkHighContrast
import com.example.ivran.ui.theme.tertiaryDarkMediumContrast
import com.example.ivran.ui.theme.tertiaryLight
import com.example.ivran.ui.theme.tertiaryLightHighContrast
import com.example.ivran.ui.theme.tertiaryLightMediumContrast

private val lightScheme = lightColorScheme(
    primary = primaryLight,
    onPrimary = onPrimaryLight,
    primaryContainer = primaryContainerLight,
    onPrimaryContainer = onPrimaryContainerLight,
    secondary = secondaryLight,
    onSecondary = onSecondaryLight,
    secondaryContainer = secondaryContainerLight,
    onSecondaryContainer = onSecondaryContainerLight,
    tertiary = tertiaryLight,
    onTertiary = onTertiaryLight,
    tertiaryContainer = tertiaryContainerLight,
    onTertiaryContainer = onTertiaryContainerLight,
    error = errorLight,
    onError = onErrorLight,
    errorContainer = errorContainerLight,
    onErrorContainer = onErrorContainerLight,
    background = backgroundLight,
    onBackground = onBackgroundLight,
    surface = surfaceLight,
    onSurface = onSurfaceLight,
    surfaceVariant = surfaceVariantLight,
    onSurfaceVariant = onSurfaceVariantLight,
    outline = outlineLight,
    outlineVariant = outlineVariantLight,
    scrim = scrimLight,
    inverseSurface = inverseSurfaceLight,
    inverseOnSurface = inverseOnSurfaceLight,
    inversePrimary = inversePrimaryLight,
    surfaceDim = surfaceDimLight,
    surfaceBright = surfaceBrightLight,
    surfaceContainerLowest = surfaceContainerLowestLight,
    surfaceContainerLow = surfaceContainerLowLight,
    surfaceContainer = surfaceContainerLight,
    surfaceContainerHigh = surfaceContainerHighLight,
    surfaceContainerHighest = surfaceContainerHighestLight,
)

private val darkScheme = darkColorScheme(
    primary = primaryDark,
    onPrimary = onPrimaryDark,
    primaryContainer = primaryContainerDark,
    onPrimaryContainer = onPrimaryContainerDark,
    secondary = secondaryDark,
    onSecondary = onSecondaryDark,
    secondaryContainer = secondaryContainerDark,
    onSecondaryContainer = onSecondaryContainerDark,
    tertiary = tertiaryDark,
    onTertiary = onTertiaryDark,
    tertiaryContainer = tertiaryContainerDark,
    onTertiaryContainer = onTertiaryContainerDark,
    error = errorDark,
    onError = onErrorDark,
    errorContainer = errorContainerDark,
    onErrorContainer = onErrorContainerDark,
    background = backgroundDark,
    onBackground = onBackgroundDark,
    surface = surfaceDark,
    onSurface = onSurfaceDark,
    surfaceVariant = surfaceVariantDark,
    onSurfaceVariant = onSurfaceVariantDark,
    outline = outlineDark,
    outlineVariant = outlineVariantDark,
    scrim = scrimDark,
    inverseSurface = inverseSurfaceDark,
    inverseOnSurface = inverseOnSurfaceDark,
    inversePrimary = inversePrimaryDark,
    surfaceDim = surfaceDimDark,
    surfaceBright = surfaceBrightDark,
    surfaceContainerLowest = surfaceContainerLowestDark,
    surfaceContainerLow = surfaceContainerLowDark,
    surfaceContainer = surfaceContainerDark,
    surfaceContainerHigh = surfaceContainerHighDark,
    surfaceContainerHighest = surfaceContainerHighestDark,
)

private val mediumContrastLightColorScheme = lightColorScheme(
    primary = primaryLightMediumContrast,
    onPrimary = onPrimaryLightMediumContrast,
    primaryContainer = primaryContainerLightMediumContrast,
    onPrimaryContainer = onPrimaryContainerLightMediumContrast,
    secondary = secondaryLightMediumContrast,
    onSecondary = onSecondaryLightMediumContrast,
    secondaryContainer = secondaryContainerLightMediumContrast,
    onSecondaryContainer = onSecondaryContainerLightMediumContrast,
    tertiary = tertiaryLightMediumContrast,
    onTertiary = onTertiaryLightMediumContrast,
    tertiaryContainer = tertiaryContainerLightMediumContrast,
    onTertiaryContainer = onTertiaryContainerLightMediumContrast,
    error = errorLightMediumContrast,
    onError = onErrorLightMediumContrast,
    errorContainer = errorContainerLightMediumContrast,
    onErrorContainer = onErrorContainerLightMediumContrast,
    background = backgroundLightMediumContrast,
    onBackground = onBackgroundLightMediumContrast,
    surface = surfaceLightMediumContrast,
    onSurface = onSurfaceLightMediumContrast,
    surfaceVariant = surfaceVariantLightMediumContrast,
    onSurfaceVariant = onSurfaceVariantLightMediumContrast,
    outline = outlineLightMediumContrast,
    outlineVariant = outlineVariantLightMediumContrast,
    scrim = scrimLightMediumContrast,
    inverseSurface = inverseSurfaceLightMediumContrast,
    inverseOnSurface = inverseOnSurfaceLightMediumContrast,
    inversePrimary = inversePrimaryLightMediumContrast,
    surfaceDim = surfaceDimLightMediumContrast,
    surfaceBright = surfaceBrightLightMediumContrast,
    surfaceContainerLowest = surfaceContainerLowestLightMediumContrast,
    surfaceContainerLow = surfaceContainerLowLightMediumContrast,
    surfaceContainer = surfaceContainerLightMediumContrast,
    surfaceContainerHigh = surfaceContainerHighLightMediumContrast,
    surfaceContainerHighest = surfaceContainerHighestLightMediumContrast,
)

private val highContrastLightColorScheme = lightColorScheme(
    primary = primaryLightHighContrast,
    onPrimary = onPrimaryLightHighContrast,
    primaryContainer = primaryContainerLightHighContrast,
    onPrimaryContainer = onPrimaryContainerLightHighContrast,
    secondary = secondaryLightHighContrast,
    onSecondary = onSecondaryLightHighContrast,
    secondaryContainer = secondaryContainerLightHighContrast,
    onSecondaryContainer = onSecondaryContainerLightHighContrast,
    tertiary = tertiaryLightHighContrast,
    onTertiary = onTertiaryLightHighContrast,
    tertiaryContainer = tertiaryContainerLightHighContrast,
    onTertiaryContainer = onTertiaryContainerLightHighContrast,
    error = errorLightHighContrast,
    onError = onErrorLightHighContrast,
    errorContainer = errorContainerLightHighContrast,
    onErrorContainer = onErrorContainerLightHighContrast,
    background = backgroundLightHighContrast,
    onBackground = onBackgroundLightHighContrast,
    surface = surfaceLightHighContrast,
    onSurface = onSurfaceLightHighContrast,
    surfaceVariant = surfaceVariantLightHighContrast,
    onSurfaceVariant = onSurfaceVariantLightHighContrast,
    outline = outlineLightHighContrast,
    outlineVariant = outlineVariantLightHighContrast,
    scrim = scrimLightHighContrast,
    inverseSurface = inverseSurfaceLightHighContrast,
    inverseOnSurface = inverseOnSurfaceLightHighContrast,
    inversePrimary = inversePrimaryLightHighContrast,
    surfaceDim = surfaceDimLightHighContrast,
    surfaceBright = surfaceBrightLightHighContrast,
    surfaceContainerLowest = surfaceContainerLowestLightHighContrast,
    surfaceContainerLow = surfaceContainerLowLightHighContrast,
    surfaceContainer = surfaceContainerLightHighContrast,
    surfaceContainerHigh = surfaceContainerHighLightHighContrast,
    surfaceContainerHighest = surfaceContainerHighestLightHighContrast,
)

private val mediumContrastDarkColorScheme = darkColorScheme(
    primary = primaryDarkMediumContrast,
    onPrimary = onPrimaryDarkMediumContrast,
    primaryContainer = primaryContainerDarkMediumContrast,
    onPrimaryContainer = onPrimaryContainerDarkMediumContrast,
    secondary = secondaryDarkMediumContrast,
    onSecondary = onSecondaryDarkMediumContrast,
    secondaryContainer = secondaryContainerDarkMediumContrast,
    onSecondaryContainer = onSecondaryContainerDarkMediumContrast,
    tertiary = tertiaryDarkMediumContrast,
    onTertiary = onTertiaryDarkMediumContrast,
    tertiaryContainer = tertiaryContainerDarkMediumContrast,
    onTertiaryContainer = onTertiaryContainerDarkMediumContrast,
    error = errorDarkMediumContrast,
    onError = onErrorDarkMediumContrast,
    errorContainer = errorContainerDarkMediumContrast,
    onErrorContainer = onErrorContainerDarkMediumContrast,
    background = backgroundDarkMediumContrast,
    onBackground = onBackgroundDarkMediumContrast,
    surface = surfaceDarkMediumContrast,
    onSurface = onSurfaceDarkMediumContrast,
    surfaceVariant = surfaceVariantDarkMediumContrast,
    onSurfaceVariant = onSurfaceVariantDarkMediumContrast,
    outline = outlineDarkMediumContrast,
    outlineVariant = outlineVariantDarkMediumContrast,
    scrim = scrimDarkMediumContrast,
    inverseSurface = inverseSurfaceDarkMediumContrast,
    inverseOnSurface = inverseOnSurfaceDarkMediumContrast,
    inversePrimary = inversePrimaryDarkMediumContrast,
    surfaceDim = surfaceDimDarkMediumContrast,
    surfaceBright = surfaceBrightDarkMediumContrast,
    surfaceContainerLowest = surfaceContainerLowestDarkMediumContrast,
    surfaceContainerLow = surfaceContainerLowDarkMediumContrast,
    surfaceContainer = surfaceContainerDarkMediumContrast,
    surfaceContainerHigh = surfaceContainerHighDarkMediumContrast,
    surfaceContainerHighest = surfaceContainerHighestDarkMediumContrast,
)

private val highContrastDarkColorScheme = darkColorScheme(
    primary = primaryDarkHighContrast,
    onPrimary = onPrimaryDarkHighContrast,
    primaryContainer = primaryContainerDarkHighContrast,
    onPrimaryContainer = onPrimaryContainerDarkHighContrast,
    secondary = secondaryDarkHighContrast,
    onSecondary = onSecondaryDarkHighContrast,
    secondaryContainer = secondaryContainerDarkHighContrast,
    onSecondaryContainer = onSecondaryContainerDarkHighContrast,
    tertiary = tertiaryDarkHighContrast,
    onTertiary = onTertiaryDarkHighContrast,
    tertiaryContainer = tertiaryContainerDarkHighContrast,
    onTertiaryContainer = onTertiaryContainerDarkHighContrast,
    error = errorDarkHighContrast,
    onError = onErrorDarkHighContrast,
    errorContainer = errorContainerDarkHighContrast,
    onErrorContainer = onErrorContainerDarkHighContrast,
    background = backgroundDarkHighContrast,
    onBackground = onBackgroundDarkHighContrast,
    surface = surfaceDarkHighContrast,
    onSurface = onSurfaceDarkHighContrast,
    surfaceVariant = surfaceVariantDarkHighContrast,
    onSurfaceVariant = onSurfaceVariantDarkHighContrast,
    outline = outlineDarkHighContrast,
    outlineVariant = outlineVariantDarkHighContrast,
    scrim = scrimDarkHighContrast,
    inverseSurface = inverseSurfaceDarkHighContrast,
    inverseOnSurface = inverseOnSurfaceDarkHighContrast,
    inversePrimary = inversePrimaryDarkHighContrast,
    surfaceDim = surfaceDimDarkHighContrast,
    surfaceBright = surfaceBrightDarkHighContrast,
    surfaceContainerLowest = surfaceContainerLowestDarkHighContrast,
    surfaceContainerLow = surfaceContainerLowDarkHighContrast,
    surfaceContainer = surfaceContainerDarkHighContrast,
    surfaceContainerHigh = surfaceContainerHighDarkHighContrast,
    surfaceContainerHighest = surfaceContainerHighestDarkHighContrast,
)

@Immutable
data class ColorFamily(
    val color: Color,
    val onColor: Color,
    val colorContainer: Color,
    val onColorContainer: Color
)

val unspecified_scheme = ColorFamily(
    Color.Unspecified, Color.Unspecified, Color.Unspecified, Color.Unspecified
)

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {

        darkTheme -> darkScheme
        else -> lightScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}