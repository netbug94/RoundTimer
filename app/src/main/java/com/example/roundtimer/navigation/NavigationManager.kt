package com.example.roundtimer.navigation

import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.roundtimer.save_screen.domain.WorkoutRoomDao
import com.example.roundtimer.first_screen.presentation.FirstScreen
import com.example.roundtimer.first_screen.presentation.WorkoutInputViewModel
import com.example.roundtimer.round_screen.RoundScreen
import com.example.roundtimer.save_screen.presentation.SavedWorkoutScreen
import com.example.roundtimer.save_screen.presentation.WorkoutRoomRepository
import com.example.roundtimer.save_screen.presentation.WorkoutRoomRepositoryImpl
import com.example.roundtimer.save_screen.presentation.WorkoutRoomViewModel
import com.example.roundtimer.save_screen.presentation.WorkoutRoomViewModelFactory
import com.example.roundtimer.setting_screens.TipsScreen
import com.example.roundtimer.setting_screens.about_screen.AboutScreen
import com.example.roundtimer.setting_screens.about_screen.FeedbackForm
import com.example.roundtimer.setting_screens.about_screen.PrivacyPolicyParagraph
import com.example.roundtimer.setting_screens.about_screen.TermsOfServiceParagraph
import com.example.roundtimer.setting_screens.settings_screen.SettingsScreen
import com.example.roundtimer.transition_screen.five_second_screen.FiveSecondScreen
import com.example.roundtimer.transition_screen.three_second_screen.ThreeSecondScreen
import com.example.roundtimer.save_screen.data.RoomDatabaseProvider

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
                onCollectionClick = {
                    navController.navigate(NavDestination.SavedWorkoutScreen)
                },
                onSwipeBack = {
                    navController.popBackStack(ExitTransition, false)
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
                    navController.popBackStack(NavDestination.FirstScreenNavi, false)
                }
            )
        }
        composable<NavDestination.ThreeSecondScreenNavi> {
            ThreeSecondScreen(
                onNavigation = {
                    navController.navigate(NavDestination.RoundScreenNavi)
                },
                onSwipeBack = {
                    navController.popBackStack(NavDestination.FirstScreenNavi, false)
                }
            )
        }

        //RoundScreen
        composable<NavDestination.RoundScreenNavi> {
            RoundScreen(
                onSwipeBack = {
                    navController.popBackStack(NavDestination.FirstScreenNavi, false)
                },
                onHomeIconClick = {
                    navController.navigate(NavDestination.FirstScreenNavi)
                },
                onListIconClick = {
                    navController.navigate(NavDestination.SavedWorkoutScreen)
                },
                workoutInputVM = workoutInputVM
            )
        }

        //Settings Screen
        composable<NavDestination.SettingsScreen> {
            SettingsScreen(
                onSwipeBack = {
                    navController.popBackStack(NavDestination.FirstScreenNavi, false)
                }
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
                }
            )
        }

        //Test
        composable<NavDestination.SavedWorkoutScreen> {
            SavedWorkoutScreen(
                roomViewModel = roomViewModel,
                onHomeClick = {
                    navController.navigate(NavDestination.FirstScreenNavi)
                },
                onStartClickFive= {
                    navController.navigate(NavDestination.FiveSecondScreenNavi)
                },
                onStartClickThree= {
                    navController.navigate(NavDestination.ThreeSecondScreenNavi)
                },
                onSwipeBack = {
                    navController.popBackStack(NavDestination.FirstScreenNavi, false)
                },
                workoutInputVM = workoutInputVM
            )
        }
    }
}