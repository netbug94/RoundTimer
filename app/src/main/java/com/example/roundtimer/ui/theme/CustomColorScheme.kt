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
            customTextColor = Color.Black,
            customButtonColor = Color.LightGray,
            customRippleColor = Color.LightGray
        )
    } else {
        CustomColor(
            customBorderColor = Color.Black,
            customTextColor = Color.White,
            customButtonColor = Color.DarkGray,
            customRippleColor = Color.DarkGray
        )
    }
}