package com.example.roundtimer.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.roundtimer.presentation.common.view_model.WorkoutInputViewModel
import com.example.roundtimer.presentation.first_screen.FirstScreen
import com.example.roundtimer.presentation.five_second_screen.FiveSecondScreen
import com.example.roundtimer.presentation.room.DatabaseProvider
import com.example.roundtimer.presentation.room.RoomViewModel
import com.example.roundtimer.presentation.room.RoomViewModelFactory
import com.example.roundtimer.presentation.room.WorkoutListScreen
import com.example.roundtimer.presentation.round_screen.RoundScreen
import com.example.roundtimer.presentation.setting_screens.SettingsScreen
import com.example.roundtimer.presentation.setting_screens.TipsScreen
import com.example.roundtimer.presentation.setting_screens.about_screen.AboutScreen
import com.example.roundtimer.presentation.setting_screens.about_screen.FeedbackForm
import com.example.roundtimer.presentation.setting_screens.about_screen.PrivacyPolicyParagraph
import com.example.roundtimer.presentation.setting_screens.about_screen.TermsOfServiceParagraph

@Composable
fun NavigationManager(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val workoutInputVM: WorkoutInputViewModel = viewModel()
    val context = LocalContext.current
    val roomViewModel: RoomViewModel = viewModel(
        factory = RoomViewModelFactory(DatabaseProvider.getDatabase(context).workoutDao())
    )

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
                onListScreen = {
                    navController.navigate(NavDestination.WorkoutListScreen)
                },
                workoutInputVM = workoutInputVM,
                roomViewModel = roomViewModel
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

        //Settings Screen
        composable<NavDestination.SettingsScreen> {
            SettingsScreen(
                onSwipeBack = {
                    navController.popBackStack(NavDestination.FirstScreenNavi, false)
                },
            )
        }

        //About Screen and sub-screens
        composable<NavDestination.AboutScreen> {
            AboutScreen(
                onSwipeBack = {
                    navController.popBackStack(NavDestination.FirstScreenNavi, false)
                },
                onTermsOfServiceParagraph = {
                    navController.navigate(NavDestination.TermsOfServiceParagraph)
                },
                onPrivacyPolicyParagraph = {
                    navController.navigate(NavDestination.PrivacyPolicyParagraph)
                },
                onSendFeedbackForm = {
                    navController.navigate(NavDestination.SendFeedbackForm)
                }
            )
        }
        composable<NavDestination.PrivacyPolicyParagraph> {
            PrivacyPolicyParagraph(
                onSwipeBack = {
                    navController.navigateUp()
                }
            )
        }
        composable<NavDestination.TermsOfServiceParagraph> {
            TermsOfServiceParagraph(
                onSwipeBack = {
                    navController.navigateUp()
                }
            )
        }
        composable<NavDestination.SendFeedbackForm> {
            FeedbackForm(
                onSwipeBack = {
                    navController.navigateUp()
                }
            )
        }

        //Tips Screen
        composable<NavDestination.TipsScreen> {
            TipsScreen(
                onSwipeBack = {
                    navController.popBackStack(NavDestination.FirstScreenNavi, false)
                },
            )
        }

        //Test
        composable<NavDestination.WorkoutListScreen> {
            WorkoutListScreen(
                roomViewModel = roomViewModel
            ) {

            }
        }
    }
}
