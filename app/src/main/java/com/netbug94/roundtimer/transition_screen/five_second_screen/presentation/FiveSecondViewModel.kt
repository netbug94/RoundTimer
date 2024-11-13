package com.netbug94.roundtimer.transition_screen.five_second_screen.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FiveSecondViewModel : ViewModel() {
    private val _secondsRemaining = MutableStateFlow(0)
    val secondsRemaining: StateFlow<Int> = _secondsRemaining.asStateFlow()

    private val _isCancelled = MutableStateFlow(false)

    private val _uiEvent = MutableSharedFlow<FiveSecondScreenEvent>()
    val uiEvent: SharedFlow<FiveSecondScreenEvent> = _uiEvent.asSharedFlow()

    fun startCountdown() {
        viewModelScope.launch {
            try {
                for (i in 5 downTo 1) {
                    if (_isCancelled.value) {
                        break
                    }
                    _secondsRemaining.value = i
                    delay(1000)
                }
                if (!_isCancelled.value) {
                    _uiEvent.emit(FiveSecondScreenEvent.Navigate)
                }
            } catch (_: CancellationException) {
                Log.d("FiveSecondViewModel", "Countdown was cancelled")
                _secondsRemaining.value = 0
            }
        }
    }

    fun cancelCountdown() {
        if (!_isCancelled.value) {
            _isCancelled.value = true
            viewModelScope.launch {
                _uiEvent.emit(FiveSecondScreenEvent.SwipeBack)
            }
        }
    }
}
