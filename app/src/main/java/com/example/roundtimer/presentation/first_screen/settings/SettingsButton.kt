package com.example.roundtimer.presentation.first_screen.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.roundtimer.R

@Composable
fun SettingsButton() {
    val isDarkTheme = isSystemInDarkTheme()
    val imageId = if (isDarkTheme) R.drawable.lightdots else R.drawable.darkdots

    Box(modifier = Modifier.size(25.dp)) {
        Image(
            painter = painterResource(id = imageId),
            contentDescription = if (isDarkTheme) "Dark theme dots" else "Light theme dots",
            contentScale = ContentScale.Fit,
            modifier = Modifier.fillMaxSize().padding(horizontal = 4.dp)
                .clip(RoundedCornerShape(2.5.dp))
                .clickable { /*TODO*/ }
        )
    }
}