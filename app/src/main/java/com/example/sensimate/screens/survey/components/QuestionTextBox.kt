package com.example.sensimate.screens.survey.components

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

//change List<question> to question when changed to the real code
@Composable
fun QuestionTextBox(
    questionText: String,
    editMode: Boolean,
    onNewValue: (String) -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp)
            .heightIn(0.dp, 232.dp)
            .clip(RoundedCornerShape(22.dp))
            .background(Color.White)
            .scrollable(rememberScrollState(), orientation = Orientation.Vertical)
    ) {
        if (!editMode) {
            Text(
                text = questionText,
                fontSize = 18.sp,
                textAlign = TextAlign.Left,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
            )
        } else {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                textStyle = TextStyle.Default.copy(fontSize = 18.sp),
                singleLine = false,
                value = questionText,
                onValueChange = { onNewValue(it) },
                placeholder = { Text("Question text") }
            )

        }
    }
}