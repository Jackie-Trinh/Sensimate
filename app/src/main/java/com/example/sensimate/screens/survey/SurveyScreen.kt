package com.example.sensimate.screens.survey

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import com.example.sensimate.navigation.BottomBarScreen
import com.example.sensimate.screens.survey.components.*

@Composable
fun SurveyScreen(
    navController: NavController,
    viewModel: SurveyViewModel = hiltViewModel(),
    eventId: String,

) {
    LaunchedEffect(Unit) { viewModel.initialize(eventId) }

    val event by viewModel.event
//    val questions = viewModel.questions
    val surveyState by viewModel.surveyState

    if(surveyState.surveyTitle!="" && surveyState.questionsStates.isNotEmpty()) {

    val questionState = remember(surveyState.currentQuestionIndex) {
        surveyState.questionsStates[surveyState.currentQuestionIndex]
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


//                SetupAnswerList(userAnswers, event)

                ExitQuestionBar(
                    navController = navController,
                    eventTitle = event.title,
                    onPressEditButton = { viewModel.onPressEditButton() },
                    editMode = viewModel.editMode.value,
                    questionIndex = questionState.questionIndex,
                    totalQuestionsCount = questionState.totalQuestionsCount,
                )

                Spacer(modifier = Modifier.padding(4.dp))

                QuestionProgressBar(questionState.questionIndex, questionState.totalQuestionsCount)

                Spacer(modifier = Modifier.padding(4.dp))

                QuestionTextBox(questionText = questionState.question.questionText)

                Spacer(modifier = Modifier.padding(8.dp))

                QuestionAnswersBox(question = questionState.question)

                Spacer(modifier = Modifier.padding(8.dp))

                QuestionLastNext(
                    questionState = questionState,
                    onPreviousPressed = { viewModel.decreaseCurrentQuestionIndex(questionState.questionIndex) },
                    onNextPressed = { viewModel.increaseCurrentQuestionIndex(questionState.questionIndex) },
                    onDonePressed = { navController.navigate(route = BottomBarScreen.Discover.route) }
                )

            }
        }
    }

}