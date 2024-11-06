package com.example.roundtimer.presentation.transition_screen.three_second_screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.roundtimer.R
import com.example.roundtimer.presentation.transition_screen.wallpaper.TransitionScreenWallpaper

@Composable
fun ThreeSecondScreen(
    onNavigation: () -> Unit,
    onSwipeBack: () -> Unit,
    threeSecondViewModel: ThreeSecondViewModel = viewModel()
) {
    val secondsRemaining by threeSecondViewModel.secondsRemaining.collectAsState()
    val startingString = stringResource(id = R.string.Starting_in)

    LaunchedEffect(Unit) {
        threeSecondViewModel.uiEvent.collect { event ->
            when (event) {
                is ThreeSecondScreenEvent.Navigate -> onNavigation()
                is ThreeSecondScreenEvent.SwipeBack -> onSwipeBack()
            }
        }
    }

    ThreeSecondScreenContent(
        secondsRemaining = secondsRemaining,
        startingString = startingString,
        onBackPressed = {
            threeSecondViewModel.cancelCountdown()
            onSwipeBack()
        }
    )
}

@Composable
fun ThreeSecondScreenContent(
    secondsRemaining: Int,
    startingString: String,
    onBackPressed: () -> Unit
) {
    TransitionScreenWallpaper()

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
            text = startingString,
            style = textStyle
        )

        Text(
            text = "$secondsRemaining",
            style = textStyle
        )
    }

    BackHandler {
        onBackPressed()
    }
}