package com.example.sensimate.screens.survey

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.sensimate.domain.model.*
import com.example.sensimate.screens.event_manager.EventManagerViewModel
import com.example.sensimate.screens.survey.components.*

@Composable
fun Survey(
    navController: NavController,
    eventViewModel: EventManagerViewModel = hiltViewModel(),
    viewModel: SurveyViewModel = hiltViewModel(),
    eventId: Int,
) {
    LaunchedEffect(Unit) {
        eventViewModel.getEvent(eventId)
        eventViewModel.getQuestions(eventId)
    }

    val questions by eventViewModel.questions.collectAsState(initial = emptyList())

    val event = eventViewModel.event

    if(eventViewModel.event.id!=0 && questions.isNotEmpty()) {

        //placeholder user answers for the questions
        val userAnswers = mutableListOf(
            UserAnswers(
                1,
                10,
                mutableListOf(),
                listOf(
                    UserAnswersItem(1, 10, mutableListOf())
                )
            )
        )


        //current page we are at
        var currentPage = remember { mutableStateOf(value = 1) }
        //new page checker
        var newPageChecker = remember { mutableStateOf(true) }


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

                SetupAnswerList(userAnswers, event)

                ExitQuestionBar(navController = navController, eventTitle = event.title)

                Spacer(modifier = Modifier.padding(4.dp))

                QuestionProgressBar(currentPage, event)

                Spacer(modifier = Modifier.padding(4.dp))

                QuestionTextBox(questions, currentPage)

                Spacer(modifier = Modifier.padding(8.dp))

                QuestionAnswerBox(currentPage, questions, userAnswers, newPageChecker)

                Spacer(modifier = Modifier.padding(8.dp))

                QuestionLastNext(currentPage, eventViewModel.event, navController, newPageChecker)

            }


        }
    }
}

//so we can set elements in the list without it crashing
@Composable
fun SetupAnswerList(userAnswers: MutableList<UserAnswers>, event: Event){
    val totalPages = event.numberOfQuestions
    val items = userAnswers[0].answers

    for (i in 0 until totalPages){
        items.add(i,0)
    }
}














