package com.example.roundtimer.presentation.first_screen.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.roundtimer.R


@Composable
fun SettingsButtonPreview() {
    var expanded by remember { mutableStateOf(false) }

    val isDarkTheme = isSystemInDarkTheme()
    val imageId = if (isDarkTheme) R.drawable.lightdots else R.drawable.darkdots

    Box(modifier = Modifier.size(25.dp)) {
        Image(
            painter = painterResource(id = imageId),
            contentDescription = if (isDarkTheme) "Dark theme dots" else "Light theme dots",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 4.dp)
                .clip(RoundedCornerShape(2.5.dp))
                .clickable { expanded = true } // Show dropdown menu when clicked
        )

        // Dropdown Menu
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .size(150.dp) // Set size if needed
        ) {
            DropdownMenuItem(
                text = { Text(text = "Settings") },
                onClick = {
                    // Handle option 1 click
                    expanded = false
                }
            )
            DropdownMenuItem(
                text = { Text(text = "About") },
                onClick = {
                    // Handle option 2 click
                    expanded = false
                }
            )
            DropdownMenuItem(
                text = { Text(text = "Tips") },
                onClick = {
                    // Handle option 3 click
                    expanded = false
                }
            )
        }
    }
}