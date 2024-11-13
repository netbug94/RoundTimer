package com.netbug94.roundtimer.setting_screens.settings_screen.round_beep_settings.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.netbug94.roundtimer.R

@Composable
fun BeepOptions.getDisplayName(): String {
    return when (this) {
        BeepOptions.DEFAULT_BEEP -> stringResource(R.string.DefaultBeepString)
        BeepOptions.MUTE_BEEP -> stringResource(R.string.MuteString)
    }
}
