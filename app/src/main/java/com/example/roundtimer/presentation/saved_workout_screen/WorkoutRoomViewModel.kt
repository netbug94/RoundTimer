package com.example.roundtimer.presentation.saved_workout_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.roundtimer.domain.room_domain.WorkoutRoomEntity
import com.example.roundtimer.domain.WorkoutInput
import com.example.roundtimer.domain.room_domain.WorkoutRoomDao
import kotlinx.coroutines.launch

class WorkoutRoomViewModel(private val workoutDao: WorkoutRoomDao) : ViewModel() {

    val allWorkouts: LiveData<List<WorkoutRoomEntity>> = workoutDao.getAllRoomWorkouts().asLiveData()

    fun addRoomWorkout(workoutInput: WorkoutInput) {
        val workoutEntity = workoutInput.toEntity()
        viewModelScope.launch {
            workoutDao.insertRoomWorkout(workoutEntity)
        }
    }

    fun updateRoomWorkout(workout: WorkoutRoomEntity) {
        viewModelScope.launch {
            workoutDao.updateRoomWorkout(workout)
        }
    }

    fun deleteRoomWorkout(workout: WorkoutRoomEntity) {
        viewModelScope.launch {
            workoutDao.deleteRoomWorkout(workout)
        }
    }

    fun deleteAllRoomWorkouts() {
        viewModelScope.launch {
            workoutDao.deleteAllRoomWorkouts()
        }
    }
}
