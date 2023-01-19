package com.example.sensimate.screens.event_manager

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.sensimate.core.composables.EventItem
import com.example.sensimate.model.EventCardSelection
import com.example.sensimate.screens.myEvents.MyEventsViewModel
import com.example.sensimate.ui.theme.LButton1
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.example.sensimate.screens.event_manager.components.AddEventButton

@OptIn(ExperimentalLifecycleComposeApi::class, ExperimentalMaterialApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun EventManager(
    navController: NavController,
    openScreen: (String) -> Unit,
    viewModel: MyEventsViewModel = hiltViewModel(),
) {

    val events = viewModel.events.collectAsStateWithLifecycle(emptyList())
    val options by viewModel.options

    Scaffold(
        bottomBar = { BottomNavigation() {

        } },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { viewModel.onAddClick(openScreen) },
                backgroundColor = LButton1,
                contentColor = MaterialTheme.colors.primary,
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(Icons.Filled.Add, "Add")
            }
        }
    ) {

        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()) {
//            ActionToolbar(
//                title = AppText.tasks,
//                modifier = Modifier.toolbarActions(),
//                endActionIcon = AppIcon.ic_settings,
//                endAction = { viewModel.onSettingsClick(openScreen) }
//            )

            Spacer(modifier = Modifier.height(10.dp))

            LazyColumn {
                items(items = events.value
                ) { eventItem ->
                    EventItem(
                        event = eventItem,
                        options = options,
                        onEventClick = { viewModel.onEventClick(openScreen, eventItem) },
                        onActionClick = { action -> viewModel.onEventActionClick(openScreen, eventItem, action) }
                    )
                }
            }

        }
    }

    LaunchedEffect(viewModel) { viewModel.loadEventOptions() }
}
