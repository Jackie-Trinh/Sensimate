package com.example.sensimate.screens.myEvents


import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.sensimate.model.EventCardSelection

@Composable
fun MyEvents(navController: NavController, myEventsViewModel: MyEventsViewModel) {
    //text data for user events
    val events = listOf(com.example.sensimate.data.Event(
            3,
            "Event 1",
            "Desc 1",
            "06-01-2023",
            "H.C Andersens vej 3",
            listOf(com.example.sensimate.data.EventItem(
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
