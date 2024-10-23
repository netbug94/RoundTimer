package com.example.roundtimer.presentation.setting_screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun AboutScreen(
    onSwipeBack: () -> Unit
) {
    BackHandler {
        onSwipeBack()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        /* App Icon and Name
        Image(
            painter = painterResource(id = R.drawable.app_icon),
            contentDescription = "App Icon",
            modifier = Modifier.size(100.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
         */

        Text(
            text = "App Name",
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        // App Description
        Text(
            text = "This is a brief description of what the app does and its main features.",
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Version Info
        Text(
            text = "Version 1.0.0",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Buttons for Privacy Policy, Terms, and Feedback
        Button(
            onClick = { /* Navigate to Privacy Policy */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Privacy Policy")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = { /* Navigate to Terms of Service */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Terms of Service")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = { /* Open email or feedback form */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Send Feedback")
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Contact Information
        Text(
            text = "Contact Us: support@example.com",
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center
        )
    }
}
