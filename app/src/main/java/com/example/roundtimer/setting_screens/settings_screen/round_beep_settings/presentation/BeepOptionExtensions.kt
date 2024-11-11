package com.example.roundtimer.setting_screens.settings_screen.round_beep_settings.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.roundtimer.R

@Composable
fun BeepOption.getDisplayName(): String {
    return when (this) {
        BeepOption.DEFAULT_BEEP -> stringResource(R.string.DefaultBeepString)
        BeepOption.MUTE_BEEP -> stringResource(R.string.MuteString)
    }
}
