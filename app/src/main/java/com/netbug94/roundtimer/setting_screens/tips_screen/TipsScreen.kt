package com.netbug94.roundtimer.setting_screens.tips_screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.netbug94.roundtimer.R
import com.netbug94.roundtimer.common.BackIconButton

@Composable
fun TipsScreen(
    onSwipeBack: () -> Unit
) {
    val inputDataString = stringResource(R.string.InputDataString)
    val saveEntityString = stringResource(R.string.SaveEntityString)
    val viewSavedString = stringResource(R.string.ViewSavedString)
    val editSavedEntityString = stringResource(R.string.EditSavedEntityString)
    val useSavedEntityString = stringResource(R.string.UseSavedEntityString)
    val accessSettingsString = stringResource(R.string.AccessSettingsString)
    val alwaysOnDisplaySettingsString = stringResource(R.string.AlwaysOnDisplaySettingsString)

    val tips = listOf(
        Tip(1, inputDataString, R.drawable.tip_shot_1),
        Tip(2, saveEntityString, R.drawable.tip_shot_2),
        Tip(3, viewSavedString, R.drawable.tip_shot_3),
        Tip(4, editSavedEntityString, R.drawable.tip_shot_4),
        Tip(5, useSavedEntityString, R.drawable.tip_shot_5),
        Tip(6, accessSettingsString, R.drawable.tip_shot_6),
        Tip(7, alwaysOnDisplaySettingsString, R.drawable.tip_shot_7)
    )
    val expandedTipIds = remember { mutableStateMapOf<Int, Boolean>() }

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
        itemsIndexed(tips) { index, tip ->
            val isExpanded = expandedTipIds[tip.id] == true
            TipsExpandableOptionGroup(
                title = tip.title,
                imageRes = tip.imageRes,
                isExpanded = isExpanded,
                onExpandedChange = { expanded ->
                    expandedTipIds[tip.id] = expanded
                }
            )
        }
    }
}