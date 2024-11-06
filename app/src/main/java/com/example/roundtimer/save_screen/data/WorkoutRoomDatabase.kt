package com.example.roundtimer.save_screen.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.roundtimer.save_screen.domain.WorkoutRoomDao
import com.example.roundtimer.save_screen.domain.WorkoutRoomEntity

@Database(entities = [WorkoutRoomEntity::class], version = 1, exportSchema = false)
abstract class WorkoutRoomDatabase : RoomDatabase() {
    abstract fun workoutRoomDao(): WorkoutRoomDao
}