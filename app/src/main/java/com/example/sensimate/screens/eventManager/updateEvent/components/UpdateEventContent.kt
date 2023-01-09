package com.example.sensimate.screens.eventManager.updateEvent.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.sensimate.core.Constants.Companion.ADDRESS
import com.example.sensimate.core.Constants.Companion.EVENT_TITLE
import com.example.sensimate.core.Constants.Companion.UPDATE
import com.example.sensimate.domain.model.Event

@Composable
fun UpdateEventContent(
    padding: PaddingValues,
    event: Event,
    updateTitle: (title: String) -> Unit,
    updateAddress: (address: String) -> Unit,
    updateEvent: (event: Event) -> Unit,
    navigateBack: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(padding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = event.title,
            onValueChange = { title ->
                updateTitle(title)
            },
            placeholder = {
                Text(
                    text = EVENT_TITLE
                )
            }
        )
        Spacer(
            modifier = Modifier.height(8.dp)
        )
        TextField(
            value = event.address,
            onValueChange = { address ->
                updateAddress(address)
            },
            placeholder = {
                Text(
                    text = ADDRESS
                )
            }
        )
        Button(
            onClick = {
                updateEvent(event)
                navigateBack()
            }
        ) {
            Text(
                text = UPDATE
            )
        }
    }
}