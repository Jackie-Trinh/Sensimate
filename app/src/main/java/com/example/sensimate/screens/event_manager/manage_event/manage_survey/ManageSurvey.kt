package com.example.sensimate.screens.event_manager.manage_event.manage_survey

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.sensimate.core.Constants
import com.example.sensimate.domain.model.Event
import com.example.sensimate.domain.model.Question
import com.example.sensimate.model.EventItem
import com.example.sensimate.navigation.BottomBarScreen
import com.example.sensimate.screens.event_manager.EventManagerViewModel
import com.example.sensimate.screens.event_manager.manage_event.components.EventManagerTopBar
import com.example.sensimate.screens.event_manager.manage_event.manage_survey.components.AddQuestionButton
import com.example.sensimate.screens.event_manager.manage_event.manage_survey.components.QuestionItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@Composable
fun ManageSurvey(
    viewModel: EventManagerViewModel = hiltViewModel(),
    eventId: Int,
    navController: NavController,
) {
    LaunchedEffect(Unit) {
        viewModel.getEvent(eventId)
        viewModel.getQuestions(eventId)
    }

    val questions by viewModel.questions.collectAsState(initial = emptyList())

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        EventManagerTopBar(navController = navController, title = Constants.EDIT_SURVEY_SCREEN)

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

                viewModel.updateEventNumberOfQuestions(viewModel.event.numberOfQuestions + 1)
                viewModel.addQuestion(
                    Question(
                        eventId,
                        viewModel.event.numberOfQuestions,
                        "Question placeholder",
                        arrayListOf()
                    )
                )

            }
        ) {
            Text(text = Constants.ADD_QUESTION, color = Color.Black)
        }


        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.background),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        ) {

            items(
                items = questions
            ) { question ->
                QuestionItem(
                    navController = navController,
                    question = question,
                    eventId = eventId,
                )
                //used as padding
                Spacer(modifier = Modifier.height(10.dp))

            }

        }
    }
}
