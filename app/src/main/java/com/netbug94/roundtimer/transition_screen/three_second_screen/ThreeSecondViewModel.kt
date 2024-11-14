package com.netbug94.roundtimer.transition_screen.three_second_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ThreeSecondViewModel : ViewModel() {
    private val _secondsRemaining = MutableStateFlow(0)
    val secondsRemaining: StateFlow<Int> = _secondsRemaining.asStateFlow()

    private val _isCancelled = MutableStateFlow(false)

    private val _uiEvent = MutableSharedFlow<ThreeSecondScreenEvent>()
    val uiEvent: SharedFlow<ThreeSecondScreenEvent> = _uiEvent.asSharedFlow()

    fun startCountdown() {
        viewModelScope.launch {
            try {
                for (i in 3 downTo 1) {
                    if (_isCancelled.value) {
                        break
                    }
                    _secondsRemaining.value = i
                    delay(1000)
                }
                if (!_isCancelled.value) {
                    _uiEvent.emit(ThreeSecondScreenEvent.Navigate)
                }
            } catch (_: CancellationException) {
                Log.d("ThreeSecondViewModel", "Countdown was cancelled")
                _secondsRemaining.value = 0
            }
        }
    }

    fun cancelCountdown() {
        if (!_isCancelled.value) {
            _isCancelled.value = true
            viewModelScope.launch {
                _uiEvent.emit(ThreeSecondScreenEvent.SwipeBack)
            }
        }
    }
}
