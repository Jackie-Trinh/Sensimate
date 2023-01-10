package com.example.sensimate.screens.event_manager.manage_survey.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.sensimate.domain.model.Event
import com.example.sensimate.domain.model.Question
import com.example.sensimate.navigation.BottomBarScreen

@Composable
fun QuestionItem(
    navController: NavController,
    question: Question,
) {
    val questionId = question.id

    Card(elevation = 8.dp, shape = RoundedCornerShape(20.dp)) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.surface)
                .padding(10.dp, 5.dp)
//                .clickable {
//                    navController.navigate("${BottomBarScreen.EventPage.route}/${eventId}")
//                }
        ) {
            Icon(
                imageVector = Icons.Filled.Person,
                contentDescription = "profile",
                tint = Color.Blue,
                modifier = Modifier.size(150.dp)
            )
            EventItemDetail(question = question)
        }
    }
}


//EventItem - details for the event item insert
@Composable
fun EventItemDetail(question: Question) {
    Column(
        modifier = Modifier
            .padding(10.dp, 5.dp)
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = question.id.toString())
        Text(text = question.questionNumber.toString(), style = MaterialTheme.typography.body2)
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = question.questionText)
        Spacer(modifier = Modifier.height(10.dp))
    }
}
