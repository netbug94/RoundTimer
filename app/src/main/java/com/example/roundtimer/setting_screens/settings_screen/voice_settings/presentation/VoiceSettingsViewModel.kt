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

    val selectedVoiceOption: StateFlow<VoiceOption> = getApplication<Application>().voiceDataStore.data
        .map { preferences ->
            when (preferences[voiceOptionKey]) {
                VoiceOption.WOMAN_VOICE.name -> VoiceOption.WOMAN_VOICE
                VoiceOption.MAN_VOICE.name -> VoiceOption.MAN_VOICE
                VoiceOption.MUTE.name -> VoiceOption.MUTE

                else -> VoiceOption.WOMAN_VOICE
            }
        }
        .onEach {
            _isVoiceOptionLoaded.value = true
        }
        .stateIn(viewModelScope, SharingStarted.Eagerly, VoiceOption.WOMAN_VOICE)

    fun selectVoiceOption(option: VoiceOption) {
        viewModelScope.launch {
            getApplication<Application>().voiceDataStore.edit { preferences ->
                preferences[voiceOptionKey] = option.name
            }
        }
    }
}
