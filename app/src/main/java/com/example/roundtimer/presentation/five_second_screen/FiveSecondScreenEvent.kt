package com.example.roundtimer.presentation.five_second_screen

sealed class FiveSecondScreenEvent {
    data object Navigate : FiveSecondScreenEvent()
    data object SwipeBack : FiveSecondScreenEvent()
}