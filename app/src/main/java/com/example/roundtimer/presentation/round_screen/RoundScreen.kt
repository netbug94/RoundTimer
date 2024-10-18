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

    // Ensure totalRounds is at least 1
    val totalRounds = workoutInput.roundNumber.coerceAtLeast(1)
    val roundDurationSeconds = workoutInput.roundMinutes * 60 + workoutInput.roundSeconds
    val restDurationSeconds = workoutInput.restMinutes * 60 + workoutInput.restSeconds
    val customColorText = customColorScheme().customBorderColor

    // State variables
    var currentRound by remember { mutableIntStateOf(1) }
    var isRest by remember { mutableStateOf(false) }
    var timeRemaining by remember { mutableIntStateOf(roundDurationSeconds) }
    var isTimerRunning by remember { mutableStateOf(true) }

    BackHandler {
        isTimerRunning = false
        onSwipeBack()
    }

    // Timer logic
    LaunchedEffect(isTimerRunning, currentRound, isRest) {
        while (isTimerRunning && (currentRound <= totalRounds || isRest)) {
            delay(1000L)
            if (timeRemaining > 0) {
                timeRemaining -= 1
            } else {
                if (!isRest) {
                    // Finished a normal round, start rest period
                    isRest = true
                    timeRemaining = restDurationSeconds
                } else {
                    // Finished a rest period, move to next round
                    isRest = false
                    currentRound += 1
                    if (currentRound <= totalRounds) {
                        timeRemaining = roundDurationSeconds
                    } else {
                        // All rounds and rests completed
                        isTimerRunning = false
                    }
                }
            }
        }
    }

    // Format time as MM:SS using Kotlin's string interpolation
    val minutes = timeRemaining / 60
    val seconds = timeRemaining % 60
    val timeFormatted = "%02d:%02d".format(minutes, seconds)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp), // Added padding for better UI
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        if (isTimerRunning) {
            // Timer UI
            // Display current round only during active rounds
            if (!isRest) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Round $currentRound / $totalRounds",
                        fontSize = 30.sp, // Adjusted font size for better visibility
                        fontWeight = FontWeight.Bold,
                        color = customColorText
                    )
                }
            }

            Spacer(modifier = Modifier.height(30.dp))

            // Display current mode (Round or Rest)
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

            // Display timer
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

            // Optional: Add controls like Pause/Resume or Reset
            /* Example:
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(onClick = { isTimerRunning = !isTimerRunning }) {
                    Text(if (isTimerRunning) "Pause" else "Resume")
                }
                Button(onClick = {
                    // Reset logic
                    currentRound = 1
                    isRest = false
                    timeRemaining = roundDurationSeconds
                    isTimerRunning = true
                }) {
                    Text("Reset")
                }
            }
            */
        } else {
            // Completion UI
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

                // Optional: Add a Reset button or navigation option
                /* Example:
                Button(onClick = {
                    // Reset logic
                    currentRound = 1
                    isRest = false
                    timeRemaining = roundDurationSeconds
                    isTimerRunning = true
                }) {
                    Text("Reset")
                }
                */
            }
        }
    }
}
