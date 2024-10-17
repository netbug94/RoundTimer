package com.example.roundtimer.presentation.round_screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.roundtimer.presentation.view_model.WorkoutInputViewModel

@Composable
fun TestLogic(onSwipeBack: () -> Unit, workoutInputVM: WorkoutInputViewModel) {
    val workoutInput by workoutInputVM.workoutInput.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Access roundNumber from workoutInput and display it
        Text("Round Number: ${workoutInput.roundNumber}")
    }
    BackHandler {
        onSwipeBack()
    }
}
