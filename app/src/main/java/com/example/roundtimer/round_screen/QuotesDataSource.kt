package com.example.roundtimer.round_screen

import com.example.roundtimer.quote.domain.Quote

interface QuotesDataSource {
    fun getRandomQuote(): Quote
}
