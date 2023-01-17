package com.example.sensimate.screens.survey.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sensimate.domain.model.UserAnswers

//change List<useranswer> to Useranswer when changed to the real code
@Composable
fun QuestionCheckRow2(
    questionString: String,
//    userAnswers: MutableList<UserAnswers>,
//    currentPage: MutableState<Int>,
//    newPageChecker: MutableState<Boolean>,
) {
//    val markCheck = userAnswers[0].answers[currentPage.value.minus(1)]
//    val totalQuestion = userAnswers[0].answers.size

//    val checkedState = remember { mutableStateOf(false) }

    //check if new page
//    if (newPageChecker.value){
//        CheckedState( checkedState,markCheck, answerNumber)
//    }

    //check if all items have been checked after a new page
//    if (answerNumber == totalQuestion){
//        newPageChecker.value = false
//    }

    //placeholder code
//    val items = userAnswers[0].answers
//    if (checkedState.value) {
//        items[currentPage.value.minus(1)] = answerNumber
//    }

    //boolean for checkbox delete
//    val checkBoxBool = items[currentPage.value.minus(1)] == 0

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(0.dp)
    ) {
//        Checkbox(
//            checked = checkedState.value,
//            onCheckedChange = { checkedState.value = it
//                if(checkBoxBool){
//                    items[currentPage.value.minus(1)] = 0
//                }else{
//                    items[currentPage.value.minus(1)] = 0
//                }
//            },
//        )

        Text(
            questionString,
            fontSize = 18.sp,
            textAlign = TextAlign.Left,
            modifier = Modifier.padding(horizontal = 0.dp, vertical = 0.dp)
        )
    }
}