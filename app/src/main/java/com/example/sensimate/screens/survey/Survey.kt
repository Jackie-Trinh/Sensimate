package com.example.sensimate.screens.survey

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.sensimate.R
import com.example.sensimate.navigation.BottomBarScreen

@Composable
fun Survey(navController: NavController, surveyViewModel: SurveyViewModel) {

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

@Composable
fun ExitQuestionBar(navController: NavController) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp)
            .clip(RoundedCornerShape(22.dp))
            .background(Color.White)
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(10.dp)
        )
        {

            Image(
                painterResource(id = R.drawable.ic_icons8_facebook),
                contentDescription = "SensimateIcon",
                Modifier
                    .size(25.dp),
            )

            Spacer(modifier = Modifier.padding(3.dp))

            Text(
                "Luk",
                fontSize = 18.sp,
                textAlign = TextAlign.Left,
                modifier = Modifier.clickable { navController.navigate(route = BottomBarScreen.Discover.route) }

                )


        }

    }
}

@Composable
fun QuestionProgressBar() {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(6.dp)
    ) {


        LinearProgressIndicator(
            progress = 0.5f,
            modifier = Modifier
                .fillMaxWidth()
                .height(12.dp)
                .clip(RoundedCornerShape(25.dp)),
            backgroundColor = Color.White,
            color = Color.Black
        )

    }

}

@Composable
fun QuestionTextBox() {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .padding(0.dp)
            .clip(RoundedCornerShape(22.dp))
            .background(Color.White)
    )
    {

        Text(
            "Spørgsmål",
            fontSize = 18.sp,
            textAlign = TextAlign.Left,
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp)
        )


    }

}

//TODO: questions passed as args???
@Composable
fun QuestionAnswerBox() {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .padding(0.dp)
            .clip(RoundedCornerShape(22.dp))
            .background(Color.White)
    ) {

        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)
        ) {

            QuestionCheckRow("Det smagte fandme godt")

            QuestionCheckRow("Det smagte okay")

            QuestionCheckRow("Det smagte ikke så godt")

            QuestionCheckRow("Det smagte virkelig virkelig dårlig")

        }


    }

}

//TODO: questions passed as args???
@Composable
fun QuestionAnswerBox1(
    questions: List<String>
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(360.dp)
            .padding(0.dp)
            .clip(RoundedCornerShape(22.dp))
            .background(Color.White)
    )
    {

        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)
        ) {


            QuestionCheckRow("Det smagte fandme godt")

            QuestionCheckRow("Det smagte okay")

            QuestionCheckRow("Det smagte ikke så godt")

            QuestionCheckRow("Det smagte virkelig virkelig dårlig")

        }


    }

}

@Composable
fun QuestionLastNext() {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(0.dp)
    ) {

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .width(168.dp)
                .height(50.dp)
                .padding(0.dp)
                .clip(RoundedCornerShape(22.dp))
                .border(width = 2.dp, color = Color.Black, shape = RoundedCornerShape(22.dp))
                .background(Color.White)
        )
        {

            Text(
                "FORRIGE",
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(horizontal = 0.dp, vertical = 0.dp)
                    .fillMaxWidth()
            )

        }

        Spacer(modifier = Modifier.padding(8.dp))

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .width(168.dp)
                .height(50.dp)
                .padding(0.dp)
                .clip(RoundedCornerShape(22.dp))
                .background(Color.Black)
        )
        {

            Text(
                "NÆSTE",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(horizontal = 0.dp, vertical = 0.dp)
                    .fillMaxWidth()
            )


        }

    }


}

@Composable
fun QuestionCheckRow(
    questionString: String
) {

    val checkedState = remember { mutableStateOf(false) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(0.dp)
    ) {
        Checkbox(
            checked = checkedState.value,
            onCheckedChange = { checkedState.value = it },
        )

        Text(
            questionString,
            fontSize = 18.sp,
            textAlign = TextAlign.Left,
            modifier = Modifier.padding(horizontal = 0.dp, vertical = 0.dp)
        )

    }


}