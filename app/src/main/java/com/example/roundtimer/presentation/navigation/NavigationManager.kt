package com.example.roundtimer.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.roundtimer.presentation.first_screen.WorkoutInputViewModel
import com.example.roundtimer.presentation.first_screen.FirstScreen
import com.example.roundtimer.data.room_data.RoomDatabaseProvider
import com.example.roundtimer.domain.room_domain.WorkoutRoomDao
import com.example.roundtimer.presentation.saved_workout_screen.SavedWorkoutScreen
import com.example.roundtimer.presentation.saved_workout_screen.WorkoutRoomViewModel
import com.example.roundtimer.presentation.saved_workout_screen.WorkoutRoomViewModelFactory
import com.example.roundtimer.presentation.round_screen.RoundScreen
import com.example.roundtimer.presentation.saved_workout_screen.WorkoutRoomRepository
import com.example.roundtimer.presentation.saved_workout_screen.WorkoutRoomRepositoryImpl
import com.example.roundtimer.presentation.setting_screens.settings_screen.SettingsScreen
import com.example.roundtimer.presentation.setting_screens.TipsScreen
import com.example.roundtimer.presentation.setting_screens.about_screen.AboutScreen
import com.example.roundtimer.presentation.setting_screens.about_screen.FeedbackForm
import com.example.roundtimer.presentation.setting_screens.about_screen.PrivacyPolicyParagraph
import com.example.roundtimer.presentation.setting_screens.about_screen.TermsOfServiceParagraph
import com.example.roundtimer.presentation.transition_screen.FiveSecondScreen
import com.example.roundtimer.presentation.transition_screen.three_second_screen.ThreeSecondScreen

@Composable
fun NavigationManager(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val workoutInputVM: WorkoutInputViewModel = viewModel()

    val context = LocalContext.current
    val workoutDao: WorkoutRoomDao = RoomDatabaseProvider.getRoomDatabase(context).workoutRoomDao()
    val repository: WorkoutRoomRepository = WorkoutRoomRepositoryImpl(workoutDao)
    val viewModelFactory = WorkoutRoomViewModelFactory(repository)
    val roomViewModel: WorkoutRoomViewModel = viewModel(
        factory = viewModelFactory
    )

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = NavDestination.FirstScreenNavi
    ) {

        composable<NavDestination.FirstScreenNavi> {
            FirstScreen(
                onStartClickFive= {
                    navController.navigate(NavDestination.FiveSecondScreenNavi)
                },
                onStartClickThree= {
                    navController.navigate(NavDestination.ThreeSecondScreenNavi)
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
                    navController.navigate(NavDestination.SavedWorkoutScreen)
                },
                workoutInputVM = workoutInputVM,
                roomViewModel = roomViewModel
            )
        }
        //Transition Screens
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
        composable<NavDestination.ThreeSecondScreenNavi> {
            ThreeSecondScreen(
                onNavigation = {
                    navController.navigate(NavDestination.RoundScreenNavi)
                },
                onSwipeBack = {
                    navController.navigateUp()
                }
            )
        }

        //RoundScreen
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
        composable<NavDestination.SavedWorkoutScreen> {
            SavedWorkoutScreen(
                roomViewModel = roomViewModel
            ) {

            }
        }
    }
}
