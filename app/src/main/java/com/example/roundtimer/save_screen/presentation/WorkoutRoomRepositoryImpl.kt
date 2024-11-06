package com.example.roundtimer.save_screen.presentation

import com.example.roundtimer.first_screen.domain.WorkoutInput
import com.example.roundtimer.save_screen.domain.WorkoutRoomDao
import com.example.roundtimer.save_screen.domain.WorkoutRoomEntity
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