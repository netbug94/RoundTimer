package com.example.roundtimer.save_screen.presentation

import com.example.roundtimer.first_screen.domain.WorkoutInput
import com.example.roundtimer.save_screen.domain.WorkoutRoomEntity
import kotlinx.coroutines.flow.Flow

interface WorkoutRoomRepository {
    fun getAllWorkouts(): Flow<List<WorkoutRoomEntity>>
    suspend fun addWorkout(workoutInput: WorkoutInput)
    suspend fun updateWorkout(workout: WorkoutRoomEntity)
    suspend fun deleteWorkout(workout: WorkoutRoomEntity)
}
