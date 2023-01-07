package com.example.sensimate.screens.eventPage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.sensimate.R

@Composable
fun EventPage(navController: NavController, eventPageViewModel: EventPageViewModel) {
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
               .padding(10.dp,20.dp)
               .clickable { navController.popBackStack() },
           verticalAlignment = Alignment.CenterVertically){
               Icon(painter = painterResource(id = R.drawable.ic_baseline_arrow_back_24),
                   contentDescription = "back arrow")
               Text(text = "Back")
           } 
        }
        item {
            Image(painter = painterResource(id = R.drawable.bar_image),
                contentDescription = "event image",
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp),
            contentScale = ContentScale.Crop)
        }
        item {
            Text(text = "Hello this is the event page")
        }
    }
}