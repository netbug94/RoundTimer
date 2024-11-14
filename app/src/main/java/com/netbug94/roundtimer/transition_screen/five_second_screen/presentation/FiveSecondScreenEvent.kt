package com.netbug94.roundtimer.transition_screen.five_second_screen.presentation

sealed class FiveSecondScreenEvent {
    data object Navigate : FiveSecondScreenEvent()
    data object SwipeBack : FiveSecondScreenEvent()
}
