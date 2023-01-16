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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.sensimate.R
import com.example.sensimate.domain.model.*
import com.example.sensimate.domain.model.Survey
import com.example.sensimate.navigation.BottomBarScreen
import com.example.sensimate.screens.event_manager.EventManagerViewModel

@Composable
fun Survey(
    navController: NavController,
    viewModel: EventManagerViewModel = hiltViewModel(),
    eventId: Int,
) {
    LaunchedEffect(Unit) {
        viewModel.getEvent(eventId)
        viewModel.getQuestions(eventId)
    }

    val questions by viewModel.questions.collectAsState(initial = emptyList())

    val event = viewModel.event

    if(viewModel.event.id!=0 && questions.isNotEmpty()) {

        //placeholder user answers for the questions
        val userAnswers = mutableListOf(
            UserAnswers(
                1,
                10,
                mutableListOf(),
                listOf(
                    UserAnswersItem(1, 10, mutableListOf())
                )
            )
        )


        //current page we are at
        var currentPage = remember { mutableStateOf(value = 1) }
        //new page checker
        var newPageChecker = remember { mutableStateOf(true) }


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

                Text(text = "$eventId")
                Text(text = event.title)

                SetupAnswerList(userAnswers, event)

                ExitQuestionBar(navController = navController)

                Spacer(modifier = Modifier.padding(4.dp))

                QuestionProgressBar(currentPage, event)

                Spacer(modifier = Modifier.padding(4.dp))

                QuestionTextBox(questions, currentPage)

                Spacer(modifier = Modifier.padding(8.dp))

                QuestionAnswerBox(currentPage, questions, userAnswers, newPageChecker)

                Spacer(modifier = Modifier.padding(8.dp))

                QuestionLastNext(currentPage, viewModel.event, navController, newPageChecker)

            }


        }
    }
}

//so we can set elements in the list without it crashing
@Composable
fun SetupAnswerList(userAnswers: MutableList<UserAnswers>, event: Event){
    val totalPages = event.numberOfQuestions
    val items = userAnswers[0].answers

    for (i in 0 until totalPages){
        items.add(i,0)
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

//change List<survey> to survey when changed to the real code
@Composable
fun QuestionProgressBar
            (
    currentPage: MutableState<Int>,
    event: Event
) {
    var progress by remember { mutableStateOf(value = 0.00f) }
    progress -= progress //reset progress, so we don't overlap the counter

    //placeholder code
        val questionTotal = event.numberOfQuestions.toFloat().plus(0.0) //number of questions
        val currentNumber = currentPage.value.toFloat().plus(0.0) //current question number
        //calculate the percentage and make it a float
        val sum = currentNumber.div(questionTotal)
        val sumTimes = sum.times(100)
        for (i in 0 until sumTimes.toInt()) {
            progress += 0.01f
        }

    /*
    //real code for the progressbar
    val questionTotal = survey.numberOfQuestions.toFloat().plus(0.0) //number of questions
    val currentNumber = currentPage.value.toFloat().plus(0.0) //current question number

    //calculate the percentage and make it a float
    val sum = currentNumber.div(questionTotal)
    val sumTimes = sum.times(100)
    for (i in 0 until sumTimes.toInt()){
        progress += 0.01f
    }
     */

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

//change List<question> to question when changed to the real code
@Composable
fun QuestionTextBox(question: List<Question>,currentPage: MutableState<Int>) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .padding(0.dp)
            .clip(RoundedCornerShape(22.dp))
            .background(Color.White)
    )
    {
        //placeholder code
            Text(
                text = question[currentPage.value.minus(1)].questionText,
                fontSize = 18.sp,
                textAlign = TextAlign.Left,
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp)
            )

        /*
        //real code
        Text(
            text =  question.questionText,
            fontSize = 18.sp,
            textAlign = TextAlign.Left,
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp)
        )
         */

    }

}


//change List<question>, List<useranswers> to question, UserAnswers when changed to the real code
@Composable
fun QuestionAnswerBox(
    currentPage: MutableState<Int>,
    question: List<Question>,
    userAnswers: MutableList<UserAnswers>,
    newPageChecker: MutableState<Boolean>,
) {

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


            //placeholder code
            val answers = question[currentPage.value.minus(1)].answerOptions
            var answerNumber = 1
            for (item in answers) {
                QuestionCheckRow(
                            questionString = item,
                            answerNumber, userAnswers, currentPage, newPageChecker)
                answerNumber++ //add counter
                }
        }
    }
}

//check if we are on a new page, if yes check for mark
@Composable
fun CheckedState(
    checkedState: MutableState<Boolean>,
    markCheck: Int, answerNumber: Int, newPageChecker: MutableState<Boolean>,){
    //reset checkedstate to false
    checkedState.value = false

    //checks if there are marked boxes
    checkedState.value = markCheck == answerNumber
}

//change List<useranswer> to Useranswer when changed to the real code
@Composable
fun QuestionCheckRow(
    questionString: String,
    answerNumber: Int,
    userAnswers: MutableList<UserAnswers>,
    currentPage: MutableState<Int>,
    newPageChecker: MutableState<Boolean>,
) {
    val markCheck = userAnswers[0].answers[currentPage.value.minus(1)]
    val totalQuestion = userAnswers[0].answers.size

    var checkedState = remember { mutableStateOf(false) }

    //check if new page
    if (newPageChecker.value){
        CheckedState( checkedState,markCheck, answerNumber,newPageChecker)
    }

    //check if all items have been checked after a new page
    if (answerNumber == totalQuestion){
        newPageChecker.value = false
    }

    //placeholder code
    val items = userAnswers[0].answers
    if (checkedState.value) {
            items[currentPage.value.minus(1)] = answerNumber
    }

    //boolean for checkbox delete
    val checkBoxBool = items[currentPage.value.minus(1)] == 0

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(0.dp)
    ) {
        Checkbox(
            checked = checkedState.value,
            onCheckedChange = { checkedState.value = it
                if(checkBoxBool){
                    items[currentPage.value.minus(1)] = 0
                }else{
                    items[currentPage.value.minus(1)] = 0
               }
                              },
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
fun QuestionLastNext(
    currentPage: MutableState<Int>,
    event: Event,
    navController: NavController,
    newPageChecker: MutableState<Boolean>
) {

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
                    .clickable {
                        currentPage.value--
                        newPageChecker.value = true
                    }

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

        //placeholder code code
        if (placeHolderInt == event.numberOfQuestions) {
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
        } else {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .width(168.dp)
                    .height(50.dp)
                    .padding(0.dp)
                    .clip(RoundedCornerShape(22.dp))
                    .background(Color.Black)
                    .clickable {
                        currentPage.value++
                        newPageChecker.value = true
                    }
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