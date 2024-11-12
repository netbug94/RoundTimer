package com.example.roundtimer.setting_screens.tips_screen

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
import androidx.compose.ui.unit.dp
import com.example.roundtimer.R
import com.example.roundtimer.common.BackIconButton

@Composable
fun TipsScreen(
    onSwipeBack: () -> Unit
) {
    val tips = listOf(
        Tip(1, "Input data", R.drawable.tip_shot_1),
        Tip(2, "Save your entity", R.drawable.tip_shot_1),
        Tip(3, "View saved entities", R.drawable.tip_shot_1)
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