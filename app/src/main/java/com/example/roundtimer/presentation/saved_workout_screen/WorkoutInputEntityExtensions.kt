package com.example.roundtimer.presentation.saved_workout_screen

import com.example.roundtimer.domain.WorkoutInput
import com.example.roundtimer.domain.room_domain.WorkoutRoomEntity

fun WorkoutInput.toEntity() = WorkoutRoomEntity(
    roundNumber = this.roundNumber,
    roundMinutes = this.roundMinutes,
    roundSeconds = this.roundSeconds,
    restMinutes = this.restMinutes,
    restSeconds = this.restSeconds
)

/*
fun WorkoutEntity.toWorkoutInput() = WorkoutInput(
    roundNumber = this.roundNumber,
    roundMinutes = this.roundMinutes,
    roundSeconds = this.roundSeconds,
    restMinutes = this.restMinutes,
    restSeconds = this.restSeconds
)
*/