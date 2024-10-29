package com.example.roundtimer.presentation.room

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.roundtimer.domain.WorkoutInput
import kotlinx.coroutines.launch

class RoomViewModel(private val workoutDao: WorkoutDao) : ViewModel() {

    val allWorkouts: LiveData<List<WorkoutEntity>> = workoutDao.getAllWorkouts().asLiveData()

    fun addWorkout(workoutInput: WorkoutInput) {
        val workoutEntity = workoutInput.toEntity()
        viewModelScope.launch {
            workoutDao.insertWorkout(workoutEntity)
        }
    }

    fun updateWorkout(workout: WorkoutEntity) {
        viewModelScope.launch {
            workoutDao.updateWorkout(workout)
        }
    }

    fun deleteWorkout(workout: WorkoutEntity) {
        viewModelScope.launch {
            workoutDao.deleteWorkout(workout)
        }
    }

    fun deleteAllWorkouts() {
        viewModelScope.launch {
            workoutDao.deleteAllWorkouts()
        }
    }
}
