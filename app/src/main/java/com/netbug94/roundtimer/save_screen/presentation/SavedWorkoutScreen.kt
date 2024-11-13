package com.netbug94.roundtimer.save_screen.presentation

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.netbug94.roundtimer.R
import com.netbug94.roundtimer.common.BackIconButton
import com.netbug94.roundtimer.first_screen.presentation.WorkoutInputViewModel
import com.netbug94.roundtimer.save_screen.domain.WorkoutRoomEntity
import com.netbug94.roundtimer.save_screen.data.toWorkoutInput
import com.netbug94.roundtimer.setting_screens.settings_screen.transition_screen_settings.presentation.TransitionScreenOption
import com.netbug94.roundtimer.setting_screens.settings_screen.transition_screen_settings.presentation.TransitionSettingsViewModel
import kotlinx.coroutines.launch

@Composable
fun SavedWorkoutScreen(
    roomViewModel: WorkoutRoomViewModel,
    onHomeClick: () -> Unit,
    workoutInputVM: WorkoutInputViewModel,
    onStartClickFive: () -> Unit,
    onStartClickThree: () -> Unit,
    onSwipeBack: () -> Unit
) {
    val settingsViewModel: TransitionSettingsViewModel = viewModel()

    WorkoutListScreen(
        roomViewModel = roomViewModel,
        onHomeClick = onHomeClick,
        onSwipeBack = onSwipeBack,
        workoutInputVM = workoutInputVM,
        settingsViewModel= settingsViewModel,
        onStartClickFive = onStartClickFive,
        onStartClickThree = onStartClickThree
    )
}

@Composable
fun WorkoutListScreen(
    roomViewModel: WorkoutRoomViewModel,
    onHomeClick: () -> Unit,
    onSwipeBack: () -> Unit,
    workoutInputVM: WorkoutInputViewModel,
    settingsViewModel: TransitionSettingsViewModel,
    onStartClickFive: () -> Unit,
    onStartClickThree: () -> Unit
) {
    val workouts by roomViewModel.allWorkouts.observeAsState(emptyList())
    val workoutToDelete = roomViewModel.workoutToDelete.observeAsState().value
    @Suppress("SpellCheckingInspection")
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val deleteSuccessful = stringResource(R.string.DeleteSuccessfulString)
    val emptyListString = stringResource(R.string.EmptyListString)

    BackHandler {
        onSwipeBack()
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { paddingValues ->
        val paddingValue = paddingValues

        BackIconButton(
            boxModifier = Modifier
                .fillMaxWidth()
                .systemBarsPadding()
                .padding(start = 16.dp, top = 8.dp),
            boxAlignment = Alignment.CenterStart,
            onBackIconClick = onHomeClick,
            iconTint = MaterialTheme.colorScheme.primary
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValue)
                .padding(top = 80.dp)
        ) {
            if (workouts.isEmpty()) {
                Column(modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(modifier = Modifier.padding(bottom = 130.dp),
                        text = emptyListString,
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
            } else {
                // List of workouts
                LazyColumn(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(
                        items = workouts,
                        key = { workout -> workout.id }
                    ) { workout ->
                        WorkoutListItem(
                            workout = workout,
                            onDelete = {
                                roomViewModel.showDeleteConfirmation(workout)
                            },
                            onEdit = { updatedWorkout ->
                                roomViewModel.updateRoomWorkoutName(updatedWorkout)
                            },
                            onClick = {
                                workoutInputVM.setWorkoutInput(workout.toWorkoutInput())
                                when (settingsViewModel.selectedOption.value) {
                                    TransitionScreenOption.FIVE_SECONDS -> {
                                        onStartClickFive()
                                    }
                                    TransitionScreenOption.THREE_SECONDS -> {
                                        onStartClickThree()
                                    }
                                }
                            },
                            showSnackbar = { message ->
                                coroutineScope.launch {
                                    snackbarHostState.showSnackbar(
                                        message,
                                        duration = SnackbarDuration.Short
                                    )
                                }
                            }
                        )
                    }
                }
            }

            // Handle the delete confirmation dialog
            if (workoutToDelete != null) {
                DeleteConfirmationDialog(
                    workout = workoutToDelete,
                    onConfirm = {
                        roomViewModel.deleteRoomWorkout(workoutToDelete)
                        roomViewModel.clearDeleteConfirmation()
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar(
                                deleteSuccessful,
                                duration = SnackbarDuration.Short,
                            )
                        }
                    },
                    onDismiss = {
                        roomViewModel.clearDeleteConfirmation()
                    }
                )
            }
        }
    }
}

@Composable
fun WorkoutListItem(
    workout: WorkoutRoomEntity,
    onDelete: () -> Unit,
    onEdit: (WorkoutRoomEntity) -> Unit,
    onClick: () -> Unit,
    @Suppress("SpellCheckingInspection")
    showSnackbar: (String) -> Unit,
) {
    var isEditing by rememberSaveable { mutableStateOf(false) }
    var editedName by rememberSaveable { mutableStateOf(workout.baseName) }
    val textFieldTitle = stringResource(R.string.TextFieldTitleWorkoutListItemString)
    val updateSuccessful = stringResource(R.string.UpdatedSuccessfulString)
    val cannotBeEmpty = stringResource(R.string.CannotBeEmptyString)
    val roundsString = stringResource(R.string.RoundsString)
    val roundTimeString = stringResource(R.string.RoundTimeString)
    val restTimeString = stringResource(R.string.RestTimeString)
    val deleteButtonString = stringResource(R.string.DeleteString)
    val cancelButtonString = stringResource(R.string.CancelString)

    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = 16.dp)
        .padding(horizontal = 6.dp)
        .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier
            .padding(16.dp)
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                if (isEditing) {
                    TextField(
                        value = editedName,
                        onValueChange = { editedName = it },
                        label = { Text(textFieldTitle) },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions.Default.copy(
                            capitalization = KeyboardCapitalization.Sentences
                        ),
                        textStyle = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.SemiBold,
                            fontStyle = FontStyle.Italic
                        ),
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    IconButton(
                        onClick = {
                            if (editedName.isNotBlank()) {
                                val updatedWorkout = workout.copy(baseName = editedName)
                                onEdit(updatedWorkout)
                                isEditing = false
                                showSnackbar(updateSuccessful)
                            } else {
                                showSnackbar(cannotBeEmpty)
                            }
                        },
                        modifier = Modifier.size(24.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Done,
                            contentDescription = "Save Workout Name",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                } else {
                    Text(
                        text = workout.
                        baseName,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    IconButton(
                        onClick = { isEditing = true },
                        modifier = Modifier.size(24.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "Edit Workout Name",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "$roundsString: ${workout.roundNumber}",
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = "$roundTimeString: ${workout.roundMinutes}m ${workout.roundSeconds}s",
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = "$restTimeString: ${workout.restMinutes}m ${workout.restSeconds}s",
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.height(8.dp))


            if (isEditing) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(
                        onClick = {
                            onDelete()
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
                    ) {
                        Text(
                            deleteButtonString,
                            color = MaterialTheme.colorScheme.onError,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    TextButton(onClick = { isEditing = false }) {
                        Text(
                            cancelButtonString,
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
        }
    }
}