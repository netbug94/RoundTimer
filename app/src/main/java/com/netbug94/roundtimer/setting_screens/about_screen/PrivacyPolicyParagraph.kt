package com.netbug94.roundtimer.setting_screens.about_screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.netbug94.roundtimer.R
import com.netbug94.roundtimer.common.BackIconButton

@Composable
fun PrivacyPolicyParagraph(
    onSwipeBack: () -> Unit,
) {
    val termsOfServiceParagraphTitle = stringResource(R.string.PrivacyPolicyParagraphTitleString)
    val termsOfServiceParagraph = stringResource(R.string.PrivacyPolicyParagraphString)

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

    LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp).systemBarsPadding().padding(top = 56.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        item {
            Text(modifier = Modifier
                .padding(bottom = 26.dp)
                .fillMaxWidth(),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
                text = termsOfServiceParagraphTitle
            )
            Text(modifier = Modifier
                .fillMaxWidth(),
                textAlign = TextAlign.Start,
                text = termsOfServiceParagraph)
        }
    }
}