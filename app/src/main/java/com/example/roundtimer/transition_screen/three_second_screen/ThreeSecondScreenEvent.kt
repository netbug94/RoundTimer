package com.example.roundtimer.presentation.transition_screen.three_second_screen

sealed class ThreeSecondScreenEvent {
    data object Navigate : ThreeSecondScreenEvent()
    data object SwipeBack : ThreeSecondScreenEvent()
}
