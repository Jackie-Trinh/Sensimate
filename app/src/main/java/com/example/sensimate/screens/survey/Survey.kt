package com.example.sensimate.screens.survey

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Checkbox
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.ProgressIndicatorDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.sensimate.R
import com.example.sensimate.domain.model.*
import com.example.sensimate.domain.model.Survey
import com.example.sensimate.navigation.BottomBarScreen

@Composable
fun Survey(navController: NavController, surveyViewModel: SurveyViewModel) {
    //placeholder survey
    val survey = listOf(
        Survey(
            1,
            3,
            listOf(
                SurveyItem(1, 3)
            )
        )
    )

    //placeholder survey items
    val surveyElements = listOf(
        Question(
            1,
            1,
            "Is this quiz cool 1?",
            listOf("yes","meh","no"),
            listOf(
                QuestionItem(1, 1,"Is this quiz cool 1?", listOf("yes","meh","no"))
            )
        ),
        Question(
            1,
            2,
            "Is this quiz cool 2?",
            listOf("yes","meh","no"),
            listOf(
                QuestionItem(1, 2,"Is this quiz cool 2?", listOf("yes","meh","no"))
            )
        ),
        Question(
            1,
            1,
            "Is this quiz cool 3?",
            listOf("yes","meh","no"),
            listOf(
                QuestionItem(1, 3,"Is this quiz cool 3?", listOf("yes","meh","no"))
            )
        )
    )

    //placeholder user answers for the questions
    val userAnswers = listOf(
        UserAnswers(
            1,
            10,
            listOf(),
            listOf(
                UserAnswersItem(1, 10, listOf())
            )
        )
    )




    //current page we are at
    var currentPage = remember { mutableStateOf(value = 1) }



    //no data from the real database yet, so we are using placeholders above
    /*
    //get the questions and their related data
    val surveyElements = remember {
        mutableStateListOf<Question>()
    }
    //get the survey id and total amount of questions
    val survey = remember {
        mutableStateListOf<Survey>()
    }
    val userAnswers = remember {
        mutableStateListOf<UserAnswers>()
    }
*/


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

            QuestionProgressBar(currentPage, survey)

            Spacer(modifier = Modifier.padding(4.dp))
            
            QuestionTextBox(surveyElements)

            Spacer(modifier = Modifier.padding(8.dp))

            QuestionAnswerBox(currentPage,surveyElements,userAnswers)

            Spacer(modifier = Modifier.padding(8.dp))

            QuestionLastNext(currentPage, survey, navController)

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
                painterResource(id = R.drawable.ic_baseline_arrow_back_24),
                contentDescription = "Exit survey button",
                Modifier
                    .size(25.dp),
            )

            Spacer(modifier = Modifier.padding(3.dp))

            Text(
                "Close",
                fontSize = 18.sp,
                textAlign = TextAlign.Left,
                modifier = Modifier.clickable { navController.navigate(route = BottomBarScreen.Discover.route) }

                )
        }
    }
}

//change surveyItem to survey when changed to the real code
@Composable
fun QuestionProgressBar(currentPage: MutableState<Int>,
                        survey: SurveyItem) {
    var progress by remember { mutableStateOf(value = 0.00f) }
    progress -= progress //reset progress, so we don't overlap the counter

    //real code for the progressbar
    val questionTotal = survey.numberOfQuestions.toFloat().plus(0.0) //number of questions
    val currentNumber = currentPage.value.toFloat().plus(0.0) //current question number

    //calculate the percentage and make it a float
    val sum = currentNumber.div(questionTotal)
    val sumTimes = sum.times(100)
    for (i in 0 until sumTimes.toInt()){
        progress += 0.01f
    }

    //added smooth animation to increase and decrease
    val animatedProgress = animateFloatAsState(
        targetValue = progress,
        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec
    ).value

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(6.dp)
    ) {
    LinearProgressIndicator(
        progress = animatedProgress,
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
fun QuestionTextBox(question: Question) {

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
            text =  question.questionText,
            fontSize = 18.sp,
            textAlign = TextAlign.Left,
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp)
        )


    }

}

//TODO: questions passed as args???
@Composable
fun QuestionAnswerBox(currentPage: MutableState<Int>,
                      question: Question,
                      userAnswers: UserAnswers) {

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

            for ((answerNumber, item) in question.answerOptions.withIndex()) {
                QuestionCheckRow(questionString = item,
                    answerNumber, userAnswers, currentPage)
                //add a count to it so the next item has the right position
            }
        }
    }
}

@Composable
fun QuestionCheckRow(
    questionString: String,
    answerNumber: Int,
    userAnswers: UserAnswers,
    currentPage: MutableState<Int>
) {

    val checkedState = remember { mutableStateOf(false) }

    //if checked add to userAnswers
    if (checkedState.value){
        userAnswers.answers[currentPage.value].plus(answerNumber)
    }

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

@Composable
fun QuestionLastNext(currentPage: MutableState<Int>, survey: Survey, navController: NavController) {

    var placeHolderInt = currentPage.value

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(0.dp)
    ) {

        //if it is the first page, make last unavailable
        if (placeHolderInt > 1){
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .width(168.dp)
                    .height(50.dp)
                    .padding(0.dp)
                    .clip(RoundedCornerShape(22.dp))
                    .border(width = 2.dp, color = Color.Black, shape = RoundedCornerShape(22.dp))
                    .background(Color.White)
                    .clickable { currentPage.value-- }

            )
            {

                Text(
                    "PREVIOUS",
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(horizontal = 0.dp, vertical = 0.dp)
                        .fillMaxWidth()
                )

            }
        }else{
            //if the current question is 1 <, then make last available
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .width(168.dp)
                    .height(50.dp)
                    .padding(0.dp)
                    .clip(RoundedCornerShape(22.dp))
                    .border(width = 2.dp, color = Color.Black, shape = RoundedCornerShape(22.dp))
                    .background(Color.White)
                    .alpha(0.3f)
            )
            {
                Text(
                    "PREVIOUS",
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(horizontal = 0.dp, vertical = 0.dp)
                        .fillMaxWidth()
                )
            }
        }

        Spacer(modifier = Modifier.padding(8.dp))

        if (placeHolderInt == survey.numberOfQuestions){
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .width(168.dp)
                    .height(50.dp)
                    .padding(0.dp)
                    .clip(RoundedCornerShape(22.dp))
                    .background(Color.Black)
                    .clickable { navController.navigate(route = BottomBarScreen.Discover.route) }
            )
            {

                Text(
                    "FINISH",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(horizontal = 0.dp, vertical = 0.dp)
                        .fillMaxWidth()
                )
            }
        }else {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .width(168.dp)
                    .height(50.dp)
                    .padding(0.dp)
                    .clip(RoundedCornerShape(22.dp))
                    .background(Color.Black)
                    .clickable { currentPage.value++ }
            )
            {

                Text(
                    "NEXT",
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
}