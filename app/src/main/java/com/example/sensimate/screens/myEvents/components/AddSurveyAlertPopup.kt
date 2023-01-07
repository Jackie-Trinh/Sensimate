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
import com.example.sensimate.domain.model.Survey
import kotlinx.coroutines.job

@Composable
fun AddSurveyAlertPopup(
    openDialog: Boolean,
    closeDialog: () -> Unit,
    addSurvey: (survey: Survey) -> Unit
) {
    if (openDialog) {
        var title by remember { mutableStateOf(Constants.NO_VALUE) }
        var author by remember { mutableStateOf(Constants.NO_VALUE) }
        val focusRequester = FocusRequester()

        AlertDialog(
            onDismissRequest = closeDialog,
            title = {
                Text(
                    text = Constants.ADD_SURVEY
                )
            },
            text = {
                Column {
                    TextField(
                        value = title,
                        onValueChange = { title = it },
                        placeholder = {
                            Text(
                                text = Constants.SURVEY_TITLE
                            )
                        },
                        modifier = Modifier.focusRequester(focusRequester)
                    )
                    LaunchedEffect(Unit) {
                        coroutineContext.job.invokeOnCompletion {
                            focusRequester.requestFocus()
                        }
                    }
                    Spacer(
                        modifier = Modifier.height(16.dp)
                    )
                    TextField(
                        value = author,
                        onValueChange = { author = it },
                        placeholder = {
                            Text(
                                text = Constants.AUTHOR
                            )
                        }
                    )
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        closeDialog()
                        val survey = Survey(0, title, author)
                        addSurvey(survey)
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