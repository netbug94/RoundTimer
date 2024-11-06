package com.example.roundtimer.transition_screen.five_second_screen

sealed class FiveSecondScreenEvent {
    data object Navigate : FiveSecondScreenEvent()
    data object SwipeBack : FiveSecondScreenEvent()
}
