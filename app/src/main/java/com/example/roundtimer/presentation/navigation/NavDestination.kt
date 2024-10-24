package com.example.roundtimer.presentation.navigation

import kotlinx.serialization.Serializable

sealed interface NavDestination{

    //Main
    @Serializable
    data object FirstScreenNavi:NavDestination

    @Serializable
    data object FiveSecondScreenNavi:NavDestination

    @Serializable
    data object RoundScreenNavi:NavDestination

    //Settings
    @Serializable
    data object SettingsScreen:NavDestination

    @Serializable
    data object AboutScreen:NavDestination

    @Serializable
    data object TipsScreen:NavDestination

    //Settings -> Privacy Policy
    @Serializable
    data object PrivacyPolicyParagraph:NavDestination
    //Settings -> Terms of Service
    @Serializable
    data object  TermsOfServiceParagraph:NavDestination

}