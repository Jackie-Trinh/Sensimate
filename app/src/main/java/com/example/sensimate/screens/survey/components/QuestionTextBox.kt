package com.example.sensimate.screens.survey.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sensimate.domain.model.Question

//change List<question> to question when changed to the real code
@Composable
fun QuestionTextBox(question: List<Question>, currentPage: MutableState<Int>) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .padding(0.dp)
            .clip(RoundedCornerShape(22.dp))
            .background(Color.White)
    )
    {
        //placeholder code
        Text(
            text = question[currentPage.value.minus(1)].questionText,
            fontSize = 18.sp,
            textAlign = TextAlign.Left,
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp)
        )

        /*
        //real code
        Text(
            text =  question.questionText,
            fontSize = 18.sp,
            textAlign = TextAlign.Left,
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp)
        )
         */

    }

}