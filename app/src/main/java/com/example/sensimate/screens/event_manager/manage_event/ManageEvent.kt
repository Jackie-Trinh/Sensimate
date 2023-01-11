package com.example.sensimate.screens.event_manager.manage_event

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.sensimate.R
import com.example.sensimate.core.Constants
import com.example.sensimate.core.Constants.Companion.ADD
import com.example.sensimate.core.Constants.Companion.ADDRESS
import com.example.sensimate.core.Constants.Companion.ADD_SURVEY
import com.example.sensimate.core.Constants.Companion.DATE
import com.example.sensimate.core.Constants.Companion.DELETE
import com.example.sensimate.core.Constants.Companion.DESCRIPTION
import com.example.sensimate.core.Constants.Companion.EVENT_TITLE
import com.example.sensimate.core.Constants.Companion.UPDATE
import com.example.sensimate.domain.model.Event
import com.example.sensimate.navigation.BottomBarScreen
import com.example.sensimate.screens.event_manager.EventManagerViewModel
import com.example.sensimate.screens.event_manager.manage_event.components.ManageEventTopBar

@Composable
fun ManageEvent(
    viewModel: EventManagerViewModel = hiltViewModel(),
    navController: NavController,
    eventId: Int?,
    addingEvent: Boolean = false,
) {
    var id by remember { mutableStateOf(0) }
    var title by remember { mutableStateOf(Constants.NO_VALUE) }
    var address by remember { mutableStateOf(Constants.NO_VALUE) }
    var date by remember { mutableStateOf(Constants.NO_VALUE) }
    var description by remember { mutableStateOf(Constants.NO_VALUE) }
    var hasSurvey by remember { mutableStateOf(false) }
    var numberOfQuestions by remember { mutableStateOf(0) }


    if (!addingEvent) {
        if (eventId != null) {
            LaunchedEffect(Unit) {
                viewModel.getEvent(eventId)
            }

        id = eventId
        title = viewModel.event.title
        address = viewModel.event.address
        date = viewModel.event.date
        description = viewModel.event.description
        hasSurvey = viewModel.event.hasSurvey
        numberOfQuestions = viewModel.event.numberOfQuestions

    }
}




    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    focusManager.clearFocus()
                })
            },
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        ManageEventTopBar(
            navController = navController,
            addingEvent = addingEvent
        )


        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            ){

            Spacer(modifier = Modifier.height(16.dp))

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

            Spacer(modifier = Modifier.height(16.dp))

            Row(modifier = Modifier
                .width(280.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement  =  Arrangement.SpaceBetween
            ) {

                //Update and add button
                Button(
                    onClick = {

                        if (addingEvent) { //Is adding event
                            val event = Event(id, title, address, date, description, false, 0)
                            viewModel.addEvent(event)

                        } else { //Is updating event
                            val event = Event(id, title, address, date, description, hasSurvey, numberOfQuestions)
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

                if (!addingEvent) {

                    //Add Survey button
                    Button(

                        onClick = {
                            navController.navigate("${BottomBarScreen.ManageSurveyPage.route}/${eventId}")
                        }

                    ) {
                        Text(text = ADD_SURVEY, color = Color.Black)
                    }

                    //Delete button
                    Button(

                        onClick = {
                            val event = Event(id, title, address, date, description, hasSurvey, numberOfQuestions)
                            viewModel.deleteEvent(event = event)
                            navController.navigate(BottomBarScreen.EventManagerPage.route)
                        }

                    ) {
                        Text(text = DELETE, color = Color.Red)

                    }
                }
            }


        }


    }
}
