package com.example.sensimate.screens.discover.composables

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import com.example.sensimate.core.Constants.Companion.DELETE_SURVEY


@Composable
fun DeleteIcon(
    deleteSurvey: () -> Unit
) {
    IconButton(
        onClick = deleteSurvey
    ) {
        Icon(
            imageVector = Icons.Default.Delete,
            contentDescription = DELETE_SURVEY,
        )
    }
}