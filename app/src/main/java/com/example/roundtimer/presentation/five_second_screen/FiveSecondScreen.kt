package com.example.roundtimer.presentation.five_second_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import kotlinx.coroutines.delay

@Composable
fun FiveSecondScreen(onNavigation: () -> Unit) {
    var secondsRemaining by remember { mutableIntStateOf(5) }
    val secondsRemainingState by rememberUpdatedState(secondsRemaining)

    LaunchedEffect(Unit) {
        while (secondsRemainingState > 1) {
            delay(1000)
            secondsRemaining -= 1
        }
        delay(1000)
        //navController.navigate(route = "round_count_down")
    }

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
