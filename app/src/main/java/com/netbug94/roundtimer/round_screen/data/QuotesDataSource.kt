package com.netbug94.roundtimer.round_screen.data

import com.netbug94.roundtimer.quote.domain.Quote

interface QuotesDataSource {
    fun getRandomQuote(): Quote
}
