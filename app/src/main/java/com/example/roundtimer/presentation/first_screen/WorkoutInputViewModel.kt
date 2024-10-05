package com.example.roundtimer.presentation.first_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class WorkoutInputViewModel : ViewModel() {
    private var _workoutInput = mutableStateOf(WorkoutInput(0, 0, 0, 0, 0))
    val workoutInput: State<WorkoutInput> = _workoutInput

    fun updateWorkoutInput(newInput: WorkoutInput) {
        _workoutInput.value = newInput
    }

    fun resetWorkoutInput() {
        _workoutInput.value = WorkoutInput(0,0,0,0,0)
    }
}
