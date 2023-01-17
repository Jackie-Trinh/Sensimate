package com.example.sensimate.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.sensimate.core.Constants
import com.example.sensimate.core.Constants.Companion.EVENT_DEFAULT_ID
import com.example.sensimate.core.Constants.Companion.EVENT_ID
import com.example.sensimate.core.Constants.Companion.EVENT_ID_ARG
import com.example.sensimate.screens.discover.Discover
import com.example.sensimate.screens.edit_event.EditEventScreen
import com.example.sensimate.screens.event_manager.EventManager
import com.example.sensimate.screens.event_manager.manage_event.ManageEvent
import com.example.sensimate.screens.event_page.EventPage
import com.example.sensimate.screens.event_manager.manage_event.manage_survey.ManageSurvey
import com.example.sensimate.screens.event_manager.manage_event.manage_survey.manage_question.ManageQuestion
import com.example.sensimate.screens.event_page.EventPage2
import com.example.sensimate.screens.myEvents.MyEvents
import com.example.sensimate.screens.profile.Profile
import com.example.sensimate.screens.profile.ProfileViewModel
import com.example.sensimate.screens.survey.Survey2


//The navigation graph for the main screens
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeNavGraph(navController: NavHostController){
    NavHost(navController = navController,
        startDestination = BottomBarScreen.Discover.route,
        route = Graph.HOME ){

        composable(BottomBarScreen.Discover.route) {
            Discover(navController = navController)
        }
        composable(BottomBarScreen.Profile.route) {
            Profile(navController = navController, profileViewModel = ProfileViewModel())
        }
//        composable(BottomBarScreen.Survey.route) {
//            Survey(navController = navController)
//        }


        //EventPage screen
        composable(route = "${BottomBarScreen.EventPage.route}/{${Constants.EVENT_ID}}",
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

        //EventManager screen
        composable(BottomBarScreen.EventManagerPage.route) {
            EventManager(
                navController = navController,
            )
        }

        //Add event in ManageEvent screen
        composable(BottomBarScreen.ManageEventPage.route) {
            ManageEvent(
                navController = navController,
                eventId = null,
                addingEvent = true,
            )
        }

        //Update event in ManageEvent screen
        composable(
            route = "${BottomBarScreen.ManageEventPage.route}/{${Constants.EVENT_ID}}",
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

        composable(
            route = "${BottomBarScreen.ManageSurveyPage.route}/{${Constants.EVENT_ID}}",
            arguments = listOf(
                navArgument(Constants.EVENT_ID) {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val eventId = backStackEntry.arguments?.getInt(Constants.EVENT_ID) ?: 0
            ManageSurvey(
                navController = navController,
                eventId = eventId,
            )
        }

        //Edit question
        composable(
            route = "${BottomBarScreen.ManageQuestionPage.route}/{${Constants.EVENT_ID}}/{${Constants.QUESTION_NUMBER}}",
            arguments = listOf(
                navArgument(Constants.EVENT_ID) {
                    type = NavType.IntType
                },
                navArgument(Constants.QUESTION_NUMBER) {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val eventId = backStackEntry.arguments?.getInt(Constants.EVENT_ID) ?: 0
            val questionNumber = backStackEntry.arguments?.getInt(Constants.QUESTION_NUMBER) ?: 0
            ManageQuestion(
                navController = navController,
                eventId = eventId,
                questionNumber = questionNumber,
            )
        }

        //Survey
        composable(
            route = "${BottomBarScreen.Survey.route}/{${Constants.EVENT_ID}}",
            arguments = listOf(
                navArgument(Constants.EVENT_ID) {
                    type = NavType.IntType
                },
            )
        ) { backStackEntry ->
            val eventId = backStackEntry.arguments?.getInt(Constants.EVENT_ID) ?: 0
//            Survey(
//                navController = navController,
//                eventId = eventId,
//            )
        }

        //MyEvents
        composable(BottomBarScreen.MyEvents.route) {
            MyEvents(
                navController = navController,
                openScreen = { route -> navController.navigate(route) }
            )
        }

        //New edit event screen
        composable(
            route = "${BottomBarScreen.EditEvent.route}$EVENT_ID_ARG",
            arguments = listOf(navArgument(EVENT_ID) { defaultValue = EVENT_DEFAULT_ID })
        ) {
            EditEventScreen(
                navController = navController,
                popUpScreen = { navController.popBackStack() },
                eventId = it.arguments?.getString(EVENT_ID) ?: EVENT_DEFAULT_ID
            )
        }

        //TODO: rename and merge new pages with old
        //New event page 2 navigation
        composable(
            route = "${BottomBarScreen.EventPage2.route}$EVENT_ID_ARG",
            arguments = listOf(navArgument(EVENT_ID) { defaultValue = EVENT_DEFAULT_ID })
        ) {
            EventPage2(
                openScreen = { route -> navController.navigate(route) },
                popUpScreen = { navController.popBackStack() },
                eventId = it.arguments?.getString(EVENT_ID) ?: EVENT_DEFAULT_ID
            )
        }

        //New survey page 2 navigation
        composable(
            route = "${BottomBarScreen.Survey2.route}$EVENT_ID_ARG",
            arguments = listOf(navArgument(EVENT_ID) { defaultValue = EVENT_DEFAULT_ID })
        ) {
            Survey2(
                navController = navController,
//                popUpScreen = { navController.popBackStack() },
                eventId = it.arguments?.getString(EVENT_ID) ?: EVENT_DEFAULT_ID
            )
        }


    }
}