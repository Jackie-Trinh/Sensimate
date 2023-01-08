package com.example.sensimate.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
){
    object MyEvents : BottomBarScreen(
        route = "MyEvents",
        title = "MyEvents",
        icon = Icons.Filled.Favorite
    )

    object Discover : BottomBarScreen(
        route = "Discover",
        title = "Discover",
        icon = Icons.Filled.Home
    )

    object Profile : BottomBarScreen(
        route = "Profile",
        title = "Profile",
        icon = Icons.Filled.Person
    )

    object EventPage : BottomBarScreen(
        route = "EventPage",
        title = "EventPage",
        icon = Icons.Filled.Done
    )

    object Survey : BottomBarScreen(
        route = "Survey",
        title = "Survey",
        icon = Icons.Filled.Send
    )

}