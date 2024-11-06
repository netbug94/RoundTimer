package com.example.roundtimer.setting_screens.settings_screen.transition_settings_screen

import android.app.Application
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TransitionSettingsViewModel(application: Application) : AndroidViewModel(application) {
    private val screenOptionKey = stringPreferencesKey("transition_screen_option")
    val selectedOption: StateFlow<TransitionScreenOption> =
        getApplication<Application>().dataStore.data
            .map { preferences ->
                when (preferences[screenOptionKey]) {
                    TransitionScreenOption.THREE_SECONDS.name -> TransitionScreenOption.THREE_SECONDS
                    else -> TransitionScreenOption.FIVE_SECONDS
                }
            }
            .stateIn(viewModelScope, SharingStarted.Eagerly, TransitionScreenOption.FIVE_SECONDS)

    private val soundEnabledKey = booleanPreferencesKey("sound_enabled")

    // Make isSoundEnabled non-nullable and initialize with a default value (true)
    val isSoundEnabled: StateFlow<Boolean> = getApplication<Application>().dataStore.data
        .map { preferences ->
            preferences[soundEnabledKey] != false // Default to true if not set
        }
        .stateIn(viewModelScope, SharingStarted.Eagerly, true)

    // Function to update the sound setting
    fun setSoundEnabled(enabled: Boolean) {
        viewModelScope.launch {
            getApplication<Application>().dataStore.edit { preferences ->
                preferences[soundEnabledKey] = enabled
            }
        }
    }

    fun selectOption(option: TransitionScreenOption) {
        viewModelScope.launch {
            getApplication<Application>().dataStore.edit { preferences ->
                preferences[screenOptionKey] = option.name
            }
        }
    }
}