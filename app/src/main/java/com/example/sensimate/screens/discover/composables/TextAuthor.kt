package com.example.sensimate.screens.discover.composables

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp

@Composable
fun TextAuthor(
    surveyAuthor: String
) {
    Text(
        text = "by $surveyAuthor",
        color = Color.DarkGray,
        fontSize = 12.sp,
        textDecoration = TextDecoration.Underline
    )
}