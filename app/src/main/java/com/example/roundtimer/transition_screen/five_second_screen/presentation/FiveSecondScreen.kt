package com.example.roundtimer.transition_screen.five_second_screen.presentation

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
import com.example.roundtimer.presentation.transition_screen.wallpaper.TransitionScreenWallpaper
import com.example.roundtimer.setting_screens.settings_screen.voice_settings.presentation.VoiceOption
import com.example.roundtimer.setting_screens.settings_screen.voice_settings.presentation.VoiceSettingsViewModel
import com.example.roundtimer.transition_screen.five_second_screen.domain.SoundIds

@Composable
fun FiveSecondScreen(
    onNavigation: () -> Unit,
    onSwipeBack: () -> Unit,
    voiceSettingsViewModel: VoiceSettingsViewModel = viewModel(),
    fiveSecondViewModel: FiveSecondViewModel = viewModel()
) {
    val secondsRemaining by fiveSecondViewModel.secondsRemaining.collectAsState()
    val selectedVoiceOption by voiceSettingsViewModel.selectedVoiceOption.collectAsState()
    val isVoiceOptionLoaded by voiceSettingsViewModel.isVoiceOptionLoaded.collectAsState()
    val startingString = stringResource(R.string.StartingInString)
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
    val soundIds = remember(selectedVoiceOption) {
        when (selectedVoiceOption) {
            VoiceOption.WOMAN_VOICE -> {
                SoundIds(
                    five = soundPool.load(context, R.raw.woman_voice_five, 1),
                    four = soundPool.load(context, R.raw.woman_voice_four, 1),
                    three = soundPool.load(context, R.raw.woman_voice_three, 1),
                    two = soundPool.load(context, R.raw.woman_voice_two, 1),
                    one = soundPool.load(context, R.raw.woman_voice_one, 1)
                )
            }
            VoiceOption.MAN_VOICE -> {
                SoundIds(
                    five = soundPool.load(context, R.raw.man_voice_five, 1),
                    four = soundPool.load(context, R.raw.man_voice_four, 1),
                    three = soundPool.load(context, R.raw.man_voice_three, 1),
                    two = soundPool.load(context, R.raw.man_voice_two, 1),
                    one = soundPool.load(context, R.raw.man_voice_one, 1)
                )
            }
            VoiceOption.MUTE -> {
                SoundIds(five = -1, four = -1, three = -1, two = -1, one = -1)
            }
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            soundPool.release()
        }
    }

    LaunchedEffect(isVoiceOptionLoaded) {
        if (isVoiceOptionLoaded) {
            fiveSecondViewModel.startCountdown()
        }
    }

    LaunchedEffect(secondsRemaining, selectedVoiceOption, isVoiceOptionLoaded) {
        if (isVoiceOptionLoaded && selectedVoiceOption != VoiceOption.MUTE) {
            when (secondsRemaining) {
                5 -> soundIds.five.takeIf { it != -1 }?.let { soundPool.play(it, 1f, 1f, 1, 0, 1f) }
                4 -> soundIds.four.takeIf { it != -1 }?.let { soundPool.play(it, 1f, 1f, 1, 0, 1f) }
                3 -> soundIds.three.takeIf { it != -1 }?.let { soundPool.play(it, 1f, 1f, 1, 0, 1f) }
                2 -> soundIds.two.takeIf { it != -1 }?.let { soundPool.play(it, 1f, 1f, 1, 0, 1f) }
                1 -> soundIds.one.takeIf { it != -1 }?.let { soundPool.play(it, 1f, 1f, 1, 0, 1f) }
            }
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
        startingString = startingString,
        onBackPressed = {
            fiveSecondViewModel.cancelCountdown()
        }
    )
}

@Composable
fun FiveSecondScreenContent(
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