package com.example.roundtimer.presentation.first_screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.roundtimer.ui.theme.customColorScheme

@Composable
fun PreviewRoundBox() {
    val workoutInputVM: WorkoutInputViewModel = viewModel()
    val workoutInput by workoutInputVM.workoutInput
    val previewBoxColor = MaterialTheme.colorScheme.onSurface

    val customColors = customColorScheme()
    //val ifFocusActiveColor = if (isFocused) MaterialTheme.colorScheme.primary else customColors.customBorderColor
    //val ifFocusActiveBorder = if (isFocused) 3.dp else 1.dp

    Row(modifier = Modifier
        .fillMaxSize()
        .border(BorderStroke(1.dp, customColors.customBorderColor), RoundedCornerShape(6.dp)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center) {

        Column(modifier = Modifier
            .fillMaxSize()
            .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {

            Text(text = "Rounds")
            Text(text = workoutInput.roundNumber.toString(), style = MaterialTheme.typography.headlineLarge
            )
        }

        VerticalDivider(modifier = Modifier
            .fillMaxHeight()
            .width(1.dp),
            color = previewBoxColor
        )

        Column(modifier = Modifier
            .fillMaxSize()
            .weight(2f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {

            Row(verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center) {
                Text(text = "${workoutInput.roundMinutes.toString().padStart(2, '0')
                } : ${workoutInput.roundSeconds.toString().padStart(2, '0')}",
                    style = MaterialTheme.typography.headlineSmall
                )
            }

            HorizontalDivider(
                color = previewBoxColor
            )

            Row(verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center) {
                Text(text = "${
                    workoutInput.restMinutes.toString().padStart(2, '0')
                } : ${workoutInput.restSeconds.toString().padStart(2, '0')}",
                    style = MaterialTheme.typography.headlineSmall,
                    color = previewBoxColor
                )
            }
        }
        VerticalDivider(modifier = Modifier
            .fillMaxHeight()
            .width(1.dp),
            color = previewBoxColor
        )
        Column(modifier = Modifier
            .fillMaxSize()
            .weight(1f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            ClickablePlusSign()
        }
    }
}