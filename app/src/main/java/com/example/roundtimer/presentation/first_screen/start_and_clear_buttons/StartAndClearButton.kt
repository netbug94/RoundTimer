package com.example.roundtimer.presentation.first_screen.start_and_clear_buttons

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.roundtimer.ui.theme.customColorScheme

@Composable
fun StartAndClearButton(
    onStartClick: () -> Unit,
    onClearClick: () -> Unit
) {
    val startButtonColor = MaterialTheme.colorScheme.primary
    val customBtnColor = customColorScheme()
    val clearButtonColor = customBtnColor.customButtonColor

    val buttonsStyling = MaterialTheme.typography.bodyLarge.copy(
        fontWeight = FontWeight.SemiBold
    )

    val focusManager = LocalFocusManager.current

    Row(
        Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            colors = ButtonDefaults.buttonColors(startButtonColor),
            onClick = {
                onStartClick()
            }
        ) {
            Text(text = "Ready", style = buttonsStyling)
        }

        Spacer(Modifier.width(15.dp))

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            colors = ButtonDefaults.buttonColors(clearButtonColor),
            onClick = {
                onClearClick()
                focusManager.clearFocus()
            }
        ) {
            Text(text = "Clear", style = buttonsStyling)
        }
    }
}