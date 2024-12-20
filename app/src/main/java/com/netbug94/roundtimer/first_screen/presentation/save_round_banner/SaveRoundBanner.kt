package com.netbug94.roundtimer.first_screen.presentation.save_round_banner

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.netbug94.roundtimer.R
import com.netbug94.roundtimer.ui.theme.customColorScheme

@Composable
fun SaveRoundBanner(showBanner: Boolean) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val dynamicHeight = screenWidth * 0.25f // %
    val bannerTextColor = customColorScheme()
    val saveString = stringResource(id = R.string.SavedString)

    Box(modifier = Modifier.fillMaxSize()) {
        AnimatedVisibility(
            visible = showBanner,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(dynamicHeight)
                    .background(MaterialTheme.colorScheme.primary),
                contentAlignment = Alignment.Center
            ) {
                Text(saveString, color = bannerTextColor.customTextColor)
            }
        }
    }
}