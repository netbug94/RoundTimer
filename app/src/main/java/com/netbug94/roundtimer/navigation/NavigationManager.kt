package com.netbug94.roundtimer.navigation

import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.netbug94.roundtimer.first_screen.presentation.FirstScreen
import com.netbug94.roundtimer.first_screen.presentation.WorkoutInputViewModel
import com.netbug94.roundtimer.round_screen.presentation.RoundScreen
import com.netbug94.roundtimer.save_screen.data.RoomDatabaseProvider
import com.netbug94.roundtimer.save_screen.data.WorkoutRoomRepositoryImpl
import com.netbug94.roundtimer.save_screen.domain.WorkoutRoomDao
import com.netbug94.roundtimer.save_screen.domain.WorkoutRoomRepository
import com.netbug94.roundtimer.save_screen.presentation.SavedWorkoutScreen
import com.netbug94.roundtimer.save_screen.presentation.WorkoutRoomViewModel
import com.netbug94.roundtimer.save_screen.presentation.WorkoutRoomViewModelFactory
import com.netbug94.roundtimer.setting_screens.tips_screen.TipsScreen
import com.netbug94.roundtimer.setting_screens.about_screen.AboutScreen
import com.netbug94.roundtimer.setting_screens.about_screen.FeedbackForm
import com.netbug94.roundtimer.setting_screens.about_screen.PrivacyPolicyParagraph
import com.netbug94.roundtimer.setting_screens.about_screen.TermsOfServiceParagraph
import com.netbug94.roundtimer.setting_screens.settings_screen.SettingsScreen
import com.netbug94.roundtimer.setting_screens.settings_screen.round_beep_settings.presentation.RoundBeepViewModel
import com.netbug94.roundtimer.transition_screen.five_second_screen.presentation.FiveSecondScreen
import com.netbug94.roundtimer.transition_screen.three_second_screen.ThreeSecondScreen

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
    val beepSettingsViewModel: RoundBeepViewModel = viewModel()

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
                workoutInputVM = workoutInputVM,
                beepSettingsViewModel = beepSettingsViewModel
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
