package com.example.sensimate.screens.discover

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.sensimate.domain.model.Event
import com.example.sensimate.model.EventCardSelection
import com.example.sensimate.navigation.BottomBarScreen
import com.example.sensimate.screens.myEvents.MyEventsViewModel


@Composable
fun Discover(
    navController: NavController,
    viewModel: DiscoverViewModel = hiltViewModel(),

) {
    val events by viewModel.events.collectAsState(
        initial = emptyList()
    )

    Column(horizontalAlignment = Alignment.CenterHorizontally

    ) {

        Spacer(modifier = Modifier.height(10.dp))

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

        EventCardSelection(navController, events)
    }




}

