package com.netbug94.roundtimer.transition_screen.three_second_screen

sealed class ThreeSecondScreenEvent {
    data object Navigate : ThreeSecondScreenEvent()
    data object SwipeBack : ThreeSecondScreenEvent()
}
