package com.example.roundtimer.presentation.first_screen.preview_round_box

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FocusViewModel : ViewModel() {
    private val _isFocused = MutableStateFlow(false)
    val isFocused: StateFlow<Boolean> = _isFocused

    fun onFocusGained() {
        if (!_isFocused.value) {
            _isFocused.value = true
        }
    }

    fun onFocusLost() {
        if (_isFocused.value) {
            _isFocused.value = false
        }
    }
}
