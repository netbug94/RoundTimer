package com.example.roundtimer.setting_screens.settings_screen.transition_settings_screen

import androidx.compose.ui.text.intl.Locale

@Suppress("SpellCheckingInspection")
enum class TransitionScreenOption(val displayNameEn: String, val displayNameEs: String) {
    FIVE_SECONDS("Five Second Screen", "Pantalla de cinco segundos"),
    THREE_SECONDS("Three Second Screen", "Pantalla de tres segundos");

    fun getDisplayName(locale: Locale = Locale.current): String {
        return when (locale.language) {
            "es" -> displayNameEs
            else -> displayNameEn
        }
    }
}
