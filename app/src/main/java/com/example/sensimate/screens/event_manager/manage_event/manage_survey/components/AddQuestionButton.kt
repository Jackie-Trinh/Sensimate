package com.example.sensimate.screens.event_manager.manage_event.manage_survey.components

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.sensimate.core.Constants
import com.example.sensimate.navigation.BottomBarScreen

@Composable
fun AddQuestionButton(
    navController: NavController,
    eventId: Int,
) {

    Button(
        onClick = {
            navController.navigate("${BottomBarScreen.ManageSurveyPage.route}/${eventId}")
        }

    ) {
        Text(text = Constants.ADD_QUESTION, color = Color.Black)
    }

}