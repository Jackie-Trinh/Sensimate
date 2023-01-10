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
import com.example.sensimate.screens.eventManager.addEvent.ManageEvent
import com.example.sensimate.screens.eventPage.EventPage
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
        startDestination = NavRoutes.EventManager.route,
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
                    navController.navigate("${NavRoutes.AddEvent.route}/${eventId}")
                }
            )
        }

        //EventManager screen
        composable(NavRoutes.EventManager.route) {
            EventManager(
                navController = navController,
                navigateToAddEventScreen = { navController.navigate(NavRoutes.AddEvent.route) }
            )
        }

        //Add event in ManageEvent screen
        composable(NavRoutes.AddEvent.route) {
            ManageEvent(
                navController = navController,
                eventId = null,
                addingEvent = true,
            )
        }

        //Update event in ManageEvent screen
        composable(
            route = "${NavRoutes.AddEvent.route}/{${Constants.EVENT_ID}}",
            arguments = listOf(
                navArgument(Constants.EVENT_ID) {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val eventId = backStackEntry.arguments?.getInt(Constants.EVENT_ID) ?: 0
            ManageEvent(
                navController = navController,
                eventId = eventId,
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