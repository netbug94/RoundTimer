package com.example.roundtimer.presentation.first_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.example.roundtimer.ui.theme.CustomColorScheme

@Composable
fun ClickablePlusSign() {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val customOnSurface = CustomColorScheme()
    val textColor = if (isPressed) MaterialTheme.colorScheme.primary else customOnSurface

    Box(
        modifier = Modifier
            .size(56.dp)
            .clip(CircleShape)
            .clickable(
                interactionSource = interactionSource,
                indication = rememberRipple(color = MaterialTheme.colorScheme.primary),
                role = Role.Button
            ) {
                // Your click action here
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "+",
            color = textColor,
            modifier = Modifier.scale(1.6f)
        )
    }
}