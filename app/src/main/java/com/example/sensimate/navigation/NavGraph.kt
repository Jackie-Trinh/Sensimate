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
import com.example.sensimate.screens.eventManager.EventManager
import com.example.sensimate.screens.eventManager.addEvent.AddEvent
import com.example.sensimate.screens.eventPage.EventPage
import com.example.sensimate.screens.eventPage.EventPageViewModel
import com.example.sensimate.screens.login.Login
import com.example.sensimate.screens.login.LoginViewModel
import com.example.sensimate.screens.profile.Profile
import com.example.sensimate.screens.profile.ProfileViewModel
import com.example.sensimate.screens.survey.Survey
import com.example.sensimate.screens.survey.SurveyViewModel
import com.example.sensimate.screens.eventManager.updateEvent.components.UpdateEventScreen

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = NavRoutes.Discover.route,
    ) {
        //MyEvents screen
        composable(NavRoutes.MyEvents.route) {
            MyEvents(
                navController = navController,
            )
        }


        //Discover screen
        composable(
            route = NavRoutes.Discover.route
        ) {
            Discover(
                navController = navController,
                navigateToUpdateEventScreen = { eventId ->
                    navController.navigate("${NavRoutes.UpdateEvent.route}/${eventId}")
                }
            )
        }

        //EventManager screen
        composable(NavRoutes.EventManager.route) {
            EventManager(
                navController = navController,
                navigateToUpdateEventScreen = { eventId ->
                    navController.navigate("${NavRoutes.UpdateEvent.route}/${eventId}")
                },
                navigateToAddEventScreen = { navController.navigate(NavRoutes.AddEvent.route) }
            )
        }

        //AddEvent screen
        composable(NavRoutes.AddEvent.route) {
            AddEvent(
                navigateBack = {
                    navController.popBackStack()
                }
            )
        }




        //UpdateEvent screen
        composable(
            route = "${NavRoutes.UpdateEvent.route}/{${Constants.EVENT_ID}}",
            arguments = listOf(
                navArgument(Constants.EVENT_ID) {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val eventId = backStackEntry.arguments?.getInt(Constants.EVENT_ID) ?: 0
            UpdateEventScreen(
                eventId = eventId,
                navigateBack = {
                    navController.popBackStack()
                }
            )
        }

        //EventPage screen
        composable(route = "${NavRoutes.EventPage.route}/{${Constants.EVENT_ID}}",
            arguments = listOf(
                navArgument(Constants.EVENT_ID) {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val eventId = backStackEntry.arguments?.getInt(Constants.EVENT_ID) ?: 0
            EventPage(
                navController = navController,
                eventId = eventId,
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

    }
}