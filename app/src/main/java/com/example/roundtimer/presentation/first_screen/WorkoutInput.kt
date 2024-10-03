package com.example.roundtimer.presentation.first_screen

data class WorkoutInput(
    val roundNumber: Int? = null,
    val roundMinutes: Int? = null,
    val roundSeconds: Int? = null,
    val restMinutes: Int? = null,
    val restSeconds: Int? = null
)
