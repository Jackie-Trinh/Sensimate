package com.example.sensimate.screens.eventManager.addEvent

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.sensimate.core.Constants
import com.example.sensimate.core.Constants.Companion.ADD
import com.example.sensimate.core.Constants.Companion.ADDRESS
import com.example.sensimate.core.Constants.Companion.DATE
import com.example.sensimate.core.Constants.Companion.DESCRIPTION
import com.example.sensimate.core.Constants.Companion.EVENT_TITLE
import com.example.sensimate.core.Constants.Companion.UPDATE
import com.example.sensimate.domain.model.Event
import com.example.sensimate.screens.eventManager.addEvent.components.ManageEventTopBar
import com.example.sensimate.screens.myEvents.MyEventsViewModel
import kotlinx.coroutines.job

//TODO: Refactor from AddEvent to ManageEvent
@Composable
fun AddEvent(
    viewModel: MyEventsViewModel = hiltViewModel(),
    navController: NavController,
    eventId: Int?,
    addingEvent: Boolean = false,
) {
    var id by remember { mutableStateOf(0) }
    var title by remember { mutableStateOf(Constants.NO_VALUE) }
    var address by remember { mutableStateOf(Constants.NO_VALUE) }
    var date by remember { mutableStateOf(Constants.NO_VALUE) }
    var description by remember { mutableStateOf(Constants.NO_VALUE) }

    if (!addingEvent) {
        LaunchedEffect(Unit) {
            if (eventId != null) {
                viewModel.getEvent(eventId)

                id = eventId
                title = viewModel.event.title
                address = viewModel.event.address
                date = viewModel.event.date
                description = viewModel.event.description

            }
        }
    }

    val focusManager = LocalFocusManager.current

    ManageEventTopBar(
        navController = navController,
        addingEvent = addingEvent
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    focusManager.clearFocus()
                })
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Column {

            TextField(
                value = title,
                onValueChange = { title = it },
                placeholder = {
                    Text(
                        text = EVENT_TITLE
                    )
                },
            )
            
            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = address,
                onValueChange = { address = it },
                placeholder = {
                    Text(
                        text = ADDRESS
                    )
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = date,
                onValueChange = { date = it },
                placeholder = {
                    Text(
                        text = DATE
                    )
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = description,
                onValueChange = { description = it },
                placeholder = {
                    Text(
                        text = DESCRIPTION
                    )
                }
            )

            Button(
                onClick = {
                    val event = Event(id, title, address, date, description, false)
                    if (addingEvent) {
                        viewModel.addEvent(event)
                    } else {
                        viewModel.updateEvent(event)
                    }
                    navController.popBackStack()

                }
            ) {
                if (addingEvent) {
                    Text(text = ADD)
                } else {
                    Text(text = UPDATE)
                }
            }


        }
    }
}