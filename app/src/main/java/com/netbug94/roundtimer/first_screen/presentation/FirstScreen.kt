package com.netbug94.roundtimer.first_screen.presentation

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.netbug94.roundtimer.R
import com.netbug94.roundtimer.common.getImageIds
import com.netbug94.roundtimer.first_screen.presentation.preview_round_box.PreviewRoundBox
import com.netbug94.roundtimer.first_screen.presentation.save_round_banner.SaveRoundBanner
import com.netbug94.roundtimer.first_screen.presentation.settings_button.SettingsButton
import com.netbug94.roundtimer.first_screen.presentation.start_and_clear_buttons.StartAndClearButtons
import com.netbug94.roundtimer.focus_handler.presentation.rememberFocusHandler
import com.netbug94.roundtimer.save_screen.presentation.WorkoutRoomViewModel
import kotlinx.coroutines.delay

@Composable
fun FirstScreen(
    onStartClickFive: () -> Unit,
    onStartClickThree: () -> Unit,
    workoutInputVM: WorkoutInputViewModel,
    roomViewModel: WorkoutRoomViewModel,
    onSettingsClick: () -> Unit,
    onTipsClick: () -> Unit,
    onAboutClick: () -> Unit,
    onCollectionClick: () -> Unit,
    onSwipeBack: () -> Unit
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
        onStartClickFive = onStartClickFive,
        onStartClickThree = onStartClickThree,
        onClearClick = workoutInputVM::clearWorkoutInput,
        onSettingsClick = onSettingsClick,
        onTipsClick = onTipsClick,
        onAboutClick = onAboutClick,
        workoutInputVM = workoutInputVM,
        roomViewModel = roomViewModel,
        onCollectionClick = onCollectionClick,
        onSwipeBack = onSwipeBack
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
    onStartClickFive: () -> Unit,
    onStartClickThree: () -> Unit,
    onClearClick: () -> Unit,
    onSettingsClick: () -> Unit,
    onTipsClick: () -> Unit,
    onAboutClick: () -> Unit,
    workoutInputVM: WorkoutInputViewModel,
    roomViewModel: WorkoutRoomViewModel,
    onCollectionClick: () -> Unit,
    onSwipeBack: () -> Unit
) {
    val firstScreenHorizontalPadding = 12.dp
    val imageId = getImageIds().second
    val focusHandler = rememberFocusHandler()
    val anyFieldFocused = focusHandler.anyFieldFocused.value
    val focusChanged = focusHandler.focusChanged
    val dividerColor = if (anyFieldFocused) {
        MaterialTheme.colorScheme.primary
    } else {
        MaterialTheme.colorScheme.onSurface
    }
    var showBanner by remember { mutableStateOf(false) }
    val roundMinString = stringResource(id = R.string.RoundMinString)
    val roundSecString = stringResource(id = R.string.RoundSecString)
    val restMinString = stringResource(id = R.string.RestMinString)
    val restSecString = stringResource(id = R.string.RestSecString)
    val firstWordString = stringResource(R.string.EntityFirstWordString)

    if (showBanner) {
        LaunchedEffect(Unit) {
            delay(1500)
            showBanner = false
        }
    }

    BackHandler {
        onSwipeBack()
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
                    roomViewModel.addRoomWorkout(currentWorkoutInput, firstWordString)
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
                firstLabel = roundMinString,
                secondValue = roundSeconds,
                onSecondValueChange = onRoundSecondsChange,
                secondLabel = roundSecString,
                onFocusChanged = focusChanged
            )

            InputDoubleFieldRow(
                firstValue = restMinutes,
                onFirstValueChange = onRestMinutesChange,
                firstLabel = restMinString,
                secondValue = restSeconds,
                onSecondValueChange = onRestSecondsChange,
                secondLabel = restSecString,
                onFocusChanged = focusChanged
            )

            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
                verticalArrangement = Arrangement.Center
            ) {
                StartAndClearButtons(
                    onStartClickFive = onStartClickFive,
                    onStartClickThree = onStartClickThree,
                    onClearClick = onClearClick
                )
            }
            Column(modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {

                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp, bottomStart = 10.dp, bottomEnd = 10.dp))
                        .clickable(
                            onClick = { onCollectionClick() },
                            indication = ripple(
                                bounded = false
                            ),
                            interactionSource = remember { MutableInteractionSource() }
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = imageId),
                        contentDescription = "Collection button",
                        modifier = Modifier.size(60.dp)
                    )
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
    val numberOfRoundsString = stringResource(id = R.string.NumberOfRoundsString)

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        InputOutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            label = numberOfRoundsString,
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