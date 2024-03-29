package com.example.sensimate.core.composables

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.sensimate.core.Constants
import com.example.sensimate.firebase_model.data.Event
import com.example.sensimate.firebase_model.data.UserData
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
            modifier = Modifier
                .graphicsLayer(alpha = 0.99f)
                .drawWithCache {
                    val brush = Brush.horizontalGradient(colors = listOf(LButton1, LButton2))
                    onDrawWithContent {
                        drawContent()
                        drawRect(brush, blendMode = BlendMode.SrcAtop)
                    }
                },
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
    val eventId = event.eventId

    Card(elevation = 8.dp, shape = RoundedCornerShape(20.dp)) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.surface)
                .padding(10.dp, 5.dp)
                .clickable {
                    navController.navigate("${BottomBarScreen.EventPage.route}?${Constants.EVENT_ID}={${event.eventId}}")
                }
        ) {
            Image(
                painter = rememberAsyncImagePainter(event.eventImage),
                contentDescription = "Event Image",
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
        Text(text = event.eventId.toString())
        Text(text = "")
    }
}


//profile card for the users information
@Composable
fun ProfileCard(userData: UserData) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colors.background)
                .padding(16.dp,8.dp,16.dp,8.dp)
                .shadow(10.dp, shape = RoundedCornerShape(20.dp)),
        ) {
            // Add profile card
            //Adds profile card icon at the top
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colors.surface,
                            shape = RoundedCornerShape(20.dp,20.dp,0.dp,0.dp))
                        .padding(20.dp, 20.dp,20.dp,5.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Person,
                        contentDescription = "profil",
                        tint = Color.Blue,
                        modifier = Modifier.size(50.dp)
                    )
                    Text(text = "") // filler text for allignment
                }

            // Adds the User information to the card
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colors.surface)
                        .padding(20.dp, 5.dp)
                ) {
                    Text(text = "Email")
                    Text(
                        text = userData.email,
                        textAlign = TextAlign.Right,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colors.surface)
                        .padding(20.dp, 5.dp)
                ) {
                    Text(text = "Født")
                    Text(
                        text = userData.age,
                        textAlign = TextAlign.Right,
                        modifier = Modifier.fillMaxWidth()
                    )

                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colors.surface)
                        .padding(20.dp, 5.dp)
                ) {
                    Text(text = "Køn")
                    Text(
                        text = userData.sex,
                        textAlign = TextAlign.Right,
                        modifier = Modifier.fillMaxWidth()
                    )

                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colors.surface,
                            shape = RoundedCornerShape(0.dp,0.dp,20.dp,20.dp))
                        .padding(20.dp, 5.dp,20.dp,20.dp)
                ) {
                    Text(text = "PostNr.")
                    Text(
                        text = userData.postal,
                        textAlign = TextAlign.Right,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
        }
}