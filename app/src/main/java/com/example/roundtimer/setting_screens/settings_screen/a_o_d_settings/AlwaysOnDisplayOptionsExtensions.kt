package com.example.roundtimer.setting_screens.settings_screen.a_o_d_settings

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.roundtimer.R

@Composable
fun AlwaysOnDisplayOptions.getDisplayName(): String {
    return when (this) {
        AlwaysOnDisplayOptions.AOD -> stringResource(R.string.AODEnable)
        AlwaysOnDisplayOptions.DISABLE_AOD -> stringResource(R.string.AODDisable)
    }
}
