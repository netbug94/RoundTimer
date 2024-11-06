package com.example.roundtimer.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun BackIconButton(
    onBackIconClick: () -> Unit,
    size: Dp = 60.dp,
    iconTint: Color = MaterialTheme.colorScheme.primary,
    boxModifier: Modifier = Modifier,
    boxAlignment: Alignment = Alignment.Center,
    buttonIcon: ImageVector = Icons.AutoMirrored.Default.KeyboardArrowLeft,
    iconModifier: Modifier = Modifier
) {
    Box(
        modifier = boxModifier,
        contentAlignment = boxAlignment
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
                    onClick = onBackIconClick
                ),
            imageVector = buttonIcon,
            contentDescription = "Back icon button",
            tint = iconTint
        )
    }
}
