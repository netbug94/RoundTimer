package com.example.roundtimer.setting_screens.settings_screen.voice_settings.presentation

import android.app.Application
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.roundtimer.setting_screens.settings_screen.voice_settings.data.voiceDataStore
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

    val selectedVoiceOption: StateFlow<VoiceOptions> = getApplication<Application>().voiceDataStore.data
        .map { preferences ->
            when (preferences[voiceOptionKey]) {
                VoiceOptions.WOMAN_VOICE.name -> VoiceOptions.WOMAN_VOICE
                VoiceOptions.MAN_VOICE.name -> VoiceOptions.MAN_VOICE
                VoiceOptions.MUTE.name -> VoiceOptions.MUTE

                else -> VoiceOptions.WOMAN_VOICE
            }
        }
        .onEach {
            _isVoiceOptionLoaded.value = true
        }
        .stateIn(viewModelScope, SharingStarted.Eagerly, VoiceOptions.WOMAN_VOICE)

    fun selectVoiceOption(option: VoiceOptions) {
        viewModelScope.launch {
            getApplication<Application>().voiceDataStore.edit { preferences ->
                preferences[voiceOptionKey] = option.name
            }
        }
    }
}
