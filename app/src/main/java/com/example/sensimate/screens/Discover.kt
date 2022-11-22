package com.example.sensimate.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
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
import com.example.sensimate.NavRoutes


@Composable
fun Discover(navController: NavController) {
    EventsCard(navController = navController)
}

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

