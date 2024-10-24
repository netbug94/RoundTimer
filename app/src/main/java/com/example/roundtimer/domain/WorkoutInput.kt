package com.example.roundtimer.domain

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "workout_inputs")
data class WorkoutInput(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val roundNumber: Int,
    val roundMinutes: Int,
    val roundSeconds: Int,
    val restMinutes: Int,
    val restSeconds: Int
)
