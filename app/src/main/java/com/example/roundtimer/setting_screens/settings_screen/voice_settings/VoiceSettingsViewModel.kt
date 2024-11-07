package com.example.roundtimer.setting_screens.settings_screen.voice_settings

import android.app.Application
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
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

    val selectedVoiceOption: StateFlow<VoiceOption> = getApplication<Application>().voiceDataStore.data
        .map { preferences ->
            when (preferences[voiceOptionKey]) {
                VoiceOption.MUTE.name -> VoiceOption.MUTE
                VoiceOption.WOMEN_VOICE.name -> VoiceOption.WOMEN_VOICE

                else -> VoiceOption.WOMEN_VOICE
            }
        }
        .onEach {
            _isVoiceOptionLoaded.value = true
        }
        .stateIn(viewModelScope, SharingStarted.Eagerly, VoiceOption.WOMEN_VOICE)

    fun selectVoiceOption(option: VoiceOption) {
        viewModelScope.launch {
            getApplication<Application>().voiceDataStore.edit { preferences ->
                preferences[voiceOptionKey] = option.name
            }
        }
    }
}