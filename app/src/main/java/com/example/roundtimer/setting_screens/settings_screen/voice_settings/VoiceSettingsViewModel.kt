package com.example.roundtimer.setting_screens.settings_screen.voice_settings

import android.app.Application
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.roundtimer.setting_screens.settings_screen.transition_settings_screen.dataStore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.onEach

class VoiceSettingsViewModel(application: Application) : AndroidViewModel(application) {
    private val voiceOptionKey = stringPreferencesKey("voice_option")

    private val _isVoiceOptionLoaded = MutableStateFlow(false)
    val isVoiceOptionLoaded: StateFlow<Boolean> = _isVoiceOptionLoaded.asStateFlow()

    val selectedVoiceOption: StateFlow<VoiceOption> = getApplication<Application>().dataStore.data
        .map { preferences ->
            when (preferences[voiceOptionKey]) {
                VoiceOption.MUTE.name -> VoiceOption.MUTE
                VoiceOption.GIRL_VOICE.name -> VoiceOption.GIRL_VOICE
                // Add more cases when you add more voices
                else -> VoiceOption.GIRL_VOICE // Default option
            }
        }
        .onEach {
            _isVoiceOptionLoaded.value = true // Indicate that settings are loaded
        }
        .stateIn(viewModelScope, SharingStarted.Eagerly, VoiceOption.GIRL_VOICE)

    fun selectVoiceOption(option: VoiceOption) {
        viewModelScope.launch {
            getApplication<Application>().dataStore.edit { preferences ->
                preferences[voiceOptionKey] = option.name
            }
        }
    }
}
