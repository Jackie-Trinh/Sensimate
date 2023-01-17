package com.example.sensimate.screens.survey.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Checkbox
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sensimate.model2.Question

//change List<question>, List<useranswers> to question, UserAnswers when changed to the real code
@Composable
fun QuestionAnswersBox(
    question: Question,
//    onAnswer: (Answer<*>) -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp)
            .clip(RoundedCornerShape(22.dp))
            .background(Color.White)
    ) {

        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .padding(15.dp)
        ) {

            if (question.questionType == "Single choice") {
                SingleChoiceQuestion(question.answerOptions)
            } else if (question.questionType == "Multiple choice") {
                MultipleChoiceQuestion(question.answerOptions)
            }


//            val answers = question[currentPage.value.minus(1)]?.answerOptions
//            if (answers != null) {
//                for (item in answers) {
//                    QuestionCheckRow2(
//                        item,
////                        answerNumber, userAnswers, currentPage, newPageChecker
//                                        )
//
//                }
//            }

            //placeholder code
//            val answers = question[currentPage.value.minus(1)]?.answerOptions
//            var answerNumber = 1
//            if (answers != null) {
//                for (item in answers) {
//                    QuestionCheckRow(
//                        questionString = item,
//                        answerNumber, userAnswers, currentPage, newPageChecker)
//                    answerNumber++ //add counter
//                }
//            }
        }
    }
}

@Composable
fun SingleChoiceQuestion(
    answerOptions: List<String>,
//onAnswerSelected: (Int) -> Unit,
) {

    val (selectedOption, onOptionSelected) = remember { mutableStateOf("") }

    Column(Modifier.selectableGroup()) {
        answerOptions.forEach { text ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .selectable(
                        selected = (text == selectedOption),
                        onClick = { onOptionSelected(text) },
                        role = Role.RadioButton
                    )
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (text == selectedOption/* && selectedOption != ""*/),
                    onClick = null // null recommended for accessibility with screenreaders
                )
                Text(
                    text,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Left,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }
    }
}

@Composable
fun MultipleChoiceQuestion(
    answerOptions: List<String>,
//onAnswerSelected: (Int) -> Unit,
) {

    Column(Modifier.selectableGroup()) {
        answerOptions.forEach { text ->
            val (checkedState, onStateChange) = remember { mutableStateOf(false) }

            Row(
                Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .toggleable(
                        value = checkedState,
                        onValueChange = { onStateChange(!checkedState) },
                        role = Role.Checkbox
                    )
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = checkedState,
                    onCheckedChange = null // null recommended for accessibility with screenreaders
                )
                Text(
                    text,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Left,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }
    }
}
