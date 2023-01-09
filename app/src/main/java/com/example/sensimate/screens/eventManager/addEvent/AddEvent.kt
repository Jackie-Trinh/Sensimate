package com.example.sensimate.screens.eventManager.addEvent

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sensimate.core.Constants
import com.example.sensimate.core.Constants.Companion.ADDRESS
import com.example.sensimate.core.Constants.Companion.DATE
import com.example.sensimate.core.Constants.Companion.DESCRIPTION
import com.example.sensimate.core.Constants.Companion.EVENT_TITLE
import com.example.sensimate.core.Constants.Companion.UPDATE
import com.example.sensimate.domain.model.Event
import com.example.sensimate.screens.myEvents.MyEventsViewModel
import kotlinx.coroutines.job

@Composable
fun AddEvent(
    viewModel: MyEventsViewModel = hiltViewModel(),
    navigateBack: () -> Unit
) {
    var title by remember { mutableStateOf(Constants.NO_VALUE) }
    var address by remember { mutableStateOf(Constants.NO_VALUE) }
    var date by remember { mutableStateOf(Constants.NO_VALUE) }
    var description by remember { mutableStateOf(Constants.NO_VALUE) }
    val focusRequester = remember { FocusRequester() }

    Column(
        modifier = Modifier.fillMaxSize(),
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
//                modifier = Modifier.focusRequester(focusRequester)
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
                    val event = Event(0, title, address, date, description)
                    viewModel.addEvent(event)
                    navigateBack()

                }
            ) {
                Text(
                    text = UPDATE
                )
            }


        }
    }
}