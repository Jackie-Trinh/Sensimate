package com.example.sensimate.navigation

sealed class NavRoutes(val route: String) {
    object MyEvents : NavRoutes("my_events")
    object Discover : NavRoutes("discover")
    object Profile : NavRoutes("profile")
    object Login : NavRoutes("login")
    object Survey : NavRoutes("survey")
    object UpdateEvent : NavRoutes("update_event")
    object EventPage : NavRoutes("event_page")
}