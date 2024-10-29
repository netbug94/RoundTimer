package com.example.roundtimer.presentation.room

import com.example.roundtimer.domain.WorkoutInput

fun WorkoutInput.toEntity() = WorkoutEntity(
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