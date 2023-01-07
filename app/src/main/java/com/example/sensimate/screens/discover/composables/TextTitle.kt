package com.example.sensimate.screens.discover.composables

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

@Composable
fun TextTitle(
    surveyTitle: String
) {
    Text(
        text = surveyTitle,
        color = Color.DarkGray,
        fontSize = 25.sp
    )
}