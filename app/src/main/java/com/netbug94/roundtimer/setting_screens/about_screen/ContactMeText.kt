package com.netbug94.roundtimer.setting_screens.about_screen

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import com.netbug94.roundtimer.R
import com.netbug94.roundtimer.ui.theme.customColorScheme

@Composable
fun ContactMeText() {
    val emailHandler = LocalContext.current
    val emailTextColor = customColorScheme().emailTextColor
    val contactMeString = stringResource(R.string.ContactMeString)

    Text(
        text = contactMeString,
        fontWeight = FontWeight.SemiBold,
        style = MaterialTheme.typography.bodyLarge
    )
    SelectionContainer {
        Text(
            text = "netbug94@gmail.com",
            style = MaterialTheme.typography.bodyLarge,
            textDecoration = TextDecoration.Underline,
            color = emailTextColor,
            modifier = Modifier
                .clickable {
                    val intent = Intent(Intent.ACTION_SENDTO).apply {
                        data = Uri.parse("mailto:netbug94@gmail.com")
                    }
                    emailHandler.startActivity(intent)
                }
        )
    }
}