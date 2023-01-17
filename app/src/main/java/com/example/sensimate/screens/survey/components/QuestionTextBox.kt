package com.example.sensimate.screens.survey.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import com.example.sensimate.model2.Question

//change List<question> to question when changed to the real code
@Composable
fun QuestionTextBox(questionText: String) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 110.dp)
            .padding(0.dp)
            .clip(RoundedCornerShape(22.dp))
            .background(Color.White)
    )
    {
        Text(
            text = questionText,
            fontSize = 20.sp,
            textAlign = TextAlign.Left,
            modifier = Modifier.padding(horizontal = 22.dp, vertical = 12.dp)
        )

    }

}