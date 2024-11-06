package com.example.roundtimer.transition_screen.five_second_screen

import android.media.AudioAttributes
import android.media.SoundPool
import androidx.activity.ComponentActivity
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
import com.example.roundtimer.presentation.transition_screen.wallpaper.TransitionScreenWallpaper
import com.example.roundtimer.setting_screens.settings_screen.transition_settings_screen.TransitionSettingsViewModel

@Composable
fun FiveSecondScreen(
    onNavigation: () -> Unit,
    onSwipeBack: () -> Unit,
    fiveSecondViewModel: FiveSecondViewModel = viewModel()
) {
    val secondsRemaining by fiveSecondViewModel.secondsRemaining.collectAsState()
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
    val soundFiveId = remember { soundPool.load(context, R.raw.five_voice, 1) }
    val soundFourId = remember { soundPool.load(context, R.raw.four_voice, 1) }
    val soundThreeId = remember { soundPool.load(context, R.raw.three_voice, 1) }
    val soundTwoId = remember { soundPool.load(context, R.raw.two_voice, 1) }
    val soundOneId = remember { soundPool.load(context, R.raw.one_voice, 1) }

    val transitionSettingsViewModel: TransitionSettingsViewModel = viewModel(
        viewModelStoreOwner = LocalContext.current as ComponentActivity
    )
    val isSoundEnabled by transitionSettingsViewModel.isSoundEnabled.collectAsState()


    LaunchedEffect(secondsRemaining, isSoundEnabled) {
        if (isSoundEnabled) {
            when (secondsRemaining) {
                5 -> soundPool.play(soundFiveId, 1f, 1f, 1, 0, 1f)
                4 -> soundPool.play(soundFourId, 1f, 1f, 1, 0, 1f)
                3 -> soundPool.play(soundThreeId, 1f, 1f, 1, 0, 1f)
                2 -> soundPool.play(soundTwoId, 1f, 1f, 1, 0, 1f)
                1 -> soundPool.play(soundOneId, 1f, 1f, 1, 0, 1f)
            }
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            soundPool.release()
        }
    }

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
    val startingInString = stringResource(id = R.string.Starting_in)

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
            text = startingInString,
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