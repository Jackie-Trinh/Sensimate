package com.example.sensimate.screens.discover

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.ui.Alignment
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.TextFieldValue
import androidx.navigation.NavController
import com.example.sensimate.domain.model.Event
import com.example.sensimate.model.*


@Composable
fun Discover(
    navController: NavController,
    viewModel: DiscoverViewModel = hiltViewModel(),
) {
    val events by viewModel.events.collectAsState(
        initial = emptyList()
    )


    val textState = remember { mutableStateOf(TextFieldValue("")) }
    val focusManager = LocalFocusManager.current //clear focus
    val filteredList = remember {
        mutableStateListOf<Event>()
    }


    Column(modifier = androidx.compose.ui.Modifier
        .fillMaxSize()
        .padding(16.dp,10.dp,16.dp,10.dp)
        .pointerInput(Unit) {
            detectTapGestures(onTap = {
                focusManager.clearFocus()
            })
        }) {
        Spacer(modifier = Modifier.height(10.dp))

        SearchView(textState)

        Spacer(modifier = Modifier.height(10.dp))

        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = androidx.compose.ui.Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.background),
        ) {

            item{
                GradientButton(navController = navController,
                    text = "Event Manager",
                    state = true){
                    navController.navigate(route = "EventManagerPage")
                }
            }

            item { Spacer(modifier = Modifier.height(10.dp)) }

            //events - iterate through each item
            if (textState.value.text != "") {
                for (item in filteredList) {
                    item {
                        EventItem(navController = navController, event = item)
                    }
                    //used as padding
                    item { Spacer(modifier = Modifier.height(10.dp)) }
                }
            } else {
                //events - iterate through each item
                for (item in events) {
                    item {
                        EventItem(navController = navController, event = item)
                    }
                    //used as padding
                    item { Spacer(modifier = Modifier.height(10.dp)) }
                }
            }
            //padding for the bot bar, to make items visible
            //used as padding
            item { Spacer(modifier = Modifier.height(10.dp)) }
            //used as padding
            item { Spacer(modifier = Modifier.height(10.dp)) }

        }
        if (textState.value.text != "") {
            SearchFunction(events, textState, filteredList) //search function test
        }
    }
}


