package com.example.roundtimer.presentation.navigation

import kotlinx.serialization.Serializable

sealed class NavDestination{

    @Serializable
    data object FirstScreenNavi:NavDestination()

    @Serializable
    data object FiveSecondScreenNavi:NavDestination()

    @Serializable
    data object RoundScreenNavi:NavDestination()
}