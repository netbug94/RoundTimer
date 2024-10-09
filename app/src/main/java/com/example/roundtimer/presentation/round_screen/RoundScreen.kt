package com.example.roundtimer.presentation.round_screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.roundtimer.ui.theme.customColorScheme

@Composable
fun RoundScreen(onSwipeBack: () -> Unit) {
    val customColorText = customColorScheme().customBorderColor

    BackHandler {
        onSwipeBack()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Text("Round 1 - 5", fontSize = 50.sp, fontWeight = FontWeight.Bold, color = customColorText)
        }

        Spacer(modifier = Modifier.fillMaxWidth().padding(vertical = 20.dp, horizontal = 10.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Text("00:00", fontSize = 70.sp, fontWeight = FontWeight.Bold, color = customColorText)
        }

    }
}
