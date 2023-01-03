package com.example.sensimate.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.sensimate.screens.*

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = NavRoutes.Discover.route,
    ) {
        composable(NavRoutes.MyEvents.route) {
            MyEvents()
        }

        composable(NavRoutes.Discover.route) {
            Discover(navController = navController)
        }

        composable(NavRoutes.Login.route) {
            Login()
        }

        composable(NavRoutes.Profile.route) {
            Profile()
        }
        composable(NavRoutes.Survey.route) {
            SurveyUI(navController = navController)
        }
    }
}