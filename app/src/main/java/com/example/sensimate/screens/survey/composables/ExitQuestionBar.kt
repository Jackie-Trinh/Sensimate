package com.example.sensimate.screens.survey.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.sensimate.R
import com.example.sensimate.navigation.NavRoutes

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
                modifier = Modifier.clickable { navController.navigate(route = NavRoutes.Discover.route) }

            )


        }

    }
}