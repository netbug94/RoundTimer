package com.example.roundtimer.first_screen.domain

data class WorkoutParameters(
    val roundNumber: Int,
    val roundMinutes: Int,
    val roundSeconds: Int,
    val restMinutes: Int,
    val restSeconds: Int
)
