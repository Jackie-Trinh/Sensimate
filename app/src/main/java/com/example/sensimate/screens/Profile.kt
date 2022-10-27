package com.example.sensimate.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun Profile() {

    Surface(
        color = MaterialTheme.colors.surface
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,


            ) {
            Icon(
                imageVector = Icons.Filled.Face,
                contentDescription = "profile",
                tint = Color.Blue,
                modifier = Modifier.size(50.dp)
            )
            Column {
                Text("Email")
                Text("Age")
                Text("Sex")
                Text("Postcode")
            }
            Column {
                Text("Peter_Christensen@gmail.com")
                Text("22")
                Text("Male")
                Text("6400")
            }
        }
    }
}

