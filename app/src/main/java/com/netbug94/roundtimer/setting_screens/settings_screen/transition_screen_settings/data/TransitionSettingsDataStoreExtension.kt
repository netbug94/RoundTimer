package com.netbug94.roundtimer.setting_screens.settings_screen.transition_screen_settings.data

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore

private const val DATASTORE_NAME = "transition_settings"

val Context.dataStore by preferencesDataStore(name = DATASTORE_NAME)
