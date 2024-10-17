package com.example.roundtimer.domain

data class WorkoutInput(
    val roundNumber: Int,
    val roundMinutes: Int,
    val roundSeconds: Int,
    val restMinutes: Int,
    val restSeconds: Int
)
