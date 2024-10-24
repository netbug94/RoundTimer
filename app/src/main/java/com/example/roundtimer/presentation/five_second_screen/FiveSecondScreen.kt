package com.example.roundtimer.presentation.five_second_screen

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
import com.example.roundtimer.presentation.wallpaper.FiveSecScreenWallpaper

@Composable
fun FiveSecondScreen(
    onNavigation: () -> Unit,
    onSwipeBack: () -> Unit,
    fiveSecondViewModel: FiveSecondViewModel = viewModel()
) {
    val secondsRemaining by fiveSecondViewModel.secondsRemaining.collectAsState()

    LaunchedEffect(Unit) {
        fiveSecondViewModel.uiEvent.collect { event ->
            when (event) {
                is FiveSecondScreenEvent.Navigate -> onNavigation()
                is FiveSecondScreenEvent.SwipeBack -> onSwipeBack()
            }
        }
    }

    FiveSecondScreenContent(
        secondsRemaining = secondsRemaining,
        onBackPressed = {
            fiveSecondViewModel.cancelCountdown()
            onSwipeBack()
        }
    )
}

@Composable
fun FiveSecondScreenContent(
    secondsRemaining: Int,
    onBackPressed: () -> Unit
) {
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
            text = stringResource(id = R.string.Starting_in),
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
