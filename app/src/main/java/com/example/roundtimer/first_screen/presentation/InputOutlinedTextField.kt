package com.example.roundtimer.first_screen.presentation

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun InputOutlinedTextField(
    modifier: Modifier = Modifier,
    label: String,
    value: Int,
    onValueChange: (Int) -> Unit,
    onFocusChanged: (Boolean) -> Unit,
    isRoundNumberField: Boolean = false
) {
    var text by remember { mutableStateOf("") }

    LaunchedEffect(value, isRoundNumberField) {
        val newText = when {
            isRoundNumberField && value == 1 -> ""
            value == 0 -> ""
            else -> value.toString()
        }
        if (newText != text) {
            text = newText
        }
    }

    OutlinedTextField(
        modifier = modifier
            .onFocusChanged { focusState ->
                onFocusChanged(focusState.isFocused)
            },
        value = text,
        onValueChange = { newText ->
            text = newText

            val intValue = when {
                isRoundNumberField && newText == "" -> 1
                isRoundNumberField && newText == "1" -> 1
                newText.isEmpty() -> 0
                else -> newText.toIntOrNull() ?: 0
            }
            onValueChange(intValue)
        },
        label = { Text(label) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        singleLine = true
    )
}