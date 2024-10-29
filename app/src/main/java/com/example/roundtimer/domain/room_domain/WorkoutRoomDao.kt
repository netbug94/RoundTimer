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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRoomWorkout(workout: WorkoutRoomEntity)

    @Update
    suspend fun updateRoomWorkout(workout: WorkoutRoomEntity)

    @Delete
    suspend fun deleteRoomWorkout(workout: WorkoutRoomEntity)

    @Query("SELECT * FROM workout_room_table WHERE id = :id")
    suspend fun getRoomWorkoutById(id: Int): WorkoutRoomEntity?

    @Query("SELECT * FROM workout_room_table")
    fun getAllRoomWorkouts(): Flow<List<WorkoutRoomEntity>>

    @Query("DELETE FROM workout_room_table")
    suspend fun deleteAllRoomWorkouts()
}
