package com.example.sensimate.screens.eventManager.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.sensimate.navigation.BottomBarScreen


@Composable
fun AddEventButton(
    navController: NavController,
) {

    Button(
        onClick = { navController.navigate(route = BottomBarScreen.ManageEventPage.route) },
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray),
        modifier = Modifier
            .width(168.dp)
            .height(50.dp)
            .padding(0.dp)
            .clip(RoundedCornerShape(22.dp))
    ) {
        Text(
            "Tilføj Event",
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
        )
    }

}