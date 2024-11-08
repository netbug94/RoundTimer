package com.example.roundtimer.setting_screens.settings_screen.voice_settings.data

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore

private const val VOICE_DATASTORE_NAME = "voice_settings"

val Context.voiceDataStore by preferencesDataStore(name = VOICE_DATASTORE_NAME)
