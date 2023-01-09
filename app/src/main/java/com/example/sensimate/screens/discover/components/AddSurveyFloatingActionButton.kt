package com.example.sensimate.screens.discover.components

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.sensimate.core.Constants.Companion.ADD_EVENT

@Composable
fun AddSurveyFloatingActionButton(
    openDialog: () -> Unit
) {
    FloatingActionButton(
        onClick = openDialog,
        backgroundColor = Color.Gray
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = ADD_EVENT
        )
    }
}