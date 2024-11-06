package com.example.roundtimer.setting_screens.about_screen

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
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.roundtimer.R
import com.example.roundtimer.common.BackIconButton

@Composable
fun AboutScreen(
    onSwipeBack: () -> Unit,
    onPrivacyPolicyParagraph: () -> Unit,
    onTermsOfServiceParagraph: () -> Unit,
    onSendFeedbackForm: () -> Unit
) {
    val darkThemeTrue = isSystemInDarkTheme()
    val versionText = stringResource(R.string.Version)
    val isDarkThemeTrue = if (darkThemeTrue) painterResource(R.drawable.round_timer_about_icon_purple)
        else painterResource(R.drawable.round_timer_about_icon_light_purple)
    val appNameString = stringResource(R.string.app_name)
    val descriptionString = stringResource(R.string.Description)
    val privacyPolicyString = stringResource(R.string.PrivacyPolicy)
    val termsOfServiceString = stringResource(R.string.TermsOfService)
    val sendFeedback = stringResource(R.string.SendFeedback)

    BackHandler {
        onSwipeBack()
    }

    BackIconButton(
        boxModifier = Modifier
            .fillMaxWidth()
            .systemBarsPadding()
            .padding(16.dp),
        boxAlignment = Alignment.TopStart,
        onBackIconClick = onSwipeBack,
        size = 40.dp,
        iconTint = MaterialTheme.colorScheme.primary
    )

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
            text = appNameString,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(22.dp))

        // App Description
        Text(
            text = descriptionString,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Justify,
            modifier = Modifier.padding(horizontal = 10.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Version Info
        Text(
            text = "$versionText 2.0.0",
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Buttons for Privacy Policy, Terms, and Feedback
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { onPrivacyPolicyParagraph() }
        ) {
            Text(text = privacyPolicyString)
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { onTermsOfServiceParagraph() }
        ) {
            Text(text = termsOfServiceString)
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = { onSendFeedbackForm() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = sendFeedback)
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Contact Information
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            ContactMeText()
        }
    }
}
