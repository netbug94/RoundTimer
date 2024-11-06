package com.example.roundtimer.presentation.transition_screen.wallpaper

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import com.example.roundtimer.R

@Composable
fun TransitionScreenWallpaper() {
    val darkTheme = isSystemInDarkTheme()
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
    val vectorBackground = when {
        darkTheme && isLandscape -> painterResource(id = R.drawable.five_second_horizontal_wallpaper_dark)
        darkTheme && !isLandscape -> painterResource(id = R.drawable.five_second_wallpaper_dark)
        !darkTheme && isLandscape -> painterResource(id = R.drawable.five_second_horizontal_wallpaper_light)
        else -> painterResource(id = R.drawable.five_second_wallpaper_light)
    }
    val contentScale = if (isLandscape) {
        ContentScale.Fit
    } else {
        ContentScale.Fit
    }

    Image(
        painter = vectorBackground,
        contentDescription = null,
        modifier = Modifier.fillMaxSize(),
        contentScale = contentScale
    )
}
