package com.netbug94.roundtimer.first_screen.presentation

import androidx.lifecycle.ViewModel
import com.netbug94.roundtimer.first_screen.domain.WorkoutParameters
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class WorkoutInputViewModel : ViewModel() {

    private val _workoutInput = MutableStateFlow(WorkoutParameters(roundNumber = 1, roundMinutes = 0, roundSeconds = 0, restMinutes = 0, restSeconds =  0))
    val workoutInput: StateFlow<WorkoutParameters> = _workoutInput.asStateFlow()

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
        _workoutInput.value = WorkoutParameters(roundNumber = 1, roundMinutes = 0, roundSeconds = 0, restMinutes = 0, restSeconds = 0)
    }
    fun setWorkoutInput(workoutInput: WorkoutParameters) {
        _workoutInput.value = workoutInput
    }
}
