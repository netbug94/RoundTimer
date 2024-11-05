package com.example.roundtimer.presentation.saved_workout_screen

import com.example.roundtimer.domain.WorkoutInput
import com.example.roundtimer.domain.room_domain.WorkoutRoomEntity

fun WorkoutInput.toEntity(displayId: Int, localeHelper: LocaleHelper = LocaleHelper()): WorkoutRoomEntity {
    val firstWordString = localeHelper.getEntityFirstWord()

    return WorkoutRoomEntity(
        displayId = displayId,
        baseName = "$firstWordString $displayId",
        roundNumber = this.roundNumber,
        roundMinutes = this.roundMinutes,
        roundSeconds = this.roundSeconds,
        restMinutes = this.restMinutes,
        restSeconds = this.restSeconds
    )
}

fun WorkoutRoomEntity.toWorkoutInput() = WorkoutInput(
    roundNumber = this.roundNumber,
    roundMinutes = this.roundMinutes,
    roundSeconds = this.roundSeconds,
    restMinutes = this.restMinutes,
    restSeconds = this.restSeconds
)
