package com.netbug94.roundtimer.setting_screens.settings_screen.a_o_d_settings

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.netbug94.roundtimer.R

@Composable
fun AlwaysOnDisplayOptions.getDisplayName(): String {
    return when (this) {
        AlwaysOnDisplayOptions.AOD -> stringResource(R.string.AODEnableString)
        AlwaysOnDisplayOptions.DISABLE_AOD -> stringResource(R.string.AODDisableString)
    }
}
