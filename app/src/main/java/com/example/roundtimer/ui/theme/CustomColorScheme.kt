package com.example.roundtimer.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun customColorScheme(): CustomColor {
    val darkTheme = isSystemInDarkTheme()

    return if (darkTheme) {
        CustomColor(
            customBorderColor = Color.White,
            customTextColor = null,
            customButtonColor = null,
            customRippleColor = null
        )
    } else {
        CustomColor(
            customBorderColor = Color.Black,
            customTextColor = null,
            customButtonColor = null,
            customRippleColor = null
        )
    }
}