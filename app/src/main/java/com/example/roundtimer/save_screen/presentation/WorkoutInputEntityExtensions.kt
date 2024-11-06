package com.example.roundtimer.save_screen.presentation

import com.example.roundtimer.first_screen.domain.WorkoutInput
import com.example.roundtimer.save_screen.domain.WorkoutRoomEntity

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
