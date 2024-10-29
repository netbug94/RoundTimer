package com.example.roundtimer.presentation.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [WorkoutEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    // Provide access to the DAO
    abstract fun workoutDao(): WorkoutDao
}
