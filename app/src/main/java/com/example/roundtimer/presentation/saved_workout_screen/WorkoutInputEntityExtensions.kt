package com.example.roundtimer.presentation.saved_workout_screen

import androidx.compose.ui.text.intl.Locale
import com.example.roundtimer.domain.WorkoutInput
import com.example.roundtimer.domain.room_domain.WorkoutRoomEntity

fun WorkoutInput.toEntity(displayId: Int): WorkoutRoomEntity {
    val language = Locale.current.language
    @Suppress("SpellCheckingInspection")
    val firstWordString = if (language == "es") "Entidad" else "Entity"

    return WorkoutRoomEntity(
        displayId = displayId,
        name = "$firstWordString $displayId",
        roundNumber = this.roundNumber,
        roundMinutes = this.roundMinutes,
        roundSeconds = this.roundSeconds,
        restMinutes = this.restMinutes,
        restSeconds = this.restSeconds
    )
}

/*
fun WorkoutEntity.toWorkoutInput() = WorkoutInput(
    roundNumber = this.roundNumber,
    roundMinutes = this.roundMinutes,
    roundSeconds = this.roundSeconds,
    restMinutes = this.restMinutes,
    restSeconds = this.restSeconds
)
*/
