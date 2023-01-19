package com.example.sensimate.screens.survey.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.ProgressIndicatorDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

//change List<survey> to survey when changed to the real code
@Composable
fun QuestionProgressBar
            (
    questionIndex: Int,
    totalQuestionsCount: Int,
) {

    var progress by remember { mutableStateOf(value = 0.00f) }
    progress -= progress //reset progress, so we don't overlap the counter



    //placeholder code
    val questionTotal = totalQuestionsCount.toFloat().plus(0.0) //number of questions
    val currentNumber = questionIndex.toFloat().plus(1.0) //current question number
    //calculate the percentage and make it a float
    val sum = currentNumber.div(questionTotal)
    val sumTimes = sum.times(100)
    for (i in 0 until sumTimes.toInt()) {
        progress += 0.01f
    }

    //TODO: put this in viewModel


    //added smooth animation to increase and decrease
    val animatedProgress = animateFloatAsState(
        targetValue = progress,
        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec
    ).value

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(6.dp)
    ) {
        LinearProgressIndicator(
            progress = animatedProgress,
            modifier = Modifier
                .fillMaxWidth()
                .height(12.dp)
                .clip(RoundedCornerShape(25.dp)),
            backgroundColor = Color.White,
            color = Color.Black
        )
    }
}
