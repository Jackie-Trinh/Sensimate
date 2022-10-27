package com.example.sensimate

sealed class NavRoutes(val route: String) {
    object MyEvents : NavRoutes("myevents")
    object Discover : NavRoutes("discover")
    object Profile : NavRoutes("profile")
    object Login : NavRoutes("login")
    object Survey : NavRoutes("survey")
}