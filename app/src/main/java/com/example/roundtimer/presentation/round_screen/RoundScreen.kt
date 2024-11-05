package com.example.roundtimer.presentation.round_screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.roundtimer.R
import com.example.roundtimer.domain.room_domain.WorkoutRoomEntity
import com.example.roundtimer.presentation.first_screen.WorkoutInputViewModel
import com.example.roundtimer.ui.theme.customColorScheme
import kotlinx.coroutines.delay

@Composable
fun RoundScreen(
    onSwipeBack: () -> Unit,
    workoutInputVM: WorkoutInputViewModel,
    selectedWorkout: WorkoutRoomEntity? = null
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

    BackHandler {
        timerStatus = TimerStatus.Paused
        onSwipeBack()
    }

    LaunchedEffect(timerStatus, currentRound, isRest) {
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
        onFinishClick = onSwipeBack
    )
}

@Composable
fun RoundScreenContent(
    currentRound: Int,
    totalRounds: Int,
    isRest: Boolean,
    timeFormatted: String,
    timerStatus: TimerStatus,
    customColorText: androidx.compose.ui.graphics.Color,
    restTextColor: androidx.compose.ui.graphics.Color,
    onPauseResumeClick: () -> Unit,
    onFinishClick: () -> Unit
) {
    val restString = stringResource(id = R.string.Rest)
    val roundString = stringResource(id = R.string.Round)
    val pauseString = stringResource(id = R.string.Pause)
    val resumeString = stringResource(id = R.string.Resume)
    val finishString = stringResource(id = R.string.Finish)

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
        Column(modifier = Modifier.fillMaxWidth().weight(1f),
            horizontalAlignment = Alignment.Start) {
            Icon(modifier = Modifier
                .size(40.dp)
                .clip(
                    RoundedCornerShape(
                        topStart = 10.dp,
                        topEnd = 10.dp,
                        bottomStart = 10.dp,
                        bottomEnd = 10.dp
                    )
                )
                .clickable(
                    onClick = { }
                ),
                imageVector = Icons.Default.Home,
                contentDescription = "Back arrow button",
                tint = MaterialTheme.colorScheme.primary
            )
        }
        Column(modifier = Modifier.fillMaxWidth().weight(1f),
            horizontalAlignment = Alignment.End) {
            Icon(modifier = Modifier
                .size(40.dp)
                .clip(
                    RoundedCornerShape(
                        topStart = 10.dp,
                        topEnd = 10.dp,
                        bottomStart = 10.dp,
                        bottomEnd = 10.dp
                    )
                )
                .clickable(
                    onClick = { }
                ),
                imageVector = Icons.AutoMirrored.Filled.List,
                contentDescription = "Back arrow button",
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}
