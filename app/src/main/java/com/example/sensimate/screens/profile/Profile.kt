package com.example.sensimate.screens.profile

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.sensimate.data.User
import com.example.sensimate.model.EventCardSelection
import com.example.sensimate.model.ProfileCard

@Composable
fun Profile(navController: NavController, profileViewModel: ProfileViewModel) {
    var visible by remember {
        mutableStateOf(true)
    }
    val colorOn = Color.Magenta
    val colorOff = Color.Gray

    //test data for profile card
    val user = User(1,"SebastianIversen@hotmail.com","20","Male","6400")

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

    Column(modifier = Modifier.fillMaxSize()) {
        ProfileCard(user = user)

        //buttons
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(70.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(40.dp, 5.dp)
        ) {
            if (visible){
                item {
                    Button(onClick = {visible = !visible},
                        colors = ButtonDefaults.buttonColors(backgroundColor = colorOn)) {
                        Text("My events")
                    }
                }
                item {
                    Button(onClick = {visible = !visible},
                        colors = ButtonDefaults.buttonColors(backgroundColor = colorOff)) {
                        Text("Event history")
                    }
                }
            }else{
                item {
                    Button(onClick = {visible = !visible},
                        colors = ButtonDefaults.buttonColors(backgroundColor = colorOff)) {
                        Text("My events")
                    }
                }
                item {
                    Button(onClick = {visible = !visible},
                        colors = ButtonDefaults.buttonColors(backgroundColor = colorOn)) {
                        Text("Event history")
                    }
                }
            }
        }

        //show profile or not based on buttons
        if (visible) {
            EventCardSelection(navController, events)
        }else{
            EventCardSelection(navController, events)
        }
    }
}

