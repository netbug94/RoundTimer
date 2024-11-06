package com.example.roundtimer.setting_screens.settings_screen.transition_settings_screen

import android.app.Application
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

    fun selectOption(option: TransitionScreenOption) {
        viewModelScope.launch {
            getApplication<Application>().dataStore.edit { preferences ->
                preferences[screenOptionKey] = option.name
            }
        }
    }
}