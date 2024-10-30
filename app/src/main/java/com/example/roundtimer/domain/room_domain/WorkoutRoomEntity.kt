package com.example.roundtimer.domain.room_domain

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "workouts")
data class WorkoutRoomEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val displayId: Int,
    val name: String,
    val roundNumber: Int,
    val roundMinutes: Int,
    val roundSeconds: Int,
    val restMinutes: Int,
    val restSeconds: Int
)
