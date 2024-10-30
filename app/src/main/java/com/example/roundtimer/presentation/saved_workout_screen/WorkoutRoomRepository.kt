package com.example.roundtimer.presentation.saved_workout_screen

import com.example.roundtimer.domain.WorkoutInput
import com.example.roundtimer.domain.room_domain.WorkoutRoomEntity
import kotlinx.coroutines.flow.Flow

interface WorkoutRoomRepository {
    fun getAllWorkouts(): Flow<List<WorkoutRoomEntity>>
    suspend fun addWorkout(workoutInput: WorkoutInput)
    suspend fun updateWorkout(workout: WorkoutRoomEntity)
    suspend fun deleteWorkout(workout: WorkoutRoomEntity)
}
