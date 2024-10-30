package com.example.roundtimer.presentation.saved_workout_screen

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import com.example.roundtimer.domain.room_domain.WorkoutRoomEntity

@Composable
fun DeleteConfirmationDialog(
    workout: WorkoutRoomEntity,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Delete Workout") },
        text = { Text(text = "Are you sure you want to delete \"${workout.name}\"?") },
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text("Delete")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}