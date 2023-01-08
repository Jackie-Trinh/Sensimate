package com.example.sensimate.screens.discover.components

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
import androidx.compose.ui.unit.dp
import com.example.sensimate.core.Constants.Companion.ADD
import com.example.sensimate.core.Constants.Companion.ADD_EVENT
import com.example.sensimate.core.Constants.Companion.ADDRESS
import com.example.sensimate.core.Constants.Companion.DISMISS
import com.example.sensimate.core.Constants.Companion.NO_VALUE
import com.example.sensimate.core.Constants.Companion.EVENT_TITLE
import com.example.sensimate.domain.model.Event
import kotlinx.coroutines.job

@Composable
fun AddSurveyAlertDialog(
    openDialog: Boolean,
    closeDialog: () -> Unit,
    addSurvey: (event: Event) -> Unit
) {
    if (openDialog) {
        var title by remember { mutableStateOf(NO_VALUE) }
        var address by remember { mutableStateOf(NO_VALUE) }
        var date by remember { mutableStateOf(NO_VALUE) }
        var description by remember { mutableStateOf(NO_VALUE) }
        val focusRequester = FocusRequester()

        AlertDialog(
            onDismissRequest = closeDialog,
            title = {
                Text(
                    text = ADD_EVENT
                )
            },
            text = {
                Column {
                    TextField(
                        value = title,
                        onValueChange = { title = it },
                        placeholder = {
                            Text(
                                text = EVENT_TITLE
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
                                text = ADDRESS
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
                        addSurvey(event)
                    }
                ) {
                    Text(
                        text = ADD
                    )
                }
            },
            dismissButton = {
                TextButton(
                    onClick = closeDialog
                ) {
                    Text(
                        text = DISMISS
                    )
                }
            }
        )
    }
}