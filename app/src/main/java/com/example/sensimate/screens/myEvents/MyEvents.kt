package com.example.sensimate.screens.myEvents


import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.sensimate.model.EventCardSelection
import com.example.sensimate.screens.myEvents.components.AddSurveyAlertPopup
import com.example.sensimate.screens.myEvents.components.AddSurveyButton

@Composable
fun MyEvents(
    navController: NavController,
    viewModel: MyEventsViewModel = hiltViewModel(),
    //navigateToUpdateSurveyScreen: (surveyId: Int) -> Unit,
) {
    val surveys by viewModel.surveys.collectAsState(
        initial = emptyList()
    )

    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        EventCardSelection(navController, surveys)

        Spacer(modifier = Modifier.padding(0.dp))

        AddSurveyAlertPopup(
            openDialog = viewModel.openDialog,
            closeDialog = { viewModel.closeDialog() },
            addSurvey = { survey -> viewModel.addSurvey(survey) }
        )

        AddSurveyButton(
            openDialog = { viewModel.openDialog() }
        )
    }




}
