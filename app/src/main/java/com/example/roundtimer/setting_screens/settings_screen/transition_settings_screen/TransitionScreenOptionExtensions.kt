package com.example.roundtimer.setting_screens.settings_screen.transition_settings_screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.roundtimer.R

@Composable
fun TransitionScreenOption.getDisplayName(): String {
    return when (this) {
        TransitionScreenOption.FIVE_SECONDS -> stringResource(R.string.TransitionOptionFiveSeconds)
        TransitionScreenOption.THREE_SECONDS -> stringResource(R.string.TransitionOptionThreeSeconds)
    }
}
