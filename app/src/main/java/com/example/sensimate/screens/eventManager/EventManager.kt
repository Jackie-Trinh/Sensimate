package com.example.sensimate.screens.eventManager

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
import com.example.sensimate.screens.eventManager.components.AddEventButton

@Composable
fun EventManager(
    navController: NavController,
    viewModel: MyEventsViewModel = hiltViewModel(),
    navigateToUpdateEventScreen: (eventId: Int) -> Unit,
    navigateToAddEventScreen: () -> Unit,
) {
    val events by viewModel.events.collectAsState(
        initial = emptyList()
    )


    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        Spacer(modifier = Modifier.height(10.dp))

        AddEventButton(
            navigateToAddEventScreen = navigateToAddEventScreen
        )

        EventCardSelection(
            navController = navController,
            events = events,
            navigateToUpdateEventScreen = navigateToUpdateEventScreen,
        )


    }


}
