package com.example.sensimate.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.sensimate.screens.homeScreen.HomeScreen

@Composable
fun RootNavGraph(navController: NavHostController){
    NavHost(navController = navController,
        startDestination = Graph.AUTHENTICATION, route = Graph.ROOT){
        authNavGraph(navController = navController)
        composable(route = Graph.HOME){
            HomeScreen()
        }
    }
}

object Graph {
    const val ROOT = "root_graph"
    const val AUTHENTICATION = "auth_graph"
    const val HOME = "home_graph"
}