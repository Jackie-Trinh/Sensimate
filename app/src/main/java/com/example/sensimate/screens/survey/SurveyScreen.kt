package com.example.sensimate.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Checkbox
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.sensimate.navigation.NavRoutes
import com.example.sensimate.R
import com.example.sensimate.screens.survey.composables.*



@Composable
fun SurveyUI(navController: NavController) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray),
        contentAlignment = Alignment.TopCenter
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {

            ExitQuestionBar(navController = navController)

            Spacer(modifier = Modifier.padding(4.dp))

            QuestionProgressBar()

            Spacer(modifier = Modifier.padding(4.dp))

            QuestionTextBox()

            Spacer(modifier = Modifier.padding(8.dp))

            QuestionAnswerBox()

            Spacer(modifier = Modifier.padding(8.dp))

            QuestionLastNext()

        }


    }
}









