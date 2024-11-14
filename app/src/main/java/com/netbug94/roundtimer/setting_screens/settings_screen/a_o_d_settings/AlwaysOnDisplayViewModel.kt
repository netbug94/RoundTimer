package com.netbug94.roundtimer.setting_screens.settings_screen.a_o_d_settings

import android.app.Application
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.netbug94.roundtimer.setting_screens.settings_screen.voice_settings.data.voiceDataStore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class AlwaysOnDisplayViewModel(application: Application) : AndroidViewModel(application) {
    private val alwaysOnDisplayKey = stringPreferencesKey("aod_option")

    private val _isAlwaysOnDisplayOptionLoaded = MutableStateFlow(false)
    //val isAlwaysOnDisplayOptionLoaded: StateFlow<Boolean> = _isAlwaysOnDisplayOptionLoaded.asStateFlow()

    val selectedAlwaysOnDisplayOption: StateFlow<AlwaysOnDisplayOptions> = getApplication<Application>().voiceDataStore.data
        .map { preferences ->
            when (preferences[alwaysOnDisplayKey]) {
                AlwaysOnDisplayOptions.AOD.name -> AlwaysOnDisplayOptions.AOD
                AlwaysOnDisplayOptions.DISABLE_AOD.name -> AlwaysOnDisplayOptions.DISABLE_AOD

                else -> AlwaysOnDisplayOptions.AOD
            }
        }
        .onEach {
            _isAlwaysOnDisplayOptionLoaded.value = true
        }
        .stateIn(viewModelScope, SharingStarted.Eagerly, AlwaysOnDisplayOptions.AOD)

    fun selectAlwaysOnDisplayOption(option: AlwaysOnDisplayOptions) {
        viewModelScope.launch {
            getApplication<Application>().voiceDataStore.edit { preferences ->
                preferences[alwaysOnDisplayKey] = option.name
            }
        }
    }
}
