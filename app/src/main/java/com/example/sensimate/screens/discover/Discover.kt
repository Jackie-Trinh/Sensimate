package com.example.sensimate.screens.discover

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.ui.Alignment
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.sensimate.domain.model.Event
import com.example.sensimate.navigation.BottomBarScreen
import com.example.sensimate.screens.myEvents.MyEventsViewModel
import com.example.sensimate.model.EventItem
import com.example.sensimate.model.SearchFunction
import com.example.sensimate.model.SearchView


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

            item {
                Button(
                    onClick = { navController.navigate(route = BottomBarScreen.EventManagerPage.route) },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray),
                    modifier = Modifier
                        .width(168.dp)
                        .height(50.dp)
                        .padding(0.dp)
                        .clip(RoundedCornerShape(22.dp))
                ) {
                    Text(
                        "Event Manager",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                    )
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


