package com.example.roundtimer.presentation.five_second_screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.example.roundtimer.presentation.wallpaper.FiveSecScreenWallpaper
import kotlinx.coroutines.delay

@Composable
fun FiveSecondScreen(onNavigation: () -> Unit, onSwipeBack: () -> Unit) {
    var secondsRemaining by rememberSaveable { mutableIntStateOf(5) }

    LaunchedEffect(Unit) {
        while (secondsRemaining > 1) {
            delay(1000)
            secondsRemaining -= 1
        }
        delay(1000)
        onNavigation()
    }

    BackHandler {
            onSwipeBack()
    }

    FiveSecScreenWallpaper()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val textStyle = MaterialTheme.typography.displayMedium.copy(
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Text(
            text = "Starting in:",
            style = textStyle
        )
        Text(
            text = "$secondsRemaining",
            style = textStyle
        )
    }
}
