package com.netbug94.roundtimer.save_screen.presentation

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.netbug94.roundtimer.R
import com.netbug94.roundtimer.save_screen.domain.WorkoutRoomEntity

@Composable
fun DeleteConfirmationDialog(
    workout: WorkoutRoomEntity,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    val deleteWorkoutString = stringResource(R.string.DeleteEntityString)
    val areYouSureString = stringResource(R.string.AreYouSureString)
    val deleteString = stringResource(R.string.DeleteString)
    val cancelString = stringResource(R.string.CancelString)

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
