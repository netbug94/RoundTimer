package com.example.roundtimer.presentation.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun BackArrowButton(
    onBackArrowClick: () -> Unit,
    size: Dp = 60.dp,
    leTint: Color = MaterialTheme.colorScheme.primary,
    rowModifier: Modifier = Modifier,
    rowAlignment: Alignment.Vertical = Alignment.CenterVertically,
    rowArrangement: Arrangement.Horizontal = Arrangement.Start,
    iconModifier: Modifier = Modifier
) {
    Row(
        modifier = rowModifier,
        verticalAlignment = rowAlignment,
        horizontalArrangement = rowArrangement
    ) {
        Icon(modifier = iconModifier
                .size(size)
                .clip(
                    RoundedCornerShape(
                        topStart = 10.dp,
                        topEnd = 10.dp,
                        bottomStart = 10.dp,
                        bottomEnd = 10.dp
                    )
                )
                .clickable(
                    onClick = onBackArrowClick
                ),
            imageVector = Icons.AutoMirrored.Default.KeyboardArrowLeft,
            contentDescription = "Back arrow button",
            tint = leTint
        )
    }
}