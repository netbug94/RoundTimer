package com.example.roundtimer.presentation.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkoutDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWorkout(workout: WorkoutEntity)

    @Update
    suspend fun updateWorkout(workout: WorkoutEntity)

    @Delete
    suspend fun deleteWorkout(workout: WorkoutEntity)

    @Query("SELECT * FROM workout_table WHERE id = :id")
    suspend fun getWorkoutById(id: Int): WorkoutEntity?

    @Query("SELECT * FROM workout_table")
    fun getAllWorkouts(): Flow<List<WorkoutEntity>>

    @Query("DELETE FROM workout_table")
    suspend fun deleteAllWorkouts()
}
