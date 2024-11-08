package com.example.roundtimer.save_screen.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.roundtimer.first_screen.domain.WorkoutParameters
import com.example.roundtimer.save_screen.domain.WorkoutRoomEntity
import com.example.roundtimer.save_screen.domain.WorkoutRoomRepository
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

    fun addRoomWorkout(workoutInput: WorkoutParameters, firstWordString: String) {
        viewModelScope.launch {
            try {
                repository.addWorkout(workoutInput, firstWordString)
            } catch (e: Exception) {
                Log.e("WorkoutRoomViewModel", "Error adding workout", e)
            }
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
