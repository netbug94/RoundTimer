package com.example.roundtimer.data

import com.example.roundtimer.domain.PhrasesDataClass

object LocalPhrasesDatabase {
    private val phrases: List<PhrasesDataClass> = listOf(
        PhrasesDataClass(1, "The journey of a thousand miles begins with one step."),
        PhrasesDataClass(2, "Success is not final, failure is not fatal: it is the courage to continue that counts."),
        PhrasesDataClass(3, "Believe you can and you're halfway there."),
        PhrasesDataClass(4, "Act as if what you do makes a difference. It does."),
        PhrasesDataClass(5, "What lies behind us and what lies before us are tiny matters compared to what lies within us.")
    )

    fun getRandomPhrase(): PhrasesDataClass {
        return phrases.random()
    }
}