package com.example.roundtimer.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.roundtimer.presentation.common.view_model.WorkoutInputViewModel
import com.example.roundtimer.presentation.first_screen.FirstScreen
import com.example.roundtimer.presentation.five_second_screen.FiveSecondScreen
import com.example.roundtimer.presentation.round_screen.RoundScreen
import com.example.roundtimer.presentation.setting_screens.about_screen.AboutScreen
import com.example.roundtimer.presentation.setting_screens.SettingsScreen
import com.example.roundtimer.presentation.setting_screens.TipsScreen

@Composable
fun NavigationManager(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val workoutInputVM: WorkoutInputViewModel = viewModel()

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = NavDestination.FirstScreenNavi
    ) {

        composable<NavDestination.FirstScreenNavi> {
            FirstScreen(
                onStartClick = {
                    navController.navigate(NavDestination.FiveSecondScreenNavi)
                },
                onSettingsClick = {
                    navController.navigate(NavDestination.SettingsScreen)
                },
                onTipsClick = {
                    navController.navigate(NavDestination.TipsScreen)
                },
                onAboutClick = {
                    navController.navigate(NavDestination.AboutScreen)
                },
                workoutInputVM = workoutInputVM
            )
        }
        composable<NavDestination.FiveSecondScreenNavi> {
            FiveSecondScreen(
                onNavigation = {
                    navController.navigate(NavDestination.RoundScreenNavi)
                },
                onSwipeBack = {
                    navController.navigateUp()
                }
            )
        }
        composable<NavDestination.RoundScreenNavi> {
            RoundScreen(
                onSwipeBack = {
                    navController.popBackStack(NavDestination.FirstScreenNavi, false)
                },
                workoutInputVM = workoutInputVM
            )
        }
        composable<NavDestination.SettingsScreen> {
            SettingsScreen(
                onSwipeBack = {
                    navController.popBackStack(NavDestination.FirstScreenNavi, false)
                },
            )
        }
        composable<NavDestination.AboutScreen> {
            AboutScreen(
                onSwipeBack = {
                    navController.popBackStack(NavDestination.FirstScreenNavi, false)
                },
            )
        }
        composable<NavDestination.TipsScreen> {
            TipsScreen(
                onSwipeBack = {
                    navController.popBackStack(NavDestination.FirstScreenNavi, false)
                },
            )
        }
    }
}
