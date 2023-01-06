package com.example.sensimate.model

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import com.example.sensimate.data.Event
import com.example.sensimate.data.EventItem
import com.example.sensimate.data.User
import com.example.sensimate.navigation.NavRoutes


//Section bar with a back to previous button

//list setup of event items
@Composable
fun EventCardSelection(navController: NavController, events: List<com.example.sensimate.data.Event>) {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.background),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
    ) {
        //events - iterate through each item
        for (item in events) {
            item{
                EventItem(navController = navController,event = item)
            }
            //used as padding
            item { Text(text = "")  }
        }

        //padding for the bot bar, to make items visible
        //used as padding
        item { Text(text = "")  }
        //used as padding
        item { Text(text = "")  }
    }
}

//EventItem - with clickable event to next page (currently survey)
@Composable
fun EventItem(navController: NavController, event: Event) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.surface)
            .padding(10.dp, 5.dp)
            .clickable {
                navController.navigate(route = NavRoutes.EventPage.route)
            }
    ) {
            Icon(
                imageVector = Icons.Filled.Person,
                contentDescription = "profile",
                tint = Color.Blue,
                modifier = Modifier.size(150.dp)
            )
        EventItemDetail(item = event)
    }
}


//EventItem - details for the event item insert
@Composable
fun EventItemDetail(item: Event) {
    Column(
        modifier = Modifier
            .padding(10.dp, 5.dp)
    ) {
        Text(text = "") //padding
        Text(text = item.name)
        Text(text = item.date, style = MaterialTheme.typography.body2)
        Text(text = "") //padding
        Text(text = item.address)
        Text(text = "")
    }
}



//Survey Question section

//Survey Answer section


//profile card for the users information
@Composable
fun ProfileCard(user: User) {

    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.background),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
    ) {

        // Add profile card
        //Adds profile card icon at the top
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
                    Text(text = "") // filler text for allignment
            }
        }

        // Adds the User information to the card
        item {
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.surface)
                    .padding(10.dp, 5.dp)
            ) {
                    Text(text = "Email")
                    Text(text = user.email,
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
                    Text(text = user.age,
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
                    Text(text = user.sex,
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
                    Text(text = user.postcode,
                        textAlign = TextAlign.Right,
                        modifier = Modifier.fillMaxWidth())
            }
        }
    }
}