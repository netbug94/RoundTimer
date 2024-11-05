package com.example.roundtimer.presentation.first_screen.preview_round_box

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.roundtimer.R

@Composable
fun PreviewRoundBox(
    roundNumber: Int,
    roundMinutes: Int,
    roundSeconds: Int,
    restMinutes: Int,
    restSeconds: Int,
    isFocused: Boolean,
    onBannerShow: () -> Unit
) {
    val previewBoxColor = MaterialTheme.colorScheme.onSurface
    val ifFocusActiveBorder = if (isFocused) 2.dp else 1.dp
    val ifFocusActiveColor = if (isFocused) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
    val roundsString = stringResource(id = R.string.Rounds)

    Row(
        modifier = Modifier
            .fillMaxSize()
            .border(BorderStroke(ifFocusActiveBorder, ifFocusActiveColor), RoundedCornerShape(6.dp)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = roundsString,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = roundNumber.toString(),
                style = MaterialTheme.typography.headlineLarge
            )
        }

        VerticalDivider(
            modifier = Modifier.fillMaxHeight(),
            color = ifFocusActiveColor,
            thickness = ifFocusActiveBorder
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .weight(2f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "${roundMinutes.toString().padStart(2, '0')} : ${roundSeconds.toString().padStart(2, '0')}",
                    style = MaterialTheme.typography.headlineSmall
                )
            }

            HorizontalDivider(
                color = ifFocusActiveColor,
                thickness = ifFocusActiveBorder
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "${restMinutes.toString().padStart(2, '0')} : ${restSeconds.toString().padStart(2, '0')}",
                    style = MaterialTheme.typography.headlineSmall,
                    color = previewBoxColor
                )
            }
        }

        VerticalDivider(
            modifier = Modifier.fillMaxHeight(),
            color = ifFocusActiveColor,
            thickness = ifFocusActiveBorder
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ClickablePlusSign {
                onBannerShow()
            }
        }
    }
}
