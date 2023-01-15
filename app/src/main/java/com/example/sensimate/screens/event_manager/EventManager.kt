package com.example.sensimate.screens.event_manager

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.sensimate.model.EventCardSelection
import com.example.sensimate.screens.myEvents.MyEventsViewModel
import com.example.sensimate.screens.event_manager.components.AddEventButton
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

@Composable
fun EventManager(
    navController: NavController,
    viewModel: EventManagerViewModel = hiltViewModel(),
) {
    val events by viewModel.events.collectAsState(
        initial = emptyList()
    )


    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        Spacer(modifier = Modifier.height(10.dp))

        AddEventButton(
            navController = navController,
        )

        EventCardSelection(
            navController = navController,
            events = events,
        )


    }


}
