package com.netbug94.roundtimer.focus_handler.domain

import androidx.compose.runtime.State

data class FocusHandler(
    val anyFieldFocused: State<Boolean>,
    val focusChanged: (Boolean) -> Unit
)
