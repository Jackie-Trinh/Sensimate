package com.example.sensimate.screens.myEvents


import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.sensimate.core.composables.EventItem
import com.example.sensimate.core.composables.GradientButton
import com.example.sensimate.core.composables.SearchFunction
import com.example.sensimate.core.composables.SearchView
import com.example.sensimate.firebase_model.data.Event
import com.example.sensimate.firebase_model.data.UserData
import com.example.sensimate.ui.theme.LButton1

@OptIn(ExperimentalLifecycleComposeApi::class, ExperimentalMaterialApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MyEvents(
    navController: NavController,
    openScreen: (String) -> Unit,
    viewModel: MyEventsViewModel = hiltViewModel(),
) {

    val events = viewModel.events

    val userData by viewModel.userData

    LaunchedEffect(Unit) { viewModel.initialize() }

    val textState = remember { mutableStateOf(TextFieldValue("")) }
    val focusManager = LocalFocusManager.current //clear focus
    val filteredList = remember {
        mutableStateListOf<Event>()
    }


    Column(modifier = androidx.compose.ui.Modifier
        .fillMaxSize()
        .padding(16.dp, 10.dp, 16.dp, 10.dp)
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
            item { Spacer(modifier = Modifier.height(48.dp)) }


        }
        if (textState.value.text != "") {
            SearchFunction(events, textState, filteredList) //search function test
        }
    }
}
