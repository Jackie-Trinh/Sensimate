package com.example.sensimate.screens.myEvents.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.sensimate.core.Constants
import com.example.sensimate.domain.model.Event
import kotlinx.coroutines.job

@Composable
fun AddEventAlertPopup(
    openDialog: Boolean,
    closeDialog: () -> Unit,
    addEvent: (event: Event) -> Unit
) {
    if (openDialog) {
        var title by remember { mutableStateOf(Constants.NO_VALUE) }
        var address by remember { mutableStateOf(Constants.NO_VALUE) }
        var date by remember { mutableStateOf(Constants.NO_VALUE) }
        var description by remember { mutableStateOf(Constants.NO_VALUE) }
        val focusRequester = FocusRequester()

        AlertDialog(
            onDismissRequest = closeDialog,
            title = {
                Text(
                    text = Constants.ADD_EVENT
                )
            },
            text = {
                Column {
                    TextField(
                        value = title,
                        onValueChange = { title = it },
                        placeholder = {
                            Text(
                                text = Constants.EVENT_TITLE
                            )
                        },
                        modifier = Modifier.focusRequester(focusRequester)
                    )

                    LaunchedEffect(Unit) {
                        coroutineContext.job.invokeOnCompletion {
                            focusRequester.requestFocus()
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    TextField(
                        value = address,
                        onValueChange = { address = it },
                        placeholder = {
                            Text(
                                text = Constants.ADDRESS
                            )
                        }
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    TextField(
                        value = date,
                        onValueChange = { date = it },
                        placeholder = {
                            Text(
                                text = Constants.DATE
                            )
                        }
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    TextField(
                        value = description,
                        onValueChange = { description = it },
                        placeholder = {
                            Text(
                                text = Constants.DESCRIPTION
                            )
                        }
                    )

                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        closeDialog()
                        val event = Event(0, title, address, date, description)
                        addEvent(event)
                    }
                ) {
                    Text(
                        text = Constants.ADD,
                        color = Color.Gray
                    )
                }
            },
            dismissButton = {
                TextButton(
                    onClick = closeDialog
                ) {
                    Text(
                        text = Constants.DISMISS,
                        color = Color.Gray
                    )
                }
            }
        )
    }
}