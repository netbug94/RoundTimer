package com.example.roundtimer.presentation.first_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class WorkoutInputViewModel : ViewModel() {
    private var _workoutInput = mutableStateOf(WorkoutInput(roundNumber = 0, roundMinutes = 0, roundSeconds = 0, restMinutes = 0, restSeconds = 0))
    val workoutInput: State<WorkoutInput> = _workoutInput

    fun updateWorkoutInput(newInput: WorkoutInput) {
        _workoutInput.value = newInput
    }

    fun clearWorkoutInput() {
        _workoutInput.value = WorkoutInput(roundNumber = 0, roundMinutes = 0, roundSeconds = 0, restMinutes = 0, restSeconds = 0)
    }
}
