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
//        if (visible) {
//            EventCardSelection(navController, events)
//        }else{
//            EventCardSelection(navController, events)
//        }
    }
}

