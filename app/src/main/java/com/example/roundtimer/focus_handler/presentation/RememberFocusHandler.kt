package com.example.roundtimer.focus_handler.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.roundtimer.focus_handler.domain.FocusHandler

@Composable
fun rememberFocusHandler(): FocusHandler {
    val anyFieldFocused = remember { mutableStateOf(false) }
    var focusedFieldCount by remember { mutableIntStateOf(0) }
    val focusChanged: (Boolean) -> Unit = { focused ->
        focusedFieldCount = (focusedFieldCount + if (focused) 1 else -1).coerceAtLeast(0)
        anyFieldFocused.value = focusedFieldCount > 0
    }

    return FocusHandler(anyFieldFocused, focusChanged)
}
