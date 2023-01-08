package com.example.sensimate.screens.myEvents


import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.sensimate.model.EventCardSelection
import com.example.sensimate.screens.myEvents.components.AddEventAlertPopup
import com.example.sensimate.screens.myEvents.components.AddEventButton

@Composable
fun MyEvents(
    navController: NavController,
    viewModel: MyEventsViewModel = hiltViewModel(),
    navigateToUpdateEventScreen: (eventId: Int) -> Unit,
) {
    val events by viewModel.events.collectAsState(
        initial = emptyList()
    )

    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        EventCardSelection(
            navController = navController,
            events = events,
            navigateToUpdateEventScreen = navigateToUpdateEventScreen,
        )

        Spacer(modifier = Modifier.padding(0.dp))

        AddEventAlertPopup(
            openDialog = viewModel.openDialog,
            closeDialog = { viewModel.closeDialog() },
            addEvent = { event -> viewModel.addEvent(event) }
        )

        AddEventButton(
            openDialog = { viewModel.openDialog() }
        )
    }




}
