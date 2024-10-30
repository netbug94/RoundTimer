package com.example.roundtimer.presentation.saved_workout_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.roundtimer.domain.room_domain.WorkoutRoomEntity

@Composable
fun SavedWorkoutScreen(
    roomViewModel: WorkoutRoomViewModel,
    onWorkoutSelected: (WorkoutRoomEntity) -> Unit
) {
    WorkoutListScreen(
        roomViewModel = roomViewModel,
        onWorkoutSelected = onWorkoutSelected
    )
}

@Composable
fun WorkoutListScreen(
    roomViewModel: WorkoutRoomViewModel,
    onWorkoutSelected: (WorkoutRoomEntity) -> Unit
) {
    val workouts by roomViewModel.allWorkouts.observeAsState(emptyList())
    val workoutToDelete = roomViewModel.workoutToDelete.observeAsState().value

    LazyColumn(
        modifier = Modifier
            .systemBarsPadding()
            .padding(horizontal = 8.dp)
    ) {
        items(
            items = workouts,
            key = { workout -> workout.id } // Ensure 'id' is unique
        ) { workout ->
            WorkoutListItem(
                workout = workout,
                onDelete = {
                    roomViewModel.showDeleteConfirmation(workout)
                },
                onEdit = { updatedWorkout ->
                    roomViewModel.updateRoomWorkoutName(updatedWorkout)
                },
                onClick = { onWorkoutSelected(workout) }
            )
        }
    }

    if (workoutToDelete != null) {
        DeleteConfirmationDialog(
            workout = workoutToDelete,
            onConfirm = {
                roomViewModel.deleteRoomWorkout(workoutToDelete)
                roomViewModel.clearDeleteConfirmation()
            },
            onDismiss = {
                roomViewModel.clearDeleteConfirmation()
            }
        )
    }
}

@Composable
fun WorkoutListItem(
    workout: WorkoutRoomEntity,
    onDelete: () -> Unit,
    onEdit: (WorkoutRoomEntity) -> Unit,
    onClick: () -> Unit
) {
    var isEditing by rememberSaveable { mutableStateOf(false) }
    var editedName by rememberSaveable { mutableStateOf(workout.name) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            // Title and Edit/Save Icon in a Row
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                if (isEditing) {
                    TextField(
                        value = editedName,
                        onValueChange = { editedName = it },
                        label = { Text("Workout Name") },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions.Default.copy(
                            capitalization = KeyboardCapitalization.Sentences
                        ),
                        textStyle = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.SemiBold,
                            fontStyle = FontStyle.Italic,
                            fontSize = 18.sp // Adjust as desired
                        ),
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    IconButton(
                        onClick = {
                            if (editedName.isNotBlank()) {
                                val updatedWorkout = workout.copy(name = editedName)
                                onEdit(updatedWorkout)
                                isEditing = false
                            }
                            // Optionally, handle empty input (e.g., show a Snackbar)
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
                        text = workout.name,
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

            // Workout Details
            Text(
                text = "Rounds: ${workout.roundNumber}",
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = "Round Time: ${workout.roundMinutes}m ${workout.roundSeconds}s",
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = "Rest Time: ${workout.restMinutes}m ${workout.restSeconds}s",
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Delete and Cancel Buttons (Visible Only in Edit Mode)
            if (isEditing) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(
                        onClick = onDelete,
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
                    ) {
                        Text(
                            "Delete",
                            color = MaterialTheme.colorScheme.onError,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    TextButton(onClick = { isEditing = false }) {
                        Text(
                            "Cancel",
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
        }
    }
}