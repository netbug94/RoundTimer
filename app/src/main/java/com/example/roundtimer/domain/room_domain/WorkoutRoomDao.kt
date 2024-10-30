package com.example.roundtimer.domain.room_domain

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkoutRoomDao {
    @Query("SELECT * FROM workouts ORDER BY displayId ASC")
    fun getAllRoomWorkouts(): Flow<List<WorkoutRoomEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRoomWorkout(workout: WorkoutRoomEntity): Long

    @Update
    suspend fun updateRoomWorkout(workout: WorkoutRoomEntity)

    @Delete
    suspend fun deleteRoomWorkout(workout: WorkoutRoomEntity)

    @Query("SELECT MAX(displayId) FROM workouts")
    suspend fun getMaxDisplayId(): Int?
}