package com.example.roundtimer.presentation.round_screen

import com.example.roundtimer.domain.Quote

interface QuotesDataSource {
    fun getRandomQuote(): Quote
}
