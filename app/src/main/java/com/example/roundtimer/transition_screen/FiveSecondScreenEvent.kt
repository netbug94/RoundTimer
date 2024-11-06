package com.example.roundtimer.transition_screen

sealed class FiveSecondScreenEvent {
    data object Navigate : FiveSecondScreenEvent()
    data object SwipeBack : FiveSecondScreenEvent()
}
