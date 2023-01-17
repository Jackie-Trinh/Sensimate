package com.example.sensimate.screens.survey

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.sensimate.screens.survey.components.*

@Composable
fun SurveyScreen(
    navController: NavController,
    viewModel: SurveyViewModel = hiltViewModel(),
    eventId: String,

) {
    LaunchedEffect(Unit) { viewModel.initialize(eventId) }

    val event by viewModel.event
    val questions = viewModel.questions
    val surveyState = viewModel.surveyInitialState


    val questionState = remember(surveyState.currentQuestionIndex) {
        surveyState.questionsState[surveyState.currentQuestionIndex]
    }

    Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.LightGray),
            contentAlignment = Alignment.TopCenter
        ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)
            ) {
                Text(text = questionState.question.questionText)


//                SetupAnswerList(userAnswers, event)

//                ExitQuestionBar(navController = navController, eventTitle = event.title,
//                    onPressEditButton = { viewModel.onPressEditButton() },
//                    editMode = viewModel.editMode.value
//                )
//
//                Spacer(modifier = Modifier.padding(4.dp))
//
//                QuestionProgressBar(viewModel.currentPage, questions)
//
//                Spacer(modifier = Modifier.padding(4.dp))
//
//                QuestionTextBox(questions, viewModel.currentPage)
//
//                Spacer(modifier = Modifier.padding(8.dp))
//
//                QuestionAnswersBox(currentPage, questions)
//
//                Spacer(modifier = Modifier.padding(8.dp))
//
//                QuestionLastNext(currentPage, event, navController, newPageChecker)

            }
        }

}