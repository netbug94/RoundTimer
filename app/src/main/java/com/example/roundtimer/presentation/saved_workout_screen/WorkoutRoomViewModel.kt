package com.example.roundtimer.presentation.saved_workout_screen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.roundtimer.domain.WorkoutInput
import com.example.roundtimer.domain.room_domain.WorkoutRoomEntity
import kotlinx.coroutines.launch

class WorkoutRoomViewModel(private val repository: WorkoutRoomRepository) : ViewModel() {
    val allWorkouts: LiveData<List<WorkoutRoomEntity>> = repository.getAllWorkouts().asLiveData()

    private val _workoutToDelete = MutableLiveData<WorkoutRoomEntity?>(null)
    val workoutToDelete: LiveData<WorkoutRoomEntity?> = _workoutToDelete

    fun showDeleteConfirmation(workout: WorkoutRoomEntity) {
        _workoutToDelete.value = workout
    }

    fun clearDeleteConfirmation() {
        _workoutToDelete.value = null
    }

    fun addRoomWorkout(workoutInput: WorkoutInput) {
        viewModelScope.launch {
            try {
                repository.addWorkout(workoutInput)
            } catch (e: Exception) {
                Log.e("WorkoutRoomViewModel", "Error adding workout", e)
            }
        }
    }

    fun updateRoomWorkout(workout: WorkoutRoomEntity) {
        viewModelScope.launch {
            repository.updateWorkout(workout)
        }
    }

    fun deleteRoomWorkout(workout: WorkoutRoomEntity) {
        viewModelScope.launch {
            repository.deleteWorkout(workout)
        }
    }

    fun updateRoomWorkoutName(updatedWorkout: WorkoutRoomEntity) {
        viewModelScope.launch {
            try {
                repository.updateWorkout(updatedWorkout)
            } catch (e: Exception) {
                Log.e("WorkoutRoomViewModel", "Error updating workout name", e)
            }
        }
    }
}
