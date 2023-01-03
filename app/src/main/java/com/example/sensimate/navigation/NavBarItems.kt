package com.example.sensimate.navigation

import androidx.compose.material.icons.*
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person

object NavBarItems {
    val BarItems = listOf(
        BarItem(
            title = "MyEvents",
            image = Icons.Filled.Favorite,
            route = "myevents"
        ),
        BarItem(
            title = "Discover",
            image = Icons.Filled.Home,
            route = "discover"
        ),
        BarItem(
            title = "Profile",
            image = Icons.Filled.Person,
            route = "Profile"
        )
    )
}