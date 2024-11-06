package com.example.roundtimer.save_screen.presentation

import androidx.compose.ui.text.intl.Locale

class LocaleHelper(private val locale: Locale = Locale.current) {
    fun getEntityFirstWord(): String {
        @Suppress("SpellCheckingInspection")
        return if (locale.language == "es") "Entidad" else "Entity"
    }
}
