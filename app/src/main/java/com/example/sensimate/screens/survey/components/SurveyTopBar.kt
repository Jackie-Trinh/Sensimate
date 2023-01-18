package com.example.sensimate.screens.survey.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Done
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun SurveyTopBar(
    navController: NavController,
    eventTitle: String,
    onPressEditButton: () -> Unit,
    editMode: Boolean,
    questionIndex: Int,
    totalQuestionsCount: Int,
    onPressDoneEditButton: () -> Unit,
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp)
            .clip(RoundedCornerShape(22.dp))
            .background(Color.White),
        Alignment.Center
    ) {

        Text(
            eventTitle,
            fontSize = 18.sp,
            textAlign = TextAlign.Center,

            )

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement  =  Arrangement.SpaceBetween
        ){
            //Back button
            IconButton(
                onClick = {
                    navController.popBackStack()
                }
            ) {
                Icon(
                    imageVector = Icons.Outlined.ArrowBack,
                    contentDescription = null,
                )
            }
            //Event title


            Row(verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(end = 16.dp)
                ) {

                if (!editMode){
                    //Edit button
                    IconButton(
                        onClick = {
                            onPressEditButton()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Edit,
                            contentDescription = null,
                        )
                    }
                } else {
                    //Finish Edit button
                    IconButton(
                        onClick = {
                            onPressEditButton()
                            onPressDoneEditButton()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Done,
                            contentDescription = null,
                        )
                    }
                }



                val tempQuestionCount = questionIndex + 1
                Text(

                    "$tempQuestionCount/$totalQuestionsCount",
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(horizontal = 0.dp)

                    )

            }

        }
    }
}