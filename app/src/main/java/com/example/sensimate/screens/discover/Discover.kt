package com.example.sensimate.screens.discover

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.sensimate.data.Event
import com.example.sensimate.model.EventItem
import com.example.sensimate.model.SearchFunction
import com.example.sensimate.model.SearchView


@Composable
fun Discover(navController: NavController, discoverViewModel: DiscoverViewModel) {
    //text data for user events
    val events = listOf(
        com.example.sensimate.data.Event(
            3,
            "Event 1",
            "Desc 1",
            "06-01-2023",
            "H.C Andersens vej 3",
            listOf(
                com.example.sensimate.data.EventItem(
                    1,
                    "item 1",
                    "desc 1",
                    "06-01-2023",
                    "H.C Andersens vej 3"
                )
            )
        ),
        com.example.sensimate.data.Event(
            2,
            "Event 2",
            "Desc 2",
            "06-01-2023",
            "H.C Andersens vej 3",
            listOf(
                com.example.sensimate.data.EventItem(
                    2,
                    "item 2",
                    "desc 2",
                    "06-01-2023",
                    "H.C Andersens vej 3"
                )
            )
        ),
        com.example.sensimate.data.Event(
            3,
            "Event 3",
            "Desc 3",
            "06-01-2023",
            "H.C Andersens vej 3",
            listOf(
                com.example.sensimate.data.EventItem(
                    3,
                    "item 3",
                    "desc 3",
                    "06-01-2023",
                    "H.C Andersens vej 3"
                )
            )
        )
    )
    val textState = remember { mutableStateOf(TextFieldValue("")) }
    val focusManager = LocalFocusManager.current //clear focus
    val filteredList = remember {
        mutableStateListOf<Event>()
    }


    Column(modifier = androidx.compose.ui.Modifier
        .fillMaxSize()
        .pointerInput(Unit) {
            detectTapGestures(onTap = {
                focusManager.clearFocus()
            })
        }) {
        SearchView(textState)

        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = androidx.compose.ui.Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.background),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        ) {

            //events - iterate through each item
            if (textState.value.text != "") {
                for (item in filteredList) {
                item {
                    EventItem(navController = navController, event = item)
                }
                    //used as padding
                    item { Text(text = "") }
                }
            }else {
                //events - iterate through each item
                for (item in events) {
                    item {
                        EventItem(navController = navController, event = item)
                    }
                    //used as padding
                    item { Text(text = "") }
                }
            }
            //padding for the bot bar, to make items visible
            //used as padding
            item { Text(text = "")  }
            //used as padding
            item { Text(text = "")  }

        }
        if (textState.value.text != "") {
            SearchFunction(events, textState, filteredList) //search function test
        }
    }
}

