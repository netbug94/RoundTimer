package com.example.roundtimer.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.roundtimer.R

@Composable
fun CustomWallPaper() {
    val vectorBackground = painterResource(id = R.drawable.customwallpaper)
    Image(
        painter = vectorBackground,
        contentDescription = null,
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Fit
    )
}