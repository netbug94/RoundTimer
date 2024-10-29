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
import androidx.compose.material3.Button
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.roundtimer.R
import com.example.roundtimer.presentation.common.rememberFocusHandler
import com.example.roundtimer.presentation.common.view_model.WorkoutInputViewModel
import com.example.roundtimer.presentation.first_screen.preview_round_box.PreviewRoundBox
import com.example.roundtimer.presentation.first_screen.save_round_banner.SaveRoundBanner
import com.example.roundtimer.presentation.first_screen.settings_button.SettingsButton
import com.example.roundtimer.presentation.first_screen.start_and_clear_buttons.StartAndClearButton
import com.example.roundtimer.presentation.room.RoomViewModel
import kotlinx.coroutines.delay

@Composable
fun FirstScreen(
    onStartClick: () -> Unit,
    workoutInputVM: WorkoutInputViewModel,
    roomViewModel: RoomViewModel,
    onSettingsClick: () -> Unit,
    onTipsClick: () -> Unit,
    onAboutClick: () -> Unit,
    onListScreen: () -> Unit
) {
    val workoutInput by workoutInputVM.workoutInput.collectAsState()
    val roundNumber = workoutInput.roundNumber
    val roundMinutes = workoutInput.roundMinutes
    val roundSeconds = workoutInput.roundSeconds
    val restMinutes = workoutInput.restMinutes
    val restSeconds = workoutInput.restSeconds

    FirstScreenContent(
        roundNumber = roundNumber,
        onRoundNumberChange = workoutInputVM::updateRoundNumber,
        roundMinutes = roundMinutes,
        onRoundMinutesChange = workoutInputVM::updateRoundMinutes,
        roundSeconds = roundSeconds,
        onRoundSecondsChange = workoutInputVM::updateRoundSeconds,
        restMinutes = restMinutes,
        onRestMinutesChange = workoutInputVM::updateRestMinutes,
        restSeconds = restSeconds,
        onRestSecondsChange = workoutInputVM::updateRestSeconds,
        onStartClick = onStartClick,
        onClearClick = workoutInputVM::clearWorkoutInput,
        onSettingsClick = onSettingsClick,
        onTipsClick = onTipsClick,
        onAboutClick = onAboutClick,
        workoutInputVM = workoutInputVM,
        roomViewModel = roomViewModel,
        onListScreen = onListScreen
    )
}

@Composable
fun FirstScreenContent(
    roundNumber: Int,
    onRoundNumberChange: (Int) -> Unit,
    roundMinutes: Int,
    onRoundMinutesChange: (Int) -> Unit,
    roundSeconds: Int,
    onRoundSecondsChange: (Int) -> Unit,
    restMinutes: Int,
    onRestMinutesChange: (Int) -> Unit,
    restSeconds: Int,
    onRestSecondsChange: (Int) -> Unit,
    onStartClick: () -> Unit,
    onClearClick: () -> Unit,
    onSettingsClick: () -> Unit,
    onTipsClick: () -> Unit,
    onAboutClick: () -> Unit,
    workoutInputVM: WorkoutInputViewModel,
    roomViewModel: RoomViewModel,
    onListScreen: () -> Unit
) {
    val firstScreenHorizontalPadding = 12.dp

    val focusHandler = rememberFocusHandler()
    val anyFieldFocused = focusHandler.anyFieldFocused.value
    val focusChanged = focusHandler.focusChanged
    val dividerColor = if (anyFieldFocused) {
        MaterialTheme.colorScheme.primary
    } else {
        MaterialTheme.colorScheme.onSurface
    }

    var showBanner by remember { mutableStateOf(false) }
    if (showBanner) {
        LaunchedEffect(Unit) {
            delay(1500)
            showBanner = false
        }
    }

    SaveRoundBanner(
        showBanner = showBanner
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .imePadding()
            .systemBarsPadding(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
                .padding(end = firstScreenHorizontalPadding, top = 30.dp),
            horizontalArrangement = Arrangement.End
        ) {
            SettingsButton(
                onSettingsClick = onSettingsClick,
                onTipsClick = onTipsClick,
                onAboutClick = onAboutClick
            )
        }

        Column(
            Modifier
                .fillMaxSize()
                .weight(1f)
                .padding(horizontal = firstScreenHorizontalPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            PreviewRoundBox(
                roundNumber = roundNumber,
                roundMinutes = roundMinutes,
                roundSeconds = roundSeconds,
                restMinutes = restMinutes,
                restSeconds = restSeconds,
                isFocused = anyFieldFocused,
                onBannerShow = {
                    showBanner = true
                    val currentWorkoutInput = workoutInputVM.workoutInput.value
                    roomViewModel.addWorkout(currentWorkoutInput)
                }
            )
        }

        Column(
            Modifier
                .fillMaxSize()
                .weight(4f)
                .padding(horizontal = firstScreenHorizontalPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(16.dp))

            InputSingleField(
                value = roundNumber,
                onValueChange = onRoundNumberChange,
                onFocusChanged = focusChanged
            )

            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, bottom = 2.dp),
                thickness = 1.dp,
                color = dividerColor
            )

            InputDoubleFieldRow(
                firstValue = roundMinutes,
                onFirstValueChange = onRoundMinutesChange,
                firstLabel = stringResource(id = R.string.Round_Min),
                secondValue = roundSeconds,
                onSecondValueChange = onRoundSecondsChange,
                secondLabel = stringResource(id = R.string.Round_Sec),
                onFocusChanged = focusChanged
            )

            InputDoubleFieldRow(
                firstValue = restMinutes,
                onFirstValueChange = onRestMinutesChange,
                firstLabel = stringResource(id = R.string.Rest_Min),
                secondValue = restSeconds,
                onSecondValueChange = onRestSecondsChange,
                secondLabel = stringResource(id = R.string.Rest_Sec),
                onFocusChanged = focusChanged
            )

            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
                verticalArrangement = Arrangement.Center
            ) {
                StartAndClearButton(
                    onStartClick = onStartClick,
                    onClearClick = onClearClick
                )
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    onClick = {
                        onListScreen()
                    }
                ) {

                }
            }
        }
    }
}

@Composable
private fun InputSingleField(
    value: Int,
    onValueChange: (Int) -> Unit,
    onFocusChanged: (Boolean) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        InputOutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            label = stringResource(id = R.string.Number_Of_Rounds),
            value = value,
            onValueChange = onValueChange,
            onFocusChanged = onFocusChanged,
            isRoundNumberField = true
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
    secondLabel: String,
    onFocusChanged: (Boolean) -> Unit
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
            onValueChange = onFirstValueChange,
            onFocusChanged = onFocusChanged
        )

        Spacer(Modifier.weight(0.01f))

        InputOutlinedTextField(
            modifier = Modifier.weight(1f),
            label = secondLabel,
            value = secondValue,
            onValueChange = onSecondValueChange,
            onFocusChanged = onFocusChanged
        )
    }
}