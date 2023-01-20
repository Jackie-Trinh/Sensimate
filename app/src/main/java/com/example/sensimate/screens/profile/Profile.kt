package com.example.sensimate.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.sensimate.core.composables.GradientButton
import com.example.sensimate.core.composables.ProfileCard
import com.example.sensimate.navigation.BottomBarScreen

@Composable
fun Profile(
    navController: NavController,
    viewModel: ProfileViewModel = hiltViewModel(),
) {

    val userData by viewModel.userData

    LaunchedEffect(Unit) { viewModel.initialize() }


    var visible by remember {
        mutableStateOf(true)
    }
    val colorOn = MaterialTheme.colors.surface
    val colorOff = MaterialTheme.colors.surface


    Column(modifier = Modifier.fillMaxSize()) {
        ProfileCard(userData = userData)



        //show profile or not based on buttons
        if (visible) {
            Column(horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = MaterialTheme.colors.background)
                    .padding(16.dp, 20.dp, 16.dp, 8.dp)
                    //.shadow(10.dp, shape = RoundedCornerShape(0.dp, 0.dp, 20.dp, 20.dp))
            )
            {
                //buttons
                Row(
                    horizontalArrangement = Arrangement.spacedBy(70.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(0.dp, 5.dp, 0.dp, 0.dp)
                ) {
                        Button(modifier = Modifier
                            //.alpha(0.65f)
                            .shadow(10.dp, shape = RoundedCornerShape(20.dp, 20.dp, 0.dp, 0.dp)),
                            shape = RoundedCornerShape(20.dp,20.dp,0.dp,0.dp),
                            contentPadding = PaddingValues(30.dp,20.dp),
                            onClick = {visible = !visible},
                            colors = ButtonDefaults.buttonColors(backgroundColor = colorOn)) {
                            Text(text = "My events")
                        }
                        Spacer(Modifier.weight(1f))

                        Button(modifier = Modifier
                            .alpha(0.65f)
                            //.shadow(11.dp, shape = RoundedCornerShape(20.dp, 20.dp, 0.dp, 0.dp))
                            ,
                            shape = RoundedCornerShape(20.dp,20.dp,0.dp,0.dp),
                            contentPadding = PaddingValues(22.dp,20.dp),
                            onClick = {visible = !visible},
                            colors = ButtonDefaults.buttonColors(backgroundColor = colorOn)) {
                            Text("Event history")
                        }
                }

                LazyColumn(modifier = Modifier
                    .fillMaxWidth()
                    .height(320.dp)
                    .background(color = MaterialTheme.colors.surface,
                        shape = RoundedCornerShape(0.dp,0.dp,20.dp,20.dp))
                    .shadow(0.dp, shape = RoundedCornerShape(0.dp, 0.dp, 20.dp, 20.dp))
                    .padding(20.dp,20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    item { Text(text = "hello this is a test for 1") }
                }
            }
        }else{
            Column(horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = MaterialTheme.colors.background)
                    .padding(16.dp, 20.dp, 16.dp, 8.dp)
                    //.shadow(10.dp, shape = RoundedCornerShape(0.dp, 0.dp, 20.dp, 20.dp))
            )
            {
                //buttons
                Row(
                    horizontalArrangement = Arrangement.spacedBy(70.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(0.dp, 5.dp, 0.dp, 0.dp)
                ) {
                    Button(modifier = Modifier
                        .alpha(0.65f)
                        //.shadow(11.dp, shape = RoundedCornerShape(20.dp, 20.dp, 0.dp, 0.dp))
                        ,
                        shape = RoundedCornerShape(20.dp,20.dp,0.dp,0.dp),
                        contentPadding = PaddingValues(30.dp,20.dp),
                        onClick = {visible = !visible},
                        colors = ButtonDefaults.buttonColors(backgroundColor = colorOn)) {
                        Text(text = "My events")
                    }
                    Spacer(Modifier.weight(1f))

                    Button(modifier = Modifier
                        //.alpha(0.65f)
                        .shadow(11.dp, shape = RoundedCornerShape(20.dp, 20.dp, 0.dp, 0.dp))
                        ,
                        shape = RoundedCornerShape(20.dp,20.dp,0.dp,0.dp),
                        contentPadding = PaddingValues(22.dp,20.dp),
                        onClick = {visible = !visible},
                        colors = ButtonDefaults.buttonColors(backgroundColor = colorOn)) {
                        Text("Event history")
                    }
                }

                LazyColumn(modifier = Modifier
                    .fillMaxWidth()
                    .height(320.dp)
                    .background(color = MaterialTheme.colors.surface,
                        shape = RoundedCornerShape(0.dp,0.dp,20.dp,20.dp))
                    .padding(20.dp,20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    item { Text(text = "hello this is a test for 2") }
                    item { Text(text = "hello this is a test for 2") }
                    item { Spacer(modifier = Modifier.height(500.dp)) }
                    item { Text(text = "hello this is a test for 2") }
                    item { Spacer(modifier = Modifier.height(20.dp)) }
                    item { GradientButton(navController = navController,
                        text = "See more", state = true) {
                        navController.navigate(route = BottomBarScreen.MyEvents.route)
                    } }
                    item { Spacer(modifier = Modifier.height(40.dp)) }
                }
            }
        }
    }
}

