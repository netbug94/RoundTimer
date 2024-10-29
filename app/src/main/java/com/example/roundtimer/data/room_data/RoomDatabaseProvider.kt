package com.example.roundtimer.data.room_data

import android.content.Context
import androidx.room.Room

object RoomDatabaseProvider {
    @Volatile
    private var INSTANCE: RoomAppDatabase? = null

    fun getRoomDatabase(context: Context): RoomAppDatabase {
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                RoomAppDatabase::class.java,
                "workout_room_database"
            ).build()
            INSTANCE = instance
            instance
        }
    }
}
