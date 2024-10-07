package com.example.roundtimer.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.roundtimer.presentation.first_screen.FirstScreen
import com.example.roundtimer.presentation.five_second_screen.FiveSecondScreen
import com.example.roundtimer.presentation.round_screen.RoundScreen

@Composable
fun NavigationManager(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = NavDestination.FirstScreenNavi) {

        composable<NavDestination.FirstScreenNavi> {
            FirstScreen {
                navController.navigate(NavDestination.FiveSecondScreenNavi)
            }
        }
        composable<NavDestination.FiveSecondScreenNavi> {
            FiveSecondScreen {
                navController.navigate(NavDestination.RoundScreenNavi)
            }
        }
        composable<NavDestination.RoundScreenNavi> {
            RoundScreen {

            }
        }
    }
}