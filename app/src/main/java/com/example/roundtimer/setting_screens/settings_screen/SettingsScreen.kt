package com.example.roundtimer.setting_screens.settings_screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.roundtimer.R
import com.example.roundtimer.common.BackIconButton
import com.example.roundtimer.setting_screens.settings_screen.transition_settings_screen.TransitionScreenOption
import com.example.roundtimer.setting_screens.settings_screen.transition_settings_screen.TransitionSettingsViewModel

@Composable
fun SettingsScreen(
    onSwipeBack: () -> Unit
) {
    val transitionSettingsViewModel: TransitionSettingsViewModel = viewModel()
    val selectedOption by transitionSettingsViewModel.selectedOption.collectAsState()
    var isExpanded by remember { mutableStateOf(false) }
    val transitionScreenDurationString = stringResource(id = R.string.TransitionScreenDuration)

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

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .padding(top = 64.dp)
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { isExpanded = !isExpanded }
                        .padding(vertical = 16.dp, horizontal = 8.dp)
                ) {
                    Text(
                        text = transitionScreenDurationString,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.weight(1f)
                    )
                    Icon(
                        imageVector = if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                        contentDescription = if (isExpanded) "Collapse" else "Expand"
                    )
                }

                if (isExpanded) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp)
                    ) {
                        TransitionScreenOption.entries.forEach { option ->
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable { transitionSettingsViewModel.selectOption(option) }
                                    .padding(vertical = 8.dp)
                            ) {
                                RadioButton(
                                    selected = (option == selectedOption),
                                    onClick = { transitionSettingsViewModel.selectOption(option) }
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(text = option.getDisplayName())
                            }
                        }
                    }
                }
            }
        }
    }
}
