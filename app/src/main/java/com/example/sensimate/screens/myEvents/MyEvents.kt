package com.example.sensimate.screens.myEvents


import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.sensimate.core.Constants
import com.example.sensimate.core.composables.EventItem
import com.example.sensimate.model.EventCardSelection
import com.example.sensimate.navigation.BottomBarScreen
import com.example.sensimate.screens.edit_event.components.ActionToolbar

@OptIn(ExperimentalLifecycleComposeApi::class, ExperimentalMaterialApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MyEvents(
    navController: NavController,
    openScreen: (String) -> Unit,
    viewModel: MyEventsViewModel = hiltViewModel(),
) {


    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { viewModel.onAddClick(openScreen) },
//                backgroundColor = MaterialTheme.colors.primary,
//                contentColor = MaterialTheme.colors.onPrimary,
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(Icons.Filled.Add, "Add")
            }
        }
    ) {
        val events = viewModel.events.collectAsStateWithLifecycle(emptyList())
        val options by viewModel.options

        Column(modifier = Modifier.fillMaxWidth().fillMaxHeight()) {
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
                        onActionClick = { action -> viewModel.onEventActionClick(openScreen, eventItem, action) }
                    )
                }
            }

        }
    }

    LaunchedEffect(viewModel) { viewModel.loadEventOptions() }
}
