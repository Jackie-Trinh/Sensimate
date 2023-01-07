package com.example.sensimate.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.sensimate.core.Constants
import com.example.sensimate.screens.myEvents.MyEvents
import com.example.sensimate.screens.discover.Discover
import com.example.sensimate.screens.discover.DiscoverViewModel
import com.example.sensimate.screens.eventPage.EventPage
import com.example.sensimate.screens.eventPage.EventPageViewModel
import com.example.sensimate.screens.login.Login
import com.example.sensimate.screens.login.LoginViewModel
import com.example.sensimate.screens.myEvents.MyEventsViewModel
import com.example.sensimate.screens.profile.Profile
import com.example.sensimate.screens.profile.ProfileViewModel
import com.example.sensimate.screens.survey.Survey
import com.example.sensimate.screens.survey.SurveyViewModel
import com.example.sensimate.screens.update_survey.UpdateSurveyScreen

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = NavRoutes.MyEvents.route,
    ) {
        composable(NavRoutes.MyEvents.route) {
            MyEvents(navController = navController)
        }

        composable(
            route = NavRoutes.Discover.route
        ) {
            Discover(
                navController = navController,
                navigateToUpdateSurveyScreen = { surveyId ->
                    navController.navigate("${NavRoutes.UpdateSurvey.route}/${surveyId}")
                }
            )
        }

        composable(
            route = "${NavRoutes.UpdateSurvey.route}/{${Constants.SURVEY_ID}}",
            arguments = listOf(
                navArgument(Constants.SURVEY_ID) {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val surveyId = backStackEntry.arguments?.getInt(Constants.SURVEY_ID) ?: 0
            UpdateSurveyScreen(
                surveyId = surveyId,
                navigateBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(NavRoutes.Login.route) {
            Login(navController = navController, loginViewModel = LoginViewModel())
        }

        composable(NavRoutes.Profile.route) {
            Profile(navController = navController, profileViewModel = ProfileViewModel())
        }
        composable(NavRoutes.Survey.route) {
            Survey(navController = navController, surveyViewModel = SurveyViewModel())
        }
        composable(NavRoutes.EventPage.route) {
            EventPage(navController = navController, eventPageViewModel = EventPageViewModel())
        }
    }
}