package com.example.roundtimer.data.room_data

import android.content.Context
import androidx.room.Room

object RoomDatabaseProvider {
    @Volatile
    private var INSTANCE: WorkoutRoomDatabase? = null

    fun getRoomDatabase(context: Context): WorkoutRoomDatabase {
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                WorkoutRoomDatabase::class.java,
                "workout_room_database"
            )
                .fallbackToDestructiveMigration()
                .build()
            INSTANCE = instance
            instance
        }
    }
}
