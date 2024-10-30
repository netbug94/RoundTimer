package com.example.roundtimer.presentation.saved_workout_screen

import com.example.roundtimer.domain.WorkoutInput
import com.example.roundtimer.domain.room_domain.WorkoutRoomDao
import com.example.roundtimer.domain.room_domain.WorkoutRoomEntity
import kotlinx.coroutines.flow.Flow

class WorkoutRoomRepositoryImpl(private val workoutDao: WorkoutRoomDao) : WorkoutRoomRepository {

    override fun getAllWorkouts(): Flow<List<WorkoutRoomEntity>> = workoutDao.getAllRoomWorkouts()

    override suspend fun addWorkout(workoutInput: WorkoutInput) {
        // Step 1: Retrieve the maximum existing displayId
        val maxDisplayId = workoutDao.getMaxDisplayId() ?: 0

        // Step 2: Assign the next displayId
        val newDisplayId = maxDisplayId + 1

        // Step 3: Create a new WorkoutRoomEntity with the assigned displayId and default name
        val workoutEntity = workoutInput.toEntity(displayId = newDisplayId)

        // Step 4: Insert the new workout into the database
        workoutDao.insertRoomWorkout(workoutEntity)
    }

    override suspend fun updateWorkout(workout: WorkoutRoomEntity) {
        workoutDao.updateRoomWorkout(workout)
    }

    override suspend fun deleteWorkout(workout: WorkoutRoomEntity) {
        workoutDao.deleteRoomWorkout(workout)
    }
}