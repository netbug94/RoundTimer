package com.example.roundtimer.setting_screens.settings_screen.voice_settings.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.roundtimer.R

@Composable
fun VoiceOptions.getDisplayName(): String {
    return when (this) {
        VoiceOptions.WOMAN_VOICE -> stringResource(R.string.WomanVoiceString)
        VoiceOptions.MAN_VOICE -> stringResource(R.string.ManVoiceString)
        VoiceOptions.MUTE -> stringResource(R.string.MuteString)
    }
}
