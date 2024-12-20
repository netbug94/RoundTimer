package com.netbug94.roundtimer.first_screen.presentation.settings_button

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.netbug94.roundtimer.R
import com.netbug94.roundtimer.common.getImageIds
import com.netbug94.roundtimer.ui.theme.customColorScheme

@Composable
fun SettingsButton(
    onSettingsClick: () -> Unit,
    onTipsClick: () -> Unit,
    onAboutClick: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val imageId = getImageIds().first
    val customColor = customColorScheme()
    val textDropdownColor = customColor.customTextColor
    val menuItems = listOf(
        stringResource(R.string.SettingsString),
        stringResource(R.string.TipsString),
        stringResource(R.string.AboutString)
    )

    Box(modifier = Modifier.size(25.dp)) {
        Image(
            painter = painterResource(id = imageId),
            contentDescription = "Dots image",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 4.dp)
                .clip(RoundedCornerShape(2.5.dp))
                .clickable { expanded = true }
        )

        Column(modifier = Modifier.fillMaxWidth().padding(end = 8.dp)) {
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.width(90.dp).background(customColor.customRippleColor)
            ) {
                menuItems.forEachIndexed { index, item ->
                    DropdownMenuItem(
                        modifier = Modifier.fillMaxWidth(),
                        text = {
                            Text(text = item,
                                color = textDropdownColor,
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center
                            )
                        },
                        onClick = {
                            expanded = false
                            when (index) {
                                0 -> onSettingsClick()
                                1 -> onTipsClick()
                                2 -> onAboutClick()
                            }
                        }
                    )

                    if (index < menuItems.lastIndex) {
                        HorizontalDivider(
                            Modifier
                                .height(2.dp)
                                .padding(horizontal = 8.dp),
                            color = customColor.customTextColor
                        )
                    }
                }
            }
        }
    }
}
