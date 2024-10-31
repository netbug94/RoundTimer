package com.example.roundtimer.presentation.saved_workout_screen

import com.example.roundtimer.domain.WorkoutInput
import com.example.roundtimer.domain.room_domain.WorkoutRoomDao
import com.example.roundtimer.domain.room_domain.WorkoutRoomEntity
import kotlinx.coroutines.flow.Flow

class WorkoutRoomRepositoryImpl(private val workoutDao: WorkoutRoomDao) : WorkoutRoomRepository {

    override fun getAllWorkouts(): Flow<List<WorkoutRoomEntity>> = workoutDao.getAllRoomWorkouts()

    override suspend fun addWorkout(workoutInput: WorkoutInput) {
        val maxDisplayId = workoutDao.getMaxDisplayId() ?: 0
        val newDisplayId = maxDisplayId + 1

        val workoutEntity = workoutInput.toEntity(displayId = newDisplayId)

        workoutDao.insertRoomWorkout(workoutEntity)
    }

    override suspend fun updateWorkout(workout: WorkoutRoomEntity) {
        workoutDao.updateRoomWorkout(workout)
    }

    override suspend fun deleteWorkout(workout: WorkoutRoomEntity) {
        workoutDao.deleteRoomWorkout(workout)
    }
}