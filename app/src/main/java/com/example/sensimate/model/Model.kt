package com.example.sensimate.model

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.sensimate.navigation.NavRoutes

//EventItem - with clickable event to next page (currently survey)
@Composable
fun EventItem(navController: NavController) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.surface)
            .padding(10.dp, 5.dp)
            .clickable {
                navController.navigate(route = NavRoutes.Survey.route)
            }
    ) {
            Icon(
                imageVector = Icons.Filled.Person,
                contentDescription = "profile",
                tint = Color.Blue,
                modifier = Modifier.size(150.dp)
            )
            Column(
                modifier = Modifier
                    .padding(10.dp, 5.dp)
            ) {
                Text(text = "")
                Text(text = "Klik for survey test")
                Text(text = "24/12-2022")
                Text(text = "")
                Text(text = "3 km")
                Text(text = "")
            }
    }
}

//Survey Question section

//Survey Answer section


//profile card for the users information
@Composable
fun ProfileCard() {

    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.background),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
    ) {

        // Add profile card
        item {
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.surface)
                    .padding(10.dp, 5.dp)
            ) {
                    Icon(
                        imageVector = Icons.Filled.Person,
                        contentDescription = "profile",
                        tint = Color.Blue,
                        modifier = Modifier.size(50.dp)
                    )
                    Text(text = "")
            }
        }

        // Add a row
        item {
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.surface)
                    .padding(10.dp, 5.dp)
            ) {
                    Text(text = "Email")
                    Text(text = "Peter_Christiansen@gmail.com",
                        textAlign = TextAlign.Right,
                    modifier = Modifier.fillMaxWidth())
            }
        }
        item {
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.surface)
                    .padding(10.dp, 5.dp)
            ) {
                    Text(text = "Age")
                    Text(text = "24",
                        textAlign = TextAlign.Right,
                        modifier = Modifier.fillMaxWidth())

            }
        }
        item {
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.surface)
                    .padding(10.dp, 5.dp)
            ) {
                    Text(text = "Sex")
                    Text(text = "Male",
                        textAlign = TextAlign.Right,
                        modifier = Modifier.fillMaxWidth())

            }
        }
        item {
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.surface)
                    .padding(10.dp, 5.dp)
            ) {
                    Text(text = "Postcode")
                    Text(text = "6400",
                        textAlign = TextAlign.Right,
                        modifier = Modifier.fillMaxWidth())
            }
        }
    }
}