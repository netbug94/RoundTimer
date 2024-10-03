package com.example.roundtimer.presentation.first_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
internal fun FirstScreen() {
    val workoutInputVM: WorkoutInputViewModel = viewModel()
    val workoutInput by workoutInputVM.workoutInput

    FirstScreenContent(
        workoutInput = workoutInput,
        onInputChange = { newInput -> workoutInputVM.updateWorkoutInput(newInput) }
    )
}

@Composable
fun FirstScreenContent(
    workoutInput: WorkoutInput,
    onInputChange: (WorkoutInput) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            InputOutlinedTextField(
                value = workoutInput.roundNumber,
                onValueChange = { newRoundNumber ->
                    onInputChange(workoutInput.copy(roundNumber = newRoundNumber))
                },
                label = "Rounds Number"
            )
        }
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp)
        ) {
            InputOutlinedTextField(modifier = Modifier.weight(1.2f),
                value = workoutInput.roundMinutes,
                onValueChange = { newRoundMinutes ->
                    onInputChange(workoutInput.copy(roundMinutes = newRoundMinutes))
                },
                label = "Round Min"
            )

            InputOutlinedTextField(modifier = Modifier.weight(1f),
                value = workoutInput.roundNumber,
                onValueChange = { newRoundNumber ->
                    onInputChange(workoutInput.copy(roundNumber = newRoundNumber))
                },
                label = "Round Sec"

            )
        }
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp)
        ) {
            InputOutlinedTextField(modifier = Modifier.weight(1.4f),
                value = workoutInput.roundNumber,
                onValueChange = { newRoundNumber ->
                    onInputChange(workoutInput.copy(roundNumber = newRoundNumber))
                },
                label = "Rest Min"
            )

            InputOutlinedTextField(modifier = Modifier.weight(1f),
                value = workoutInput.roundNumber,
                onValueChange = { newRoundNumber ->
                    onInputChange(workoutInput.copy(roundNumber = newRoundNumber))
                },
                label = "Rest Sec"
            )
        }
    }
}
