package com.netbug94.roundtimer.save_screen.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.netbug94.roundtimer.save_screen.domain.WorkoutRoomRepository

class WorkoutRoomViewModelFactory(private val repository: WorkoutRoomRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WorkoutRoomViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WorkoutRoomViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
