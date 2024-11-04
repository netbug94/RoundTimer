package com.example.roundtimer.presentation.transition_screen

sealed class FiveSecondScreenEvent {
    data object Navigate : FiveSecondScreenEvent()
    data object SwipeBack : FiveSecondScreenEvent()
}
