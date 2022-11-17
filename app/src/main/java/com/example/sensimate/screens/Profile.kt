package com.example.sensimate.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sensimate.R

@Preview
@Composable
fun Profile() {
    
        ProfileCard()
}

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
                    modifier = Modifier.size(50.dp)
                )
            }
            item {
                Text(text = "")
            }
        }
    }

    // Add a row
    item {
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.surface)
                .padding(10.dp, 5.dp)
        ) {
            item {
                Text(text = "Email")
            }
            item {
                Text(text = "Peter_Christiansen@gmail.com")
            }
        }
    }
    item {
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.surface)
                .padding(10.dp, 5.dp)
        ) {
            item {
                Text(text = "Age")
            }
            item {
                Text(text = "24")
            }
        }
    }
    item {
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.surface)
                .padding(10.dp, 5.dp)
        ) {
            item {
                Text(text = "Sex")
            }
            item {
                Text(text = "Male")
            }
        }
    }
    item {
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.surface)
                .padding(10.dp, 5.dp)
        ) {
            item {
                Text(text = "Postcode")
            }
            item {
                Text(text = "6400")
            }
        }
    }
        //buttons
        item {
            var selected by remember { mutableStateOf(false) }
            val color = if (selected) Color.Magenta else Color.Gray

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(70.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(40.dp, 5.dp)
            ) {
                item {
                    val Bactive = true;
                    Button(onClick = {selected = !selected   },
                        colors = ButtonDefaults.buttonColors(backgroundColor = color)) {
                        Text("My events")
                   }
                }
                item {
                    Button(onClick = {selected = !selected   },
                        colors = ButtonDefaults.buttonColors(backgroundColor = color)) {
                        Text("Event history")
                    }
                }
            }
        }
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

        //used as padding
        item { Text(text = "")  }
        //used as padding
        item { Text(text = "")  }
        //used as padding
        item { Text(text = "")  }

}
}


