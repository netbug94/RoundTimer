package com.example.roundtimer.setting_screens.settings_screen.voice_settings.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.roundtimer.R

@Composable
fun VoiceOption.getDisplayName(): String {
    return when (this) {
        VoiceOption.WOMAN_VOICE -> stringResource(R.string.WomanVoiceString)
        VoiceOption.MAN_VOICE -> stringResource(R.string.ManVoiceString)
        VoiceOption.MUTE -> stringResource(R.string.MuteString)
    }
}
