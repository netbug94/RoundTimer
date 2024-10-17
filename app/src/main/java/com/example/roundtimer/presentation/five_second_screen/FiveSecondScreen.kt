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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.example.roundtimer.presentation.wallpaper.FiveSecScreenWallpaper
import kotlinx.coroutines.delay
import kotlin.coroutines.cancellation.CancellationException

@Composable
fun FiveSecondScreen(onNavigation: () -> Unit, onSwipeBack: () -> Unit) {
    var secondsRemaining by rememberSaveable { mutableIntStateOf(5) }
    var isCancelled by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        try {
            for (i in 5 downTo 1) {
                // Check if the countdown has been cancelled
                if (isCancelled) {
                    break
                }
                secondsRemaining = i
                delay(1000)
            }
            // Only navigate if the countdown wasn't cancelled
            if (!isCancelled) {
                onNavigation()
            }
        } catch (e: CancellationException) {
            // Handle coroutine cancellation if needed
        }
    }

    BackHandler {
        // Set the cancellation flag to true
        isCancelled = true
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
