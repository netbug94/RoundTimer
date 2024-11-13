package com.netbug94.roundtimer.save_screen.data

import com.netbug94.roundtimer.first_screen.domain.WorkoutParameters
import com.netbug94.roundtimer.save_screen.domain.WorkoutRoomEntity

fun WorkoutParameters.toEntity(
    displayId: Int,
    firstWordString: String
): WorkoutRoomEntity {
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

fun WorkoutRoomEntity.toWorkoutInput() = WorkoutParameters(
    roundNumber = this.roundNumber,
    roundMinutes = this.roundMinutes,
    roundSeconds = this.roundSeconds,
    restMinutes = this.restMinutes,
    restSeconds = this.restSeconds
)
