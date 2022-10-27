package com.example.sensimate.ui.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@SuppressLint("ConflictingOnColor")
private val DarkColorPalette = darkColors(

    background = DBackground,
    surface = DOverlay,
    onSurface = DOnItem,
    primary = DPrimary,
    onPrimary = DOnItem,
    secondary = DSecondary
)

@SuppressLint("ConflictingOnColor")
private val LightColorPalette = lightColors(
    background = LBackground,
    surface = LOverlay,
    onSurface = LOnItem,
    primary = LPrimary,
    onPrimary = LOnItem,
    secondary = LSecondary


    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun SensimateTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}