package com.example.roundtimer.presentation.round_screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.roundtimer.presentation.common.view_model.WorkoutInputViewModel
import com.example.roundtimer.ui.theme.customColorScheme
import kotlinx.coroutines.delay

@Composable
fun RoundScreen(
    onSwipeBack: () -> Unit,
    workoutInputVM: WorkoutInputViewModel
) {
    val workoutInput by workoutInputVM.workoutInput.collectAsState()

    val totalRounds = workoutInput.roundNumber.coerceAtLeast(1)
    val roundDurationSeconds = workoutInput.roundMinutes * 60 + workoutInput.roundSeconds
    val restDurationSeconds = workoutInput.restMinutes * 60 + workoutInput.restSeconds
    val customColorText = customColorScheme().customBorderColor

    var currentRound by remember { mutableIntStateOf(1) }
    var isRest by remember { mutableStateOf(false) }
    var timeRemaining by remember { mutableIntStateOf(roundDurationSeconds) }
    var timerStatus by remember { mutableStateOf(TimerStatus.Running) }

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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        if (timerStatus == TimerStatus.Running || timerStatus == TimerStatus.Paused) {

            if (!isRest) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Round $currentRound / $totalRounds",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        color = customColorText
                    )
                }
            }

            Spacer(modifier = Modifier.height(30.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = if (isRest) "Rest" else "Round",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Medium,
                    color = if (isRest) customColorText.copy(alpha = 0.7f) else customColorText
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
                    color = customColorText
                )
            }

            Spacer(modifier = Modifier.height(40.dp))

            Button(
                onClick = {
                    timerStatus = if (timerStatus == TimerStatus.Running) TimerStatus.Paused else TimerStatus.Running
                }
            ) {
                Text(text = if (timerStatus == TimerStatus.Running) "Pause" else "Resume")
            }

        } else if (timerStatus == TimerStatus.Completed) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = "Completed",
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold,
                    color = customColorText
                )

                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}