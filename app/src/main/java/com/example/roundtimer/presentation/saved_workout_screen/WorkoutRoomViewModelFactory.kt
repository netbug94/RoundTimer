package com.example.roundtimer.presentation.saved_workout_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.roundtimer.domain.room_domain.WorkoutRoomDao

class WorkoutRoomViewModelFactory(private val workoutDao: WorkoutRoomDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WorkoutRoomViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WorkoutRoomViewModel(workoutDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
