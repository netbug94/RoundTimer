package com.netbug94.roundtimer.first_screen.presentation.start_and_clear_buttons

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.netbug94.roundtimer.R
import com.netbug94.roundtimer.setting_screens.settings_screen.transition_screen_settings.presentation.TransitionScreenOption
import com.netbug94.roundtimer.setting_screens.settings_screen.transition_screen_settings.presentation.TransitionSettingsViewModel
import com.netbug94.roundtimer.ui.theme.customColorScheme

@Composable
fun StartAndClearButtons(
    onStartClickFive: () -> Unit,
    onStartClickThree: () -> Unit,
    onClearClick: () -> Unit
) {
    val settingsViewModel: TransitionSettingsViewModel = viewModel()
    val startButtonColor = MaterialTheme.colorScheme.primary
    val customBtnColor = customColorScheme()
    val clearButtonColor = customBtnColor.customButtonColor
    val buttonsStyling = MaterialTheme.typography.bodyLarge.copy(
        fontWeight = FontWeight.SemiBold
    )
    val focusManager = LocalFocusManager.current
    val clearString = stringResource(id = R.string.ClearString)
    val readyString = stringResource(id = R.string.ReadyString)

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
                when (settingsViewModel.selectedOption.value) {
                    TransitionScreenOption.FIVE_SECONDS -> {
                        onStartClickFive()
                    }
                    TransitionScreenOption.THREE_SECONDS -> {
                        onStartClickThree()
                    }
                }
            }
        ) {
            Text(text = readyString, style = buttonsStyling)
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
            Text(text = clearString, style = buttonsStyling)
        }
    }
}
