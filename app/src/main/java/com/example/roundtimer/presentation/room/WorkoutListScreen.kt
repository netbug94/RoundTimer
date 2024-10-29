package com.example.roundtimer.presentation.room

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun WorkoutListScreen(roomViewModel: RoomViewModel, onWorkoutSelected: (WorkoutEntity) -> Unit) {
    val workouts by roomViewModel.allWorkouts.observeAsState(initial = emptyList())

    // Call the composable that displays the list
    WorkoutList(workouts = workouts, onWorkoutSelected = onWorkoutSelected)
}

@Composable
fun WorkoutList(workouts: List<WorkoutEntity>, onWorkoutSelected: (WorkoutEntity) -> Unit) {
    LazyColumn(modifier = Modifier.systemBarsPadding().padding(top = 16.dp)) {
        items(workouts) { workout ->
            WorkoutListItem(workout = workout, onClick = { onWorkoutSelected(workout) })
        }
    }
}

@Composable
fun WorkoutListItem(workout: WorkoutEntity, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = onClick)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Rounds: ${workout.roundNumber}", style = MaterialTheme.typography.bodyLarge)
            Text(
                text = "Round Time: ${workout.roundMinutes}m ${workout.roundSeconds}s",
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = "Rest Time: ${workout.restMinutes}m ${workout.restSeconds}s",
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}
