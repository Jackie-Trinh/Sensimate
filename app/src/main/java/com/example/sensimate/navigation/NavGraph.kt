package com.example.sensimate.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.sensimate.screens.myEvents.MyEvents
import com.example.sensimate.screens.discover.Discover
import com.example.sensimate.screens.discover.DiscoverViewModel
import com.example.sensimate.screens.eventPage.EventPage
import com.example.sensimate.screens.eventPage.EventPageViewModel
import com.example.sensimate.screens.landing.LandingPage
import com.example.sensimate.screens.landing.LandingPageViewModel
import com.example.sensimate.screens.login.Login
import com.example.sensimate.screens.login.LoginViewModel
import com.example.sensimate.screens.myEvents.MyEventsViewModel
import com.example.sensimate.screens.profile.Profile
import com.example.sensimate.screens.profile.ProfileViewModel
import com.example.sensimate.screens.survey.Survey
import com.example.sensimate.screens.survey.SurveyViewModel

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = NavRoutes.Discover.route,
    ) {
        composable(NavRoutes.MyEvents.route) {
            MyEvents(navController = navController, myEventsViewModel = MyEventsViewModel())
        }

        composable(NavRoutes.Discover.route) {
            Discover(navController = navController, discoverViewModel = DiscoverViewModel())
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
        composable(NavRoutes.LandingPage.route) {
            LandingPage(navController = navController, landingPageViewModel = LandingPageViewModel())
        }
    }
}