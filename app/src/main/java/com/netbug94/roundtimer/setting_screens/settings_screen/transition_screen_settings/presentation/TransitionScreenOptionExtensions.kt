package com.netbug94.roundtimer.setting_screens.settings_screen.transition_screen_settings.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.netbug94.roundtimer.R

@Composable
fun TransitionScreenOption.getDisplayName(): String {
    return when (this) {
        TransitionScreenOption.FIVE_SECONDS -> stringResource(R.string.TransitionOptionFiveSecondsString)
        TransitionScreenOption.THREE_SECONDS -> stringResource(R.string.TransitionOptionThreeSecondsString)
    }
}
