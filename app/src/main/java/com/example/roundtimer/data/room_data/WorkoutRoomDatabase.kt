package com.example.roundtimer.data.room_data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.roundtimer.domain.room_domain.WorkoutRoomDao
import com.example.roundtimer.domain.room_domain.WorkoutRoomEntity

@Database(entities = [WorkoutRoomEntity::class], version = 1, exportSchema = false)
abstract class WorkoutRoomDatabase : RoomDatabase() {
    abstract fun workoutRoomDao(): WorkoutRoomDao
}
