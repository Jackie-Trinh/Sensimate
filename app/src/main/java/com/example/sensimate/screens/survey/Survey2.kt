package com.example.sensimate.screens.survey
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.navigation.NavController
import com.example.sensimate.core.Constants
import com.example.sensimate.domain.model.*
import com.example.sensimate.screens.survey.components.*

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun Survey2(
    navController: NavController,
    viewModel: SurveyViewModel = hiltViewModel(),
    eventId: String,
) {
//    val event by viewModel.event
//    val questions = viewModel.questions
//
//    LaunchedEffect(Unit) { viewModel.initialize(eventId) }
//
//    if(event.eventId != Constants.EVENT_DEFAULT_ID) {
//
//        //current page we are at
//        var currentPage = remember { mutableStateOf(value = 1) }
//        //new page checker
//        var newPageChecker = remember { mutableStateOf(true) }
//
//
//        Box(
//            modifier = Modifier
//                .fillMaxSize()
//                .background(Color.LightGray),
//            contentAlignment = Alignment.TopCenter
//        ) {
//
//            Column(
//                horizontalAlignment = Alignment.CenterHorizontally,
//                verticalArrangement = Arrangement.Top,
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(20.dp)
//            ) {
//
////                SetupAnswerList(userAnswers, event)
//
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
////                QuestionAnswersBox(currentPage, questions)
//
//                Spacer(modifier = Modifier.padding(8.dp))
//
//                QuestionLastNext(currentPage, event, navController, newPageChecker)
//
//            }
//        }
//    }
}

//so we can set elements in the list without it crashing
//@Composable
//fun SetupAnswerList(userAnswers: MutableList<UserAnswers>, event: Event){
//    val totalPages = event.numberOfQuestions
//    val items = userAnswers[0].answers
//
//    for (i in 0 until totalPages){
//        items.add(i,0)
//    }
//}














