package com.example.roundtimer.save_screen.domain

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "workouts")
data class WorkoutRoomEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val displayId: Int,
    val baseName: String,
    val roundNumber: Int,
    val roundMinutes: Int,
    val roundSeconds: Int,
    val restMinutes: Int,
    val restSeconds: Int
) : Parcelable
