package com.example.roundtimer.presentation.round_screen

import androidx.lifecycle.ViewModel
import com.example.roundtimer.data.LocalQuotesDatabase
import com.example.roundtimer.domain.Quote
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class QuoteViewModel : ViewModel() {
    private val _currentQuote = MutableStateFlow<Quote?>(null)
    val currentQuote: StateFlow<Quote?> = _currentQuote

    init {
        fetchRandomQuote()
    }

    private fun fetchRandomQuote() {
        val quote = LocalQuotesDatabase.getRandomQuote()
        _currentQuote.value = quote
    }
}