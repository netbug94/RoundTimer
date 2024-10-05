package com.example.roundtimer.presentation.first_screen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.input.KeyboardType
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.roundtimer.presentation.first_screen.preview_round_box.FocusViewModel

@Composable
fun InputOutlinedTextField(
    label: String,
    value: Int?,
    onValueChange: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    /*
    val unFocusedColorTextField = MaterialTheme.colorScheme.onSurface
    val focusedColorTextField = MaterialTheme.colorScheme.onSurface
    */

    val focusViewModel: FocusViewModel = viewModel()

    OutlinedTextField(
        value = if (value == 0) "" else value.toString(),
        onValueChange = { newValue ->
            val intValue = newValue.toIntOrNull() ?: 0
            onValueChange(intValue)
        },
        label = { Text(label) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        singleLine = true,
        modifier = modifier.fillMaxWidth()
            .onFocusChanged { focusState ->
                if (focusState.isFocused) {
                    focusViewModel.onFocusGained()
                } else {
                    focusViewModel.onFocusLost()
                }
            },
        /*
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = unFocusedColorTextField,
            focusedBorderColor = focusedColorTextField,
            unfocusedTextColor = unFocusedColorTextField,
            focusedTextColor = focusedColorTextField,
            unfocusedLabelColor = unFocusedColorTextField,
            focusedLabelColor = focusedColorTextField,
            cursorColor = unFocusedColorTextField
        ),
         */
    )
}
