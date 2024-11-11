package com.example.roundtimer.setting_screens.settings_screen.round_beep_settings.data

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore

private const val BEEP_DATASTORE_NAME = "beep_settings"

val Context.beepDataStore by preferencesDataStore(name = BEEP_DATASTORE_NAME)
