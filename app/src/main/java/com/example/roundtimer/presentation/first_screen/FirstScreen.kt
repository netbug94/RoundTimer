package com.example.roundtimer.presentation.first_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.roundtimer.presentation.first_screen.preview_round_box.FocusViewModel
import com.example.roundtimer.presentation.first_screen.preview_round_box.PreviewRoundBox
import com.example.roundtimer.presentation.first_screen.save_round_banner.SaveRoundBanner
import com.example.roundtimer.presentation.first_screen.settings.SettingsButton
import com.example.roundtimer.presentation.first_screen.start_and_clear_buttons.StartAndClearButton
import kotlinx.coroutines.delay

@Composable
internal fun FirstScreen(onNavigation: () -> Unit) {
    val workoutInputVM: WorkoutInputViewModel = viewModel()
    val workoutInput by workoutInputVM.workoutInput

    val focusViewModel: FocusViewModel = viewModel()
    val isFocused by focusViewModel.isFocused.collectAsState()

    FirstScreenContent(
        workoutInput = workoutInput,
        onInputChange = { newInput -> workoutInputVM.updateWorkoutInput(newInput) },
        isFocused = isFocused,
    )
}

@Composable
fun FirstScreenContent(
    workoutInput: WorkoutInput,
    onInputChange: (WorkoutInput) -> Unit,
    isFocused: Boolean
) {
    val firstScreenHorizontalPadding = 12.dp
    val ifFocusColor = if (isFocused) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface

    var showBanner by remember { mutableStateOf(false) }
    if (showBanner) {
        LaunchedEffect(Unit) {
            delay(1500)
            showBanner = false
        }
    }

    SaveRoundBanner(showBanner = showBanner)

    Column(
        modifier = Modifier.fillMaxSize()
            .imePadding().systemBarsPadding(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(modifier = Modifier
            .fillMaxSize()
            .weight(1f)
            .padding(end = firstScreenHorizontalPadding, top = 30.dp),
            horizontalArrangement = Arrangement.End) {
            SettingsButton()
        }

        Column(Modifier.fillMaxSize().weight(1f)
            .padding(horizontal = firstScreenHorizontalPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {

            PreviewRoundBox {
                showBanner = true
            }
        }

        Column(Modifier.fillMaxSize().weight(4f)
            .padding(horizontal = firstScreenHorizontalPadding),
            horizontalAlignment = Alignment.CenterHorizontally) {

            Spacer(Modifier.height(16.dp))

            InputSingleField(
                value = workoutInput.roundNumber,
                onValueChange = { newRoundNumber ->
                    onInputChange(workoutInput.copy(roundNumber = newRoundNumber))
                }
            )

            HorizontalDivider(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 2.dp),
                thickness = 1.dp,
                color = ifFocusColor
            )

            InputDoubleFieldRow(
                firstValue = workoutInput.roundMinutes,
                onFirstValueChange = { newRoundMinutes ->
                    onInputChange(workoutInput.copy(roundMinutes = newRoundMinutes))
                },
                firstLabel = "Round Min",
                secondValue = workoutInput.roundSeconds,
                onSecondValueChange = { newRoundSeconds ->
                    onInputChange(workoutInput.copy(roundSeconds = newRoundSeconds))
                },
                secondLabel = "Round Sec"
            )

            InputDoubleFieldRow(
                firstValue = workoutInput.restMinutes,
                onFirstValueChange = { newRestMinutes ->
                    onInputChange(workoutInput.copy(restMinutes = newRestMinutes))
                },
                firstLabel = "Rest Min",
                secondValue = workoutInput.restSeconds,
                onSecondValueChange = { newRestSeconds ->
                    onInputChange(workoutInput.copy(restSeconds = newRestSeconds))
                },
                secondLabel = "Rest Sec"
            )

            Column(modifier = Modifier.fillMaxWidth().padding(top = 20.dp),
                verticalArrangement = Arrangement.Center) {
                StartAndClearButton()
            }
        }
    }
}

@Composable
private fun InputSingleField(
    value: Int,
    onValueChange: (Int) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        InputOutlinedTextField(
            label = "Rounds Number",
            value = value,
            onValueChange = onValueChange
        )
    }
}

@Composable
private fun InputDoubleFieldRow(
    firstValue: Int,
    onFirstValueChange: (Int) -> Unit,
    firstLabel: String,
    secondValue: Int,
    onSecondValueChange: (Int) -> Unit,
    secondLabel: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        InputOutlinedTextField(
            modifier = Modifier.weight(1f),
            label = firstLabel,
            value = firstValue,
            onValueChange = onFirstValueChange
        )

        Spacer(Modifier.weight(0.01f))

        InputOutlinedTextField(
            modifier = Modifier.weight(1f),
            label = secondLabel,
            value = secondValue,
            onValueChange = onSecondValueChange
        )
    }
}