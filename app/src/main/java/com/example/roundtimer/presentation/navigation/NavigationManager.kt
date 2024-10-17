package com.example.roundtimer.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.roundtimer.presentation.first_screen.FirstScreen
import com.example.roundtimer.presentation.view_model.WorkoutInputViewModel
import com.example.roundtimer.presentation.five_second_screen.FiveSecondScreen
import com.example.roundtimer.presentation.round_screen.TestLogic

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
            TestLogic(
                onSwipeBack = {
                    navController.popBackStack(NavDestination.FirstScreenNavi, false)
                },
                workoutInputVM = workoutInputVM
            )
        }
    }
}
