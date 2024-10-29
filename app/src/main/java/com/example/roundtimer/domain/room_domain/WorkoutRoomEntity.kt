package com.example.roundtimer.domain.room_domain

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "workout_room_table")
data class WorkoutRoomEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val roundNumber: Int,
    val roundMinutes: Int,
    val roundSeconds: Int,
    val restMinutes: Int,
    val restSeconds: Int
)
