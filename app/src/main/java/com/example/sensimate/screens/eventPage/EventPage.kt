package com.example.sensimate.screens.eventPage

import android.content.ClipData.Item
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.sensimate.R
import com.example.sensimate.navigation.BottomBarScreen
import com.example.sensimate.screens.eventManager.EventManagerViewModel
import java.time.format.TextStyle

@Composable
fun EventPage(
    viewModel: EventManagerViewModel = hiltViewModel(),
    eventId: Int,
    navController: NavController
) {
    LaunchedEffect(Unit) {
        viewModel.getEvent(eventId)
    }

    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.background),
        contentPadding = PaddingValues(horizontal = 0.dp, vertical = 0.dp),
    ) {
        item { 
           Row(modifier = Modifier
               .fillMaxWidth()
               .background(MaterialTheme.colors.surface)
               .padding(10.dp, 20.dp),
               verticalAlignment = Alignment.CenterVertically,
               horizontalArrangement  =  Arrangement.SpaceBetween
               ){
               Row(modifier = Modifier
                   .fillMaxHeight()
                   .clickable { navController.popBackStack() }
               ){
                   Icon(painter = painterResource(id = R.drawable.ic_baseline_arrow_back_24),
                       contentDescription = "back arrow")
                   Text(text = "Back")
               }
               Row(modifier = Modifier
                   .fillMaxHeight()
                   .clickable { navController.navigate("${BottomBarScreen.ManageEventPage.route}/${eventId}") }
               ){
                   Text(
                       text = "Edit Event",
                   )
               }
           } 
        }

        item { Text(text = viewModel.event.title, fontSize = 28.sp, fontWeight = FontWeight.Bold) }

        item { Swipe() }

        item {
            Text(text = viewModel.event.date)
            Text(text = viewModel.event.address)
            Text(text = "Beskrivelse", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Text(text = viewModel.event.description)


        }
    }
}

@Preview
@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun Swipe() {
    LazyRow(
        modifier = Modifier
            .swipeable(
                state = rememberSwipeableState(0),
                anchors = mapOf(0f to 0, 1f to 1),
                orientation = Orientation.Horizontal,
                thresholds = { _, _ -> FractionalThreshold(0f) }
            )
            .background(MaterialTheme.colors.background),
        contentPadding = PaddingValues(16.dp,4.dp),
        horizontalArrangement = Arrangement.spacedBy(24.dp)

    ) {
        items(2)
        { item -> ImageCard(painterResource(id = R.drawable.bar_image), "", "")

        }
    }
}

@Composable
private fun ImageCard(
    painter: Painter,
    contentDescription: String,
    title: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = 4.dp,
        border = BorderStroke(1.dp, color = Color.Black),
        shape = RoundedCornerShape(10.dp),
    ) {
        Row(modifier = Modifier
            .height(200.dp),
            horizontalArrangement = Arrangement.Center){
                Image(
                    painter = painter,
                    contentDescription = contentDescription,
                    contentScale = ContentScale.Crop)

        }
    }

}