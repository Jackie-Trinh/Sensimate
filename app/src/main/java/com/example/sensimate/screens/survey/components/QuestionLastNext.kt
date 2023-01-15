package com.example.sensimate.screens.survey.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.sensimate.domain.model.Event
import com.example.sensimate.navigation.BottomBarScreen

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