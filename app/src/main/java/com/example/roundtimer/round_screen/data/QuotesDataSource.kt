package com.example.roundtimer.round_screen.data

import com.example.roundtimer.quote.domain.Quote

interface QuotesDataSource {
    fun getRandomQuote(): Quote
}
