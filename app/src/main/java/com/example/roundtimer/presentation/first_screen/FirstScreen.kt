package com.example.roundtimer.presentation.first_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
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
    val firstScreenHorizontalPadding = 12.dp

    Column(
        modifier = Modifier.fillMaxSize()
            .systemBarsPadding(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(Modifier.fillMaxSize().weight(1f))

        Column(Modifier.fillMaxSize().weight(1f)
            .padding(horizontal = firstScreenHorizontalPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            SaveRoundBox()
        }

        Column(Modifier.fillMaxSize().weight(4f)
            .padding(horizontal = firstScreenHorizontalPadding),
            horizontalAlignment = Alignment.CenterHorizontally) {

            HorizontalDivider(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp, bottom = 4.dp),
                thickness = 2.dp,
                color = MaterialTheme.colorScheme.primary
            )

            Row(
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
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                InputOutlinedTextField(
                    modifier = Modifier.weight(1f),
                    value = workoutInput.roundMinutes,
                    onValueChange = { newRoundMinutes ->
                        onInputChange(workoutInput.copy(roundMinutes = newRoundMinutes))
                    },
                    label = "Round Min"
                )

                InputOutlinedTextField(
                    modifier = Modifier.weight(1f),
                    value = workoutInput.roundSeconds,
                    onValueChange = { newRoundSeconds ->
                        onInputChange(workoutInput.copy(roundSeconds = newRoundSeconds))
                    },
                    label = "Round Sec"
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                InputOutlinedTextField(
                    modifier = Modifier.weight(1.5f),
                    value = workoutInput.restMinutes,
                    onValueChange = { newRestMinutes ->
                        onInputChange(workoutInput.copy(restMinutes = newRestMinutes))
                    },
                    label = "Rest Min"
                )

                InputOutlinedTextField(
                    modifier = Modifier.weight(1f),
                    value = workoutInput.restSeconds,
                    onValueChange = { newRestSeconds ->
                        onInputChange(workoutInput.copy(restSeconds = newRestSeconds))
                    },
                    label = "Rest Sec"
                )
            }
        }
    }
}
