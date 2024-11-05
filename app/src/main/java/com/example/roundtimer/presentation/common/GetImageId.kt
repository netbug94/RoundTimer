package com.example.roundtimer.presentation.common

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import com.example.roundtimer.R

@Composable
fun getImageIds(): Pair<Int, Int> {
    val isDarkTheme = isSystemInDarkTheme()
    val dotsImageId = if (isDarkTheme) R.drawable.light_dots else R.drawable.dark_dots
    val savedIconImageId = if (isDarkTheme) R.drawable.saved_icon_light else R.drawable.saved_icon_dark

    return Pair(dotsImageId, savedIconImageId)
}
