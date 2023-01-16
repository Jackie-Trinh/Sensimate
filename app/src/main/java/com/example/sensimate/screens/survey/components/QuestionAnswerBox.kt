package com.example.sensimate.screens.survey.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.sensimate.domain.model.Question
import com.example.sensimate.domain.model.UserAnswers

//change List<question>, List<useranswers> to question, UserAnswers when changed to the real code
@Composable
fun QuestionAnswerBox(
    currentPage: MutableState<Int>,
    question: List<Question>,
    userAnswers: MutableList<UserAnswers>,
    newPageChecker: MutableState<Boolean>,
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .padding(0.dp)
            .clip(RoundedCornerShape(22.dp))
            .background(Color.White)
    ) {

        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)
        ) {


            //placeholder code
            val answers = question[currentPage.value.minus(1)].answerOptions
            var answerNumber = 1
            for (item in answers) {
                QuestionCheckRow(
                    questionString = item,
                    answerNumber, userAnswers, currentPage, newPageChecker)
                answerNumber++ //add counter
            }
        }
    }
}