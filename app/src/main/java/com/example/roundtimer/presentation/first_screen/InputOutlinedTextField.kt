package com.example.roundtimer.presentation.first_screen

import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged

@Composable
fun InputOutlinedTextField(
    modifier: Modifier = Modifier,
    label: String,
    value: Int,
    onValueChange: (Int) -> Unit,
    onFocusChanged: (Boolean) -> Unit
) {
    var text by remember(value) {
        mutableStateOf(if (value == 0) "" else value.toString())
    }
    OutlinedTextField(
        modifier = modifier
            .onFocusChanged { focusState ->
                onFocusChanged(focusState.isFocused)
            },
        value = text,
        onValueChange = { newText ->
            text = newText
            onValueChange(newText.toIntOrNull() ?: 0)
        },
        label = { Text(label) }
    )
}
