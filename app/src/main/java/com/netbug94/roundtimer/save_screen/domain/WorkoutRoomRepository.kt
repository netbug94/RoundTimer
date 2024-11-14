package com.netbug94.roundtimer.save_screen.domain

import com.netbug94.roundtimer.first_screen.domain.WorkoutParameters
import kotlinx.coroutines.flow.Flow

interface WorkoutRoomRepository {
    fun getAllWorkouts(): Flow<List<WorkoutRoomEntity>>
    suspend fun addWorkout(workoutInput: WorkoutParameters, firstWordString: String)
    suspend fun updateWorkout(workout: WorkoutRoomEntity)
    suspend fun deleteWorkout(workout: WorkoutRoomEntity)
}