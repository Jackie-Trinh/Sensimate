package com.example.sensimate.model

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.sensimate.data.EventItem
import com.example.sensimate.data.User
import com.example.sensimate.domain.model.Event
import com.example.sensimate.navigation.BottomBarScreen
import com.example.sensimate.ui.theme.LButton1
import com.example.sensimate.ui.theme.LButton2


//Section bar with a back to previous button

//Button
@Composable
fun GradientButton(navController: NavController, text: String ,state: Boolean,onClick: ()-> Unit){
if(state){
    Button(
        onClick = onClick,
        Modifier.defaultMinSize(150.dp,50.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
        border = BorderStroke(2.dp, brush = Brush.horizontalGradient(
            colors = listOf(LButton1, LButton2))),
        shape = RoundedCornerShape(50),
        contentPadding = PaddingValues(17.dp)
    ) {
        Text(
            text,
            modifier = Modifier.graphicsLayer(alpha = 0.99f)
                .drawWithCache { val brush = Brush.horizontalGradient(colors = listOf(LButton1, LButton2))
                               onDrawWithContent { drawContent()
                               drawRect(brush, blendMode = BlendMode.SrcAtop)}},
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
        )
    }
}else{
    Button(
        onClick = onClick,
        Modifier.defaultMinSize(150.dp,50.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
        border = BorderStroke(2.dp, brush = Brush.horizontalGradient(
            colors = listOf(LButton1, LButton2))),
        shape = RoundedCornerShape(50),
        contentPadding = PaddingValues(17.dp)
    ) {
        Text(
            text,
            color = Color.Black,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
        )
    }
}
}

//list setup of event items
@Composable
fun EventCardSelection(
    navController: NavController,
    events: List<Event>,
) {

    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.background),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
    ) {
        //events - iterate through each item
        items(
            items = events
        ) { event ->
            EventItem(
                navController = navController,
                event = event,

            )
            //used as padding
            Spacer(modifier = Modifier.height(10.dp))

        }

        //padding for the bot bar, to make items visible
        //used as padding
        item { Spacer(modifier = Modifier.height(10.dp))  }
        //used as padding
        item { Spacer(modifier = Modifier.height(10.dp))  }
    }
}

//EventItem - with clickable event to next page (currently survey)
@Composable
fun EventItem(
    navController: NavController,
    event: Event,
) {
    val eventId = event.id

    Card(elevation = 8.dp, shape = RoundedCornerShape(20.dp)) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.surface)
                .padding(10.dp, 5.dp)
                .clickable {
                    navController.navigate("${BottomBarScreen.EventPage.route}/${eventId}")
                }
        ) {
            Icon(
                imageVector = Icons.Filled.Person,
                contentDescription = "profile",
                tint = Color.Blue,
                modifier = Modifier.size(150.dp)
            )
            EventItemDetail(event = event)
        }
    }
}


//EventItem - details for the event item insert
@Composable
fun EventItemDetail(event: Event) {
    Column(
        modifier = Modifier
            .padding(10.dp, 5.dp)
    ) {
        Text(text = "") //padding
        Text(text = event.title)
        Text(text = event.address, style = MaterialTheme.typography.body2)
        Text(text = "") //padding
        Text(text = event.id.toString())
        Text(text = "")
    }
}


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