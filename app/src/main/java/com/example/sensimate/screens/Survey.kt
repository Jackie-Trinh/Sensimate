package com.example.sensimate.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sensimate.model.Surveylist
import com.example.sensimate.R





class Survey() {
    fun loaadSurveyList(): List<Surveylist> {
        return listOf<Surveylist>(
            Surveylist(R.string.SurveyList1, R.bool.button1),
            Surveylist(R.string.SurveyList2, R.bool.button2),
        )
    }
}




@Preview
@Composable
fun Surveys() {

    Box(
        modifier = Modifier.fillMaxSize()

    ) {
        Icon(
            imageVector = Icons.Filled.Home,
            contentDescription = "discover",
            tint = Color.Blue,
            modifier = Modifier
                .size(150.dp)
                .align(Alignment.Center)
        )
    }
}

@Composable
fun surveyList() {

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column() {
            Text(
                text = stringResource(id = Surveylist.stringResourceId),
                modifier = Modifer.padding(16.dp),
                style = MaterialTheme.typography.h4

            )

        }
    }
}

@Composable
fun input() {



}
