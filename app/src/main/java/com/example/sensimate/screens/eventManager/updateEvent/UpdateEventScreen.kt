package com.example.sensimate.screens.eventManager.updateEvent

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sensimate.screens.discover.DiscoverViewModel
import com.example.sensimate.screens.eventManager.updateEvent.components.UpdateEventContent
import com.example.sensimate.screens.eventManager.updateEvent.components.UpdateEventTopBar


@Composable
fun UpdateEventScreen(
    viewModel: DiscoverViewModel = hiltViewModel(),
    eventId: Int,
    navigateBack: () -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.getEvent(eventId)
    }
    Scaffold(
        topBar = {
            UpdateEventTopBar(
                navigateBack = navigateBack
            )
        },
        content = { padding ->
            UpdateEventContent(
                padding = padding,
                event = viewModel.event,
                updateTitle = { title ->
                    viewModel.updateTitle(title)
                },
                updateAddress = { address ->
                    viewModel.updateAddress(address)
                },
                updateEvent = { event ->
                    viewModel.updateEvent(event)
                },
                navigateBack = navigateBack
            )
        }
    )
}