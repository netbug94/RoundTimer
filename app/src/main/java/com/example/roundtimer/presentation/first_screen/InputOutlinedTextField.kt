package com.example.roundtimer.presentation.first_screen

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
    isRoundNumberField: Boolean = false // New parameter to handle Round Number
) {
    var text by remember { mutableStateOf("") }

    // Synchronize text with value from ViewModel
    LaunchedEffect(value, isRoundNumberField) {
        val newText = when {
            isRoundNumberField && value == 1 -> "" // Display empty for value 1
            value == 0 -> "" // Display empty for value 0
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
            // Determine the integer value based on input
            val intValue = when {
                isRoundNumberField && newText == "" -> 1 // Default to 1 for empty input in Round Number field
                isRoundNumberField && newText == "1" -> 1 // Treat input '1' as default
                newText.isEmpty() -> 0 // Default to 0 for other fields when empty
                else -> newText.toIntOrNull() ?: 0 // Parse to Int, default to 0 if invalid
            }
            onValueChange(intValue)
        },
        label = { Text(label) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        singleLine = true
    )
}