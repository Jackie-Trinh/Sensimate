package com.example.sensimate.screens.survey.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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
