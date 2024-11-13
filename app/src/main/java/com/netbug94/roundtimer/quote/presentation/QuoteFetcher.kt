package com.netbug94.roundtimer.quote.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.netbug94.roundtimer.ui.theme.customColorScheme

@Composable
fun QuoteFetcher() {
    val customColorText = customColorScheme().customBorderColor
    val quoteViewModel: QuoteViewModel = viewModel()
    val quote by quoteViewModel.currentQuote.collectAsState()

    quote?.let {
        Text(
            text = "\"${it.text}\"",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Medium,
            color = customColorText,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        if (it.author.isNotBlank()) {
            Text(
                text = "- ${it.author}",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = customColorText.copy(alpha = 0.7f),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}