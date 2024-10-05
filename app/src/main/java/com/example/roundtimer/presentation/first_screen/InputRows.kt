package com.example.roundtimer.presentation.first_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun InputSingleField(
    value: Int,
    onValueChange: (Int) -> Unit,
    label: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        InputOutlinedTextField(
            label = label,
            value = value,
            onValueChange = onValueChange
        )
    }
}

@Composable
fun InputDoubleFieldRow(
    firstValue: Int,
    onFirstValueChange: (Int) -> Unit,
    firstLabel: String,
    secondValue: Int,
    onSecondValueChange: (Int) -> Unit,
    secondLabel: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        InputOutlinedTextField(
            modifier = Modifier.weight(1f),
            label = firstLabel,
            value = firstValue,
            onValueChange = onFirstValueChange
        )

        Spacer(Modifier.weight(0.01f))

        InputOutlinedTextField(
            modifier = Modifier.weight(1f),
            label = secondLabel,
            value = secondValue,
            onValueChange = onSecondValueChange
        )
    }
}
