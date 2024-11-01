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
            redTextColor = Color.Red,
            emailTextColor = LightBlue,
            customButtonColor = Color.LightGray,
            customRippleColor = Color.LightGray,
            inverseSecondary = PurpleGrey40
        )
    } else {
        CustomColor(
            customBorderColor = Color.Black,
            customTextColor = Color.White,
            emailTextColor = Color.Blue,
            redTextColor = DarkRed,
            customButtonColor = Color.DarkGray,
            customRippleColor = Color.DarkGray,
            inverseSecondary = PurpleGrey80
        )
    }
}
