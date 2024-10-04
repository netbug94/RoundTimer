package com.example.roundtimer.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp

@Composable
fun Herrete(wmk: String) {
    Box(modifier = Modifier.fillMaxSize()) {

        Text(text = wmk, modifier = Modifier
            .align(Alignment.BottomEnd)
            .padding(bottom = 10 .dp, end = 8.dp)
            .alpha(.1f),
        )
    }
    println("netBug94 -> https://github.com/netbug94")
}