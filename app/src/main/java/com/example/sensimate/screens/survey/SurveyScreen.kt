package com.example.sensimate.screens.survey

import android.annotation.SuppressLint
import android.view.Window
import android.view.WindowId
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.sensimate.navigation.BottomBarScreen
import com.example.sensimate.screens.survey.components.*

//TODO: Add dropdown menu for question picking question type
// and a delete question button
// bottom bar should be as bottom bar, and questions should be able to scale
// add images and videos
@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun SurveyScreen(
    navController: NavController,
    viewModel: SurveyViewModel = hiltViewModel(),
    eventId: String,

    ) {

    val userData by viewModel.userData


    LaunchedEffect(Unit) { viewModel.initialize(eventId) }

    val event by viewModel.event
    val surveyState by viewModel.surveyState


    if (surveyState.questionsStates.isNotEmpty()) {

        val questionState = remember(
            surveyState.currentQuestionIndex,
            surveyState, surveyState.questionsStates
        )
        { surveyState.questionsStates[surveyState.currentQuestionIndex] }

        //TODO scrollable doesnt fricking work
        val focusManager = LocalFocusManager.current

        Scaffold(

            topBar = {
                SurveyTopBar(
                    navController = navController,
                    eventTitle = event.title,
                    onPressEditButton = { viewModel.onPressEditButton() },
                    editMode = viewModel.editMode,
                    questionIndex = questionState.questionIndex,
                    totalQuestionsCount = surveyState.questionsStates.size,
                    onPressDoneEditButton = { viewModel.onDoneEditing() },
                    userData = userData
                )
            },
            content = { contentPadding ->

                Column(
                    modifier = Modifier
                        .padding(contentPadding)
                        .fillMaxSize()
                        .background(Color.LightGray)

//                    .pointerInput(Unit) {
//                        detectTapGestures(onTap = {
//                            focusManager.clearFocus()
//                        }) }
                    ,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Top,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 20.dp)
                            .background(Color.LightGray)
                            .verticalScroll(rememberScrollState())
//                            .pointerInput(Unit) {
//                                detectTapGestures(onTap = {
//                                focusManager.clearFocus()
//                            }) }
                    ) {
                        Spacer(modifier = Modifier.padding(4.dp))

                        QuestionProgressBar(
                            questionState.questionIndex,
                            surveyState.questionsStates.size
                        )


                        Spacer(modifier = Modifier.padding(4.dp))

                        QuestionTextBox(
                            questionText = questionState.question.value.questionText,
                            editMode = viewModel.editMode,
                            onNewValue = viewModel::onQuestionTextChange
                        )

                        Spacer(modifier = Modifier.padding(8.dp))

                        QuestionAnswersBox(
                            question = questionState.question.value,
                            editMode = viewModel.editMode,
                            onNewAnswerOptionValue = viewModel::onAnswerOptionsChange
                        )

                        Spacer(modifier = Modifier.padding(8.dp))


                    }


                }

            },

            bottomBar = {


                QuestionLastNext(
                    questionState = questionState,
                    onPreviousPressed = { viewModel.decreaseCurrentQuestionIndex(questionState.questionIndex) },
                    onNextPressed = { viewModel.increaseCurrentQuestionIndex(questionState.questionIndex) },
                    onDonePressed = { navController.navigate(route = BottomBarScreen.Discover.route) },
                    editMode = viewModel.editMode,
                    onAddQuestionClick = {
                        viewModel.onAddQuestionClick(
                            currentQuestionIndex = surveyState.currentQuestionIndex
                        )
                    }
                )


            }

        )


    } else {
        Button(onClick = { navController.popBackStack() }) {

        }
    }

}

