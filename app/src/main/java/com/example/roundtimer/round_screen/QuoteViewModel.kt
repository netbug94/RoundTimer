package com.example.roundtimer.round_screen

import androidx.compose.ui.text.intl.Locale
import androidx.lifecycle.ViewModel
import com.example.roundtimer.quote.data.LocalQuotesDataBase
import com.example.roundtimer.quote.data.LocalQuotesDataBaseES
import com.example.roundtimer.quote.domain.Quote
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class QuoteViewModel : ViewModel() {
    private val _currentQuote = MutableStateFlow<Quote?>(null)
    val currentQuote: StateFlow<Quote?> = _currentQuote

    private val database: QuotesDataSource = if (Locale.current.language == "es") {
        LocalQuotesDataBaseES
    } else {
        LocalQuotesDataBase
    }

    init {
        fetchRandomQuote()
    }

    private fun fetchRandomQuote() {
        val quote = database.getRandomQuote()
        _currentQuote.value = quote
    }
}