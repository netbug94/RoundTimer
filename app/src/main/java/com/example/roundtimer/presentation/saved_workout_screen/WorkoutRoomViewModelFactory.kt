package com.example.roundtimer.presentation.saved_workout_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class WorkoutRoomViewModelFactory(private val repository: WorkoutRoomRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WorkoutRoomViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WorkoutRoomViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
