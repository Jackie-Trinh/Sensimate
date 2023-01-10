package com.example.sensimate.screens.event_manager.manage_event.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.sensimate.core.Constants

@Composable
fun ManageEventTopBar(
    navController: NavController,
    addingEvent: Boolean
) {
    TopAppBar (
        title = {
            if (addingEvent){
                Text(text = Constants.ADD_EVENT_SCREEN)
            } else {
                Text(text = Constants.UPDATE_EVENT_SCREEN)
            }
        },
        navigationIcon = {
            IconButton(
                onClick = {
                    navController.popBackStack()
                }
            ) {
                Icon(
                    imageVector = Icons.Outlined.ArrowBack,
                    contentDescription = null,
                )
            }
        }
    )
}