package com.example.sensimate.screens.survey.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState

//check if we are on a new page, if yes check for mark
@Composable
fun CheckedState(
    checkedState: MutableState<Boolean>,
    markCheck: Int, answerNumber: Int,
){
    //reset checkedstate to false
    checkedState.value = false

    //checks if there are marked boxes
    checkedState.value = markCheck == answerNumber
}