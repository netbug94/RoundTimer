package com.netbug94.roundtimer.round_screen.presentation

import android.media.AudioManager
import android.media.ToneGenerator
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.netbug94.roundtimer.R
import com.netbug94.roundtimer.common.BackIconButton
import com.netbug94.roundtimer.first_screen.presentation.WorkoutInputViewModel
import com.netbug94.roundtimer.quote.presentation.QuoteFetcher
import com.netbug94.roundtimer.save_screen.domain.WorkoutRoomEntity
import com.netbug94.roundtimer.setting_screens.settings_screen.round_beep_settings.presentation.RoundBeepViewModel
import com.netbug94.roundtimer.ui.theme.customColorScheme
import kotlinx.coroutines.delay

@Composable
fun RoundScreen(
    onSwipeBack: () -> Unit,
    workoutInputVM: WorkoutInputViewModel,
    selectedWorkout: WorkoutRoomEntity? = null,
    beepSettingsViewModel: RoundBeepViewModel,
    onHomeIconClick: () -> Unit,
    onListIconClick: () -> Unit
) {
    val workoutInput by workoutInputVM.workoutInput.collectAsState()
    val totalRounds: Int
    val roundDurationSeconds: Int
    val restDurationSeconds: Int
    val customColorText = customColorScheme().customBorderColor
    val restTextColor = customColorScheme().redTextColor
    if (selectedWorkout != null) {
        totalRounds = selectedWorkout.roundNumber.coerceAtLeast(1)
        roundDurationSeconds = selectedWorkout.roundMinutes * 60 + selectedWorkout.roundSeconds
        restDurationSeconds = selectedWorkout.restMinutes * 60 + selectedWorkout.restSeconds
    } else {
        totalRounds = workoutInput.roundNumber.coerceAtLeast(1)
        roundDurationSeconds = workoutInput.roundMinutes * 60 + workoutInput.roundSeconds
        restDurationSeconds = workoutInput.restMinutes * 60 + workoutInput.restSeconds
    }
    var currentRound by rememberSaveable { mutableIntStateOf(1) }
    var isRest by rememberSaveable { mutableStateOf(false) }
    var timeRemaining by rememberSaveable { mutableIntStateOf(roundDurationSeconds) }
    var timerStatus by rememberSaveable { mutableStateOf(TimerStatus.Running) }
    val toneGenerator = remember {
        ToneGenerator(AudioManager.STREAM_MUSIC, 100)
    }
    val isBeepMute by beepSettingsViewModel.isBeepOptionLoaded.collectAsState()

    DisposableEffect(Unit) {
        onDispose {
            toneGenerator.release()
        }
    }

    BackHandler {
        timerStatus = TimerStatus.Paused
        onSwipeBack()
    }

    LaunchedEffect(timerStatus, currentRound, isRest) {
        if (timerStatus == TimerStatus.Running && currentRound == 1 && !isRest && !isBeepMute) {
            toneGenerator.startTone(ToneGenerator.TONE_SUP_PIP, 100)
        }

        while (timerStatus == TimerStatus.Running && (currentRound <= totalRounds || isRest)) {
            delay(1000L)
            if (timeRemaining > 0) {
                timeRemaining -= 1
            } else {
                if (!isRest) {
                    isRest = true
                    timeRemaining = restDurationSeconds
                } else {
                    isRest = false
                    currentRound += 1
                    if (currentRound <= totalRounds) {
                        timeRemaining = roundDurationSeconds
                    } else {
                        timerStatus = TimerStatus.Completed
                    }
                }
            }
        }
    }

    LaunchedEffect(timeRemaining) {
        when (timeRemaining) {
            in 1..3 -> {
                if (!isBeepMute) {
                    toneGenerator.startTone(ToneGenerator.TONE_PROP_BEEP, 100)
                }
            }
            0 -> {
                if (!isBeepMute) {
                    toneGenerator.startTone(ToneGenerator.TONE_CDMA_PRESSHOLDKEY_LITE, 100)
                }
            }
        }
    }

    val minutes = timeRemaining / 60
    val seconds = timeRemaining % 60
    val timeFormatted = "%02d:%02d".format(minutes, seconds)

    RoundScreenContent(
        currentRound = currentRound,
        totalRounds = totalRounds,
        isRest = isRest,
        timeFormatted = timeFormatted,
        timerStatus = timerStatus,
        customColorText = customColorText,
        restTextColor = restTextColor,
        onPauseResumeClick = {
            timerStatus = if (timerStatus == TimerStatus.Running) TimerStatus.Paused else TimerStatus.Running
        },
        onFinishClick = onSwipeBack,
        onHomeIconClick = onHomeIconClick,
        onListIconClick = onListIconClick
    )
}

@Composable
fun RoundScreenContent(
    currentRound: Int,
    totalRounds: Int,
    isRest: Boolean,
    timeFormatted: String,
    timerStatus: TimerStatus,
    customColorText: Color,
    restTextColor: Color,
    onPauseResumeClick: () -> Unit,
    onFinishClick: () -> Unit,
    onHomeIconClick: () -> Unit,
    onListIconClick: () -> Unit
) {
    val restString = stringResource(id = R.string.RestString)
    val roundString = stringResource(id = R.string.RoundString)
    val pauseString = stringResource(id = R.string.PauseString)
    val resumeString = stringResource(id = R.string.ResumeString)
    val finishString = stringResource(id = R.string.FinishString)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        if (timerStatus == TimerStatus.Running || timerStatus == TimerStatus.Paused) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = if (isRest) restString else "$roundString $currentRound / $totalRounds",
                    style = MaterialTheme.typography.displayMedium,
                    fontWeight = FontWeight.Bold,
                    color = if (isRest) restTextColor else customColorText
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = timeFormatted,
                    fontSize = 70.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (isRest) restTextColor else customColorText
                )
            }

            Spacer(modifier = Modifier.height(40.dp))

            Button(onClick = onPauseResumeClick) {
                Text(text = if (timerStatus == TimerStatus.Running) pauseString else resumeString)
            }

        } else if (timerStatus == TimerStatus.Completed) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ) {

                QuoteFetcher()

                Spacer(modifier = Modifier.height(20.dp))

                Button(onClick = onFinishClick) {
                    Text(finishString)
                }
            }
        }
    }
    Row(modifier = Modifier
        .fillMaxWidth()
        .systemBarsPadding()
        .padding(16.dp),
        verticalAlignment = Alignment.Bottom) {
        BackIconButton(boxModifier = Modifier
            .fillMaxWidth()
            .weight(1f),
            boxAlignment = Alignment.CenterStart,
            size = 40.dp,
            onBackIconClick = { onHomeIconClick() },
            iconTint = MaterialTheme.colorScheme.primary,
            buttonIcon = Icons.Default.Home
        )

        BackIconButton(boxModifier = Modifier
            .fillMaxWidth()
            .weight(1f),
            boxAlignment = Alignment.CenterEnd,
            size = 40.dp,
            onBackIconClick = { onListIconClick() },
            iconTint = MaterialTheme.colorScheme.primary,
            buttonIcon = Icons.AutoMirrored.Default.List
        )
    }
}