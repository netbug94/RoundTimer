package com.example.roundtimer.presentation.round_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.roundtimer.presentation.first_screen.WorkoutInputViewModel

@Composable
fun TestLogic(onSwipeBack: () -> Unit) {
    val workoutInputViewModel: WorkoutInputViewModel = viewModel()
    val workoutInput by workoutInputViewModel.workoutInput

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Access roundNumber from workoutInput and display it
        Text("Round Number: ${workoutInput.roundNumber}")
    }
}
