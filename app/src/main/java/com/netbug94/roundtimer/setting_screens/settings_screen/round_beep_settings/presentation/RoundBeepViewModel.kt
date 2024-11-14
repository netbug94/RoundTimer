package com.netbug94.roundtimer.setting_screens.settings_screen.round_beep_settings.presentation

import android.app.Application
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.netbug94.roundtimer.setting_screens.settings_screen.round_beep_settings.data.beepDataStore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class RoundBeepViewModel(application: Application) : AndroidViewModel(application) {
    private val beepOptionKey = stringPreferencesKey("beep_option")

    private val _isBeepOptionLoaded = MutableStateFlow(false)
    val isBeepOptionLoaded: StateFlow<Boolean> = _isBeepOptionLoaded.asStateFlow()

    val selectedBeepOption: StateFlow<BeepOptions> = getApplication<Application>().beepDataStore.data
        .map { preferences ->
            when (preferences[beepOptionKey]) {
                BeepOptions.DEFAULT_BEEP.name -> BeepOptions.DEFAULT_BEEP
                BeepOptions.MUTE_BEEP.name -> BeepOptions.MUTE_BEEP
                else -> BeepOptions.DEFAULT_BEEP
            }
        }
        .onEach { beepOption ->
            _isBeepOptionLoaded.value = (beepOption == BeepOptions.MUTE_BEEP)
        }
        .stateIn(viewModelScope, SharingStarted.Eagerly, BeepOptions.DEFAULT_BEEP)


    fun selectBeepOption(option: BeepOptions) {
        viewModelScope.launch {
            getApplication<Application>().beepDataStore.edit { preferences ->
                preferences[beepOptionKey] = option.name
            }
        }
    }
}
