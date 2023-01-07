package com.example.sensimate.screens.landing

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.sensimate.R

@Composable
fun LandingPage(navController: NavController, landingPageViewModel: LandingPageViewModel) {

    val logo = painterResource(id = R.drawable.ic_sensimate)

    Column(modifier = Modifier.fillMaxSize()) {
        //used as padding
        Text(text = "")
        //used as padding
        Text(text = "")
        //used as padding
        Text(text = "")

        //welcome text
        // TODO: style up the welcome text
        Text(text = "Velkommen til SensiMate")

        //placeholder icon for SensiMate logo
        Image(
            logo,
            contentDescription = "SensimateIcon",
            Modifier
                .size(200.dp),
        )

        //age confirmation question
        Text(text = "Er du 18 år eller ældre?")

        //buttons
        LazyRow(horizontalArrangement = Arrangement.spacedBy(70.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(40.dp, 5.dp)
        ) {
            item {
                Button(onClick = { },
                    colors = ButtonDefaults.buttonColors(Color.Magenta)) {
                    Text("Yes")
                }
            }


        }

    }
}
