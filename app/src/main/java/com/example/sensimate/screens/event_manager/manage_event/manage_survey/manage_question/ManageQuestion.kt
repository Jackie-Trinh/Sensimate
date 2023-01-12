package com.example.sensimate.screens.event_manager.manage_event.manage_survey.manage_question

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.sensimate.core.Constants
import com.example.sensimate.core.Constants.Companion.EDIT_QUESTION_SCREEN
import com.example.sensimate.domain.model.Question
import com.example.sensimate.screens.event_manager.EventManagerViewModel
import com.example.sensimate.screens.event_manager.manage_event.components.EventManagerTopBar

@Composable
fun ManageQuestion(
    viewModel: EventManagerViewModel = hiltViewModel(),
    navController: NavController,
    eventId: Int,
    questionNumber: Int,
) {
    LaunchedEffect(Unit) {
        viewModel.getEvent(eventId)
        viewModel.getQuestion(eventId, questionNumber)
    }

    val focusManager = LocalFocusManager.current

    var questionText by remember { mutableStateOf(Constants.NO_VALUE) }
//    var answerOptions = remember(emptyList<String>()) { mutableStateListOf<String>() }
    var answerOptions = remember { mutableStateListOf<String>()}

    val eventId = viewModel.question.id
    val questionNumber = viewModel.question.questionNumber
    questionText = viewModel.question.questionText
//    val answerOptions = remember(viewModel.question.answerOptions)  { mutableStateListOf<String>() }

//    viewModel.question.answerOptions.forEach{
//        var temp = temp
//        answerOptions[temp] = it
//    }

    if(answerOptions.isEmpty()){
        answerOptions.addAll(viewModel.question.answerOptions)
    }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    focusManager.clearFocus()
                })
            },
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        EventManagerTopBar(
            navController = navController,
            title = EDIT_QUESTION_SCREEN
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Question number: $questionNumber")

        Spacer(modifier = Modifier.height(16.dp))

        TextField(modifier = Modifier
            .height(64.dp),
            value = questionText,
            onValueChange = { questionText = it },
            placeholder = {
                Text(
                    text = Constants.EVENT_TITLE,
                    fontSize = 16.sp
                )
            },
        )

        Button(
            onClick = { viewModel.updateQuestion(
                Question(
                eventId,
                questionNumber,
                questionText,
                answerOptions.toMutableList() as ArrayList<String>,
                ))
                navController.popBackStack()
            }
        ) {
            Text("Update")
        }



        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            itemsIndexed(
                items = answerOptions
            ) { index, option ->
                var optionTemp = option
                TextField(
                    value = optionTemp,
                    onValueChange = {
                        optionTemp = it
                        answerOptions[index] to it
                                    },
                    placeholder = {
                        Text(
                            text = Constants.ANSWER_OPTION,
                            fontSize = 16.sp
                        )
                    },
                )
                //used as padding
                Spacer(modifier = Modifier.height(10.dp))

            }

            item {
                IconButton(
                    onClick = {
                        answerOptions.add("Answer option")
                        //TODO: update instant when adding answer
//                        viewModel.updateQuestionAnswerOptions(answerOptions.toMutableList() as ArrayList<String>)
                              },
                ) {
                    Icon(
                        Icons.Outlined.AddCircle,
                        "Add question",
                        tint = Color.Gray
                    )
                }
            }


        }

    }

}

