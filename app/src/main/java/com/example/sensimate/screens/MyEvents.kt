package com.example.sensimate.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.sensimate.Event
import com.example.sensimate.EventItem

@Preview
@Composable
fun MyEvents() {
    val events = listOf(
        Event(1, "Ev", "Description 1", listOf(EventItem(1,"item 1", "desc 1"))),
        Event(2, "Event 2", "Description 2", listOf(EventItem(2,"item 2", "desc 2"))),
        Event(3, "Event 3", "Desc 3", listOf(EventItem(3,"item 3", "desc 3")))
    )
    Column {
        EventLazyColumn(events = events)
    }

}


@Composable
fun EventLazyColumn(events: List<Event>) {
    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colors.background),
        contentPadding = PaddingValues(horizontal = 15.dp, vertical = 8.dp),)
    {
        items(items = events) { event ->
            EventLazyRows(event = event)
        }
    }
}

@Composable
fun EventLazyRows(event: Event) {
    Row {
        for (item in event.items) {

            EventItem(item = item)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun EventItem(item: EventItem) {
    Row(modifier = Modifier.padding(16.dp)) {
        Icon(
            imageVector = Icons.Filled.Person,
            contentDescription = "profile",
            tint = Color.Blue,
            modifier = Modifier.size(150.dp)
        )
        Column {
            Text(text = item.name, style = MaterialTheme.typography.h6)
            Text(text = item.description, style = MaterialTheme.typography.body2)
        }
    }
}


/*
@Composable
fun MyEventsCard() {

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
                        Text(text = "Øl smagning")
                        Text(text = "24/12-2022")
                        Text(text = "")
                        Text(text = "3 km")
                        Text(text = "")
                    }
                }
            }
        }

        //used as padding
        item { Text(text = "")  }


        item {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.surface)
                    .padding(10.dp, 5.dp)
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
                        Text(text = "Øl smagning")
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
