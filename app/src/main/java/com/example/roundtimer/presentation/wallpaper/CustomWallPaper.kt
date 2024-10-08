package com.example.roundtimer.presentation.wallpaper

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.roundtimer.R

@Composable
fun CustomWallPaper() {
    val vectorBackground = painterResource(id = R.drawable.five_sec_wallpaper)
    Image(
        painter = vectorBackground,
        contentDescription = null,
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Fit
    )
}