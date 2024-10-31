package com.example.roundtimer.domain

import androidx.compose.runtime.State

data class FocusHandler(
    val anyFieldFocused: State<Boolean>,
    val focusChanged: (Boolean) -> Unit
)
