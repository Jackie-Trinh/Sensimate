package com.example.sensimate.screens.faq

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.sensimate.R

@Composable
fun FAQ(
    navController: NavController
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Image(painter = painterResource(id = R.drawable.bar_image),
            contentDescription = "FAQ image",
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp),
            contentScale = ContentScale.Crop)

        LazyColumn(modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp, 20.dp)
            .shadow(10.dp, shape = RoundedCornerShape(20.dp,20.dp,20.dp,20.dp))
            .background(
                color = MaterialTheme.colors.surface,
                shape = RoundedCornerShape(20.dp, 20.dp, 20.dp, 20.dp)
            ),
            contentPadding = PaddingValues(20.dp,20.dp)
        ){
            item { Text(text = "hello this is the FAQ page, This is just filler text" +
                    "to check if the text keeps going after there is overflow") }
        }
    }
}