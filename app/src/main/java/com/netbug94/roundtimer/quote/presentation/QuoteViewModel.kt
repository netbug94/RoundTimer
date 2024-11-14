package com.netbug94.roundtimer.quote.presentation

import androidx.compose.ui.text.intl.Locale
import androidx.lifecycle.ViewModel
import com.netbug94.roundtimer.quote.data.LocalQuotesDataBase
import com.netbug94.roundtimer.quote.data.LocalQuotesDataBaseES
import com.netbug94.roundtimer.quote.domain.Quote
import com.netbug94.roundtimer.round_screen.data.QuotesDataSource
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