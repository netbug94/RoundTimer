package com.example.roundtimer.presentation.common.view_model

import androidx.lifecycle.ViewModel
import com.example.roundtimer.domain.WorkoutInput
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class WorkoutInputViewModel : ViewModel() {

    private val _workoutInput = MutableStateFlow(WorkoutInput(1, 0, 0, 0, 0))
    val workoutInput: StateFlow<WorkoutInput> = _workoutInput.asStateFlow()

    fun updateRoundNumber(newValue: Int) {
        _workoutInput.value = _workoutInput.value.copy(roundNumber = newValue)
    }

    fun updateRoundMinutes(newValue: Int) {
        _workoutInput.value = _workoutInput.value.copy(roundMinutes = newValue)
    }

    fun updateRoundSeconds(newValue: Int) {
        _workoutInput.value = _workoutInput.value.copy(roundSeconds = newValue)
    }

    fun updateRestMinutes(newValue: Int) {
        _workoutInput.value = _workoutInput.value.copy(restMinutes = newValue)
    }

    fun updateRestSeconds(newValue: Int) {
        _workoutInput.value = _workoutInput.value.copy(restSeconds = newValue)
    }

    fun clearWorkoutInput() {
        _workoutInput.value = WorkoutInput(1, 0, 0, 0, 0)
    }
}
