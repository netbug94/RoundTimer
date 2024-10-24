package com.example.roundtimer.presentation.setting_screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.roundtimer.R

@Composable
fun AboutScreen(
    onSwipeBack: () -> Unit
) {
    val darkThemeTrue = isSystemInDarkTheme()
    val versionText = stringResource(R.string.Version)

    val isDarkThemeTrue =
        if (darkThemeTrue) painterResource(R.drawable.round_timer_about_icon_light_purple)
        else painterResource(R.drawable.round_timer_about_icon_purple)

    BackHandler {
        onSwipeBack()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        //App Icon and Name
        Image(
            painter = isDarkThemeTrue,
            contentDescription = "App Icon",
            modifier = Modifier.size(200.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = stringResource(R.string.app_name),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(22.dp))

        // App Description
        Text(
            text = stringResource(R.string.Description),
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Justify,
            modifier = Modifier.padding(horizontal = 10.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Version Info
        Text(
            text = "$versionText 1.0.0",
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Buttons for Privacy Policy, Terms, and Feedback
        Button(
            onClick = { /* Navigate to Privacy Policy */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = stringResource(R.string.PrivacyPolicy))
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = { /* Navigate to Terms of Service */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = stringResource(R.string.TermsOfService))
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = { /* Open email or feedback form */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = stringResource(R.string.SendFeedback))
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Contact Information
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(R.string.ContactMe),
                fontWeight = FontWeight.SemiBold,
                style = MaterialTheme.typography.bodyLarge
            )
            SelectionContainer {
                Text(
                    text = " netbug94@gmail.com",
                    style = MaterialTheme.typography.bodyLarge,
                    textDecoration = TextDecoration.Underline,
                    color = Color.Blue
                )
            }
        }
    }
}
