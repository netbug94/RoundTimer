package com.example.roundtimer.presentation.saved_workout_screen

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.roundtimer.R
import com.example.roundtimer.domain.room_domain.WorkoutRoomEntity

@Composable
fun DeleteConfirmationDialog(
    workout: WorkoutRoomEntity,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    val deleteWorkoutString = stringResource(R.string.DeleteEntity)
    val areYouSureString = stringResource(R.string.AreYouSureString)
    val deleteString = stringResource(R.string.Delete)
    val cancelString = stringResource(R.string.Cancel)

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = deleteWorkoutString) },
        text = { Text(text = "$areYouSureString \"${workout.baseName}\"?") },
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text(deleteString)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(cancelString)
            }
        }
    )
}
