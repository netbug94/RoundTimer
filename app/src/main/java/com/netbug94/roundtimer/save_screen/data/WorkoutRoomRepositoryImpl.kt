package com.netbug94.roundtimer.save_screen.data

import com.netbug94.roundtimer.first_screen.domain.WorkoutParameters
import com.netbug94.roundtimer.save_screen.domain.WorkoutRoomDao
import com.netbug94.roundtimer.save_screen.domain.WorkoutRoomEntity
import com.netbug94.roundtimer.save_screen.domain.WorkoutRoomRepository
import kotlinx.coroutines.flow.Flow

class WorkoutRoomRepositoryImpl(private val workoutDao: WorkoutRoomDao) : WorkoutRoomRepository {

    override fun getAllWorkouts(): Flow<List<WorkoutRoomEntity>> =
        workoutDao.getAllRoomWorkouts()

    override suspend fun addWorkout(workoutInput: WorkoutParameters, firstWordString: String) {
        val maxDisplayId = workoutDao.getMaxDisplayId() ?: 0
        val newDisplayId = maxDisplayId + 1

        val workoutEntity = workoutInput.toEntity(
            displayId = newDisplayId,
            firstWordString = firstWordString
        )

        workoutDao.insertRoomWorkout(workoutEntity)
    }

    override suspend fun updateWorkout(workout: WorkoutRoomEntity) {
        workoutDao.updateRoomWorkout(workout)
    }

    override suspend fun deleteWorkout(workout: WorkoutRoomEntity) {
        workoutDao.deleteRoomWorkout(workout)
    }
}
