package com.example.roundtimer.first_screen.domain

data class WorkoutInput(
    val roundNumber: Int,
    val roundMinutes: Int,
    val roundSeconds: Int,
    val restMinutes: Int,
    val restSeconds: Int
)
