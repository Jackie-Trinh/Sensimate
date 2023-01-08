package com.example.sensimate.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.sensimate.screens.homeScreen.HomeScreen

//The root navigation graph, that leads to the other navigation graphs
@Composable
fun RootNavGraph(navController: NavHostController){
    NavHost(navController = navController,
        //start with the authentication screen, with it's navGraph
        startDestination = Graph.AUTHENTICATION, route = Graph.ROOT){
        authNavGraph(navController = navController)
        //instantiate main screen so it is available later for calling //second screen
        composable(route = Graph.HOME){
            HomeScreen()
        }
    }
}

//Make the different graph objects that are available
object Graph {
    const val ROOT = "root_graph"
    const val AUTHENTICATION = "auth_graph"
    const val HOME = "home_graph"
}