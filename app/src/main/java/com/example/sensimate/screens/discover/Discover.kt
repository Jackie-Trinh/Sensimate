package com.example.sensimate.screens.discover

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.sensimate.model.EventCardSelection
import com.example.sensimate.navigation.NavRoutes


@Composable
fun Discover(navController: NavController, discoverViewModel: DiscoverViewModel) {
    //text data for user events
    val events = listOf(
        com.example.sensimate.data.Event(
            3,
            "Event 1",
            "Desc 1",
            "06-01-2023",
            "H.C Andersens vej 3",
            listOf(
                com.example.sensimate.data.EventItem(
                    1,
                    "item 1",
                    "desc 1",
                    "06-01-2023",
                    "H.C Andersens vej 3"
                )
            )
        ),
        com.example.sensimate.data.Event(
            2,
            "Event 2",
            "Desc 2",
            "06-01-2023",
            "H.C Andersens vej 3",
            listOf(
                com.example.sensimate.data.EventItem(
                    2,
                    "item 2",
                    "desc 2",
                    "06-01-2023",
                    "H.C Andersens vej 3"
                )
            )
        ),
        com.example.sensimate.data.Event(
            3,
            "Event 3",
            "Desc 3",
            "06-01-2023",
            "H.C Andersens vej 3",
            listOf(
                com.example.sensimate.data.EventItem(
                    3,
                    "item 3",
                    "desc 3",
                    "06-01-2023",
                    "H.C Andersens vej 3"
                )
            )
        )
    )

    EventCardSelection(navController, events)
}


