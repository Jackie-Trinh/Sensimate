package com.example.sensimate.screens.event_manager.manage_survey

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.sensimate.core.Constants
import com.example.sensimate.domain.model.Event
import com.example.sensimate.navigation.BottomBarScreen
import com.example.sensimate.screens.event_manager.EventManagerViewModel
import com.example.sensimate.screens.event_manager.manage_survey.components.AddQuestionButton
import com.example.sensimate.screens.event_manager.manage_survey.components.ManageSurveyTopBar
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@Composable
fun ManageSurvey(
    viewModel: EventManagerViewModel = hiltViewModel(),
    navController: NavController,
    eventId: Int,
) {
    viewModel.getEvent(eventId)

//    //TODO: only placeholder variables:
//    var title = viewModel.event.title
//    var address = viewModel.event.address
//    var date = viewModel.event.date
//    var description = viewModel.event.description

    //TODO: Can't move Topbar and text out of lazy column
//    Column(
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//
//
//        ManageSurveyTopBar(navController = navController)
//
//        Text(
//            text = viewModel.event.title,
//            fontSize = 40.sp,
//        )
//        Text(
//            text = "Survey",
//            fontSize = 20.sp,
//        )



        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.background),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        ) {

            item {
                ManageSurveyTopBar(navController = navController)

                Text(
                    text = viewModel.event.title,
                    fontSize = 40.sp,
                )
                Text(
                    text = "Survey",
                    fontSize = 20.sp,
                )

                Button(
                    onClick = {
                        navController.navigate("${BottomBarScreen.ManageSurveyPage.route}/${eventId}")
                    }
                ) {
                    Text(text = Constants.ADD_QUESTION, color = Color.Black)
                }

            }

            item {

            }

        }

    }

