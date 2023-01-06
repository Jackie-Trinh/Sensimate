package com.example.sensimate.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.sensimate.screens.MyEvents
import com.example.sensimate.screens.discover.Discover
import com.example.sensimate.screens.discover.DiscoverViewModel
import com.example.sensimate.screens.login.Login
import com.example.sensimate.screens.login.LoginViewModel
import com.example.sensimate.screens.profile.Profile
import com.example.sensimate.screens.profile.ProfileViewModel
import com.example.sensimate.screens.survey.Survey
import com.example.sensimate.screens.survey.SurveyViewModel

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = NavRoutes.MyEvents.route,
    ) {
        composable(NavRoutes.MyEvents.route) {
            MyEvents(navController = navController)
        }

        composable(NavRoutes.Discover.route) {
            Discover(navController = navController)
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
    }
}