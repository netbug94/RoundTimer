package com.example.roundtimer.transition_screen.three_second_screen

import android.media.AudioAttributes
import android.media.SoundPool
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.roundtimer.R
import com.example.roundtimer.presentation.transition_screen.three_second_screen.ThreeSecondScreenEvent
import com.example.roundtimer.presentation.transition_screen.three_second_screen.ThreeSecondViewModel
import com.example.roundtimer.presentation.transition_screen.wallpaper.TransitionScreenWallpaper

@Composable
fun ThreeSecondScreen(
    onNavigation: () -> Unit,
    onSwipeBack: () -> Unit,
    threeSecondViewModel: ThreeSecondViewModel = viewModel()
) {
    val secondsRemaining by threeSecondViewModel.secondsRemaining.collectAsState()
    val startingString = stringResource(id = R.string.Starting_in)
    val context = LocalContext.current
    val soundPool = remember {
        SoundPool.Builder()
            .setMaxStreams(1)
            .setAudioAttributes(
                AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build()
            )
            .build()
    }
    val soundThreeId = remember { soundPool.load(context, R.raw.three_voice, 1) }
    val soundTwoId = remember { soundPool.load(context, R.raw.two_voice, 1) }
    val soundOneId = remember { soundPool.load(context, R.raw.one_voice, 1) }

    DisposableEffect(Unit) {
        onDispose {
            soundPool.release()
        }
    }

    LaunchedEffect(secondsRemaining) {
        when (secondsRemaining) {
            3 -> {
                soundPool.play(soundThreeId, 1f, 1f, 1, 0, 1f)
            }
            2 -> {
                soundPool.play(soundTwoId, 1f, 1f, 1, 0, 1f)
            }
            1 -> {
                soundPool.play(soundOneId, 1f, 1f, 1, 0, 1f)
            }
        }
    }

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