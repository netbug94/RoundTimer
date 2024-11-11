package com.example.roundtimer.setting_screens.settings_screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
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
import com.example.roundtimer.setting_screens.settings_screen.round_beep_settings.presentation.BeepOption
import com.example.roundtimer.setting_screens.settings_screen.round_beep_settings.presentation.RoundBeepViewModel
import com.example.roundtimer.setting_screens.settings_screen.round_beep_settings.presentation.getDisplayName
import com.example.roundtimer.setting_screens.settings_screen.transition_settings_screen.presentation.TransitionScreenOption
import com.example.roundtimer.setting_screens.settings_screen.transition_settings_screen.presentation.TransitionSettingsViewModel
import com.example.roundtimer.setting_screens.settings_screen.transition_settings_screen.presentation.getDisplayName
import com.example.roundtimer.setting_screens.settings_screen.voice_settings.presentation.VoiceOption
import com.example.roundtimer.setting_screens.settings_screen.voice_settings.presentation.VoiceSettingsViewModel
import com.example.roundtimer.setting_screens.settings_screen.voice_settings.presentation.getDisplayName

@Composable
fun SettingsScreen(
    onSwipeBack: () -> Unit
) {
    val transitionSettingsViewModel: TransitionSettingsViewModel = viewModel()
    val selectedOption by transitionSettingsViewModel.selectedOption.collectAsState()
    val transitionScreenDurationString = stringResource(id = R.string.TransitionScreenDuration)
    var isTransitionDurationExpanded by remember { mutableStateOf(false) }

    val voiceSettingsViewModel: VoiceSettingsViewModel = viewModel()
    val selectedVoiceOption by voiceSettingsViewModel.selectedVoiceOption.collectAsState()
    var isVoiceOptionsExpanded by remember { mutableStateOf(false) }
    val voiceOptionsString = stringResource(R.string.VoiceOptionsString)

    val roundBeepViewModel: RoundBeepViewModel = viewModel()
    val selectedBeepOption by roundBeepViewModel.selectedBeepOption.collectAsState()
    var isBeepOptionsExpanded by remember { mutableStateOf(false) }
    val beepOptionsString = stringResource(R.string.BeepOptionsString)

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
            ExpandableOptionGroup(
                title = transitionScreenDurationString,
                options = TransitionScreenOption.entries,
                selectedOption = selectedOption,
                isExpanded = isTransitionDurationExpanded,
                onExpandedChange = { isTransitionDurationExpanded = it },
                onOptionSelected = { option ->
                    transitionSettingsViewModel.selectOption(option)
                },
                optionLabel = { option -> option.getDisplayName() },
                modifier = Modifier.fillMaxWidth()
            )
        }
        item {
            ExpandableOptionGroup(
                title = voiceOptionsString,
                options = VoiceOption.entries,
                selectedOption = selectedVoiceOption,
                isExpanded = isVoiceOptionsExpanded,
                onExpandedChange = { expanded ->
                    isVoiceOptionsExpanded = expanded
                },
                onOptionSelected = { option ->
                    voiceSettingsViewModel.selectVoiceOption(option)
                },
                optionLabel = { option -> option.getDisplayName() },
                modifier = Modifier.fillMaxWidth()
            )
        }
        item {
            ExpandableOptionGroup(
                title = beepOptionsString,
                options = BeepOption.entries,
                selectedOption = selectedBeepOption,
                isExpanded = isBeepOptionsExpanded,
                onExpandedChange = { isBeepOptionsExpanded = it },
                onOptionSelected = { option ->
                    roundBeepViewModel.selectBeepOption(option)
                },
                optionLabel = { option -> option.getDisplayName() },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
