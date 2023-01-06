package com.example.sensimate.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.sensimate.*


@Composable
fun Discover(navController: NavController) {
    //EventsCard(navController = navController)
    val discover = listOf(
        Discover(1, "Ev", "Description 1", listOf(DiscoverItem(1,"item 1", "desc 1"))),
        Discover(2, "Event 2", "Description 2", listOf(DiscoverItem(2,"item 2", "desc 2"))),
        Discover(3, "Event 3", "Desc 3", listOf(DiscoverItem(3,"item 3", "desc 3")))
    )
    Column {
        discoverColumn(discover = discover, navController)
    }
}

@Composable
fun discoverColumn(discover: List<Discover>, navController: NavController) {
    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colors.background),
        contentPadding = PaddingValues(horizontal = 15.dp, vertical = 8.dp),)
    {
        items(items = discover) { Discover ->
            DiscoverRow(discover = Discover, navController)
        }
    }


}

@Composable
fun DiscoverRow (discover: Discover, navController: NavController) {
    Row(modifier = Modifier
        .clickable { navController.navigate(route = NavRoutes.Survey.route) }){

        for (item in discover.items) {
            Item(item = item)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun Item(item: DiscoverItem) {
    Row(modifier = Modifier.padding(16.dp)) {
        Icon(
            imageVector = Icons.Filled.Person,
            contentDescription = "profile",
            tint = Color.Blue,
            modifier = Modifier.size(150.dp),
        )

        Column {
            Text(text = item.name, style = MaterialTheme.typography.h6)
            Text(text = item.description, style = MaterialTheme.typography.body2)
        }
    }
}



/*
@Composable
fun EventsCard(navController: NavController) {

    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.background),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
    ) {

        //events
        item {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.surface)
                    .padding(10.dp, 5.dp)
                    .clickable { navController.navigate(route = NavRoutes.Survey.route) }
            ) {
                item {
                    Icon(
                        imageVector = Icons.Filled.Person,
                        contentDescription = "profile",
                        tint = Color.Blue,
                        modifier = Modifier.size(150.dp)
                    )
                }
                item {
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
        }
    }
}
*/
