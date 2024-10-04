package com.example.roundtimer.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun CustomColorScheme(): Color {
    val darkTheme = isSystemInDarkTheme()
    return if (darkTheme) Color.White else Color.Black
}