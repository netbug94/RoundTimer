package com.example.roundtimer.presentation.transition_screen.three_second_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException

class ThreeSecondViewModel : ViewModel() {
    private val _secondsRemaining = MutableStateFlow(3)
    val secondsRemaining: StateFlow<Int> = _secondsRemaining.asStateFlow()

    private val _isCancelled = MutableStateFlow(false)

    private val _uiEvent = MutableSharedFlow<ThreeSecondScreenEvent>()
    val uiEvent: SharedFlow<ThreeSecondScreenEvent> = _uiEvent.asSharedFlow()

    init {
        startCountdown()
    }

    private fun startCountdown() {
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
            } catch (e: CancellationException) {
                Log.d("Countdown", "Countdown was cancelled")
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