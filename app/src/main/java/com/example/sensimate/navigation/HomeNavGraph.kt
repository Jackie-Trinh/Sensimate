package com.example.sensimate.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.sensimate.screens.discover.Discover
import com.example.sensimate.screens.discover.DiscoverViewModel
import com.example.sensimate.screens.eventPage.EventPage
import com.example.sensimate.screens.eventPage.EventPageViewModel
import com.example.sensimate.screens.myEvents.MyEvents
import com.example.sensimate.screens.myEvents.MyEventsViewModel
import com.example.sensimate.screens.profile.Profile
import com.example.sensimate.screens.profile.ProfileViewModel
import com.example.sensimate.screens.survey.Survey
import com.example.sensimate.screens.survey.SurveyViewModel


//The navigation graph for the main screens
@Composable
fun HomeNavGraph(navController: NavHostController){
    NavHost(navController = navController,
        startDestination = BottomBarScreen.Discover.route,
        route = Graph.HOME ){
        composable(BottomBarScreen.MyEvents.route) {
            MyEvents(navController = navController, myEventsViewModel = MyEventsViewModel())
        }
        composable(BottomBarScreen.Discover.route) {
            Discover(navController = navController, discoverViewModel = DiscoverViewModel())
        }
        composable(BottomBarScreen.Profile.route) {
            Profile(navController = navController, profileViewModel = ProfileViewModel())
        }
        composable(BottomBarScreen.Survey.route) {
            Survey(navController = navController, surveyViewModel = SurveyViewModel())
        }
        composable(BottomBarScreen.EventPage.route) {
            EventPage(navController = navController, eventPageViewModel = EventPageViewModel())
        }
    }
}