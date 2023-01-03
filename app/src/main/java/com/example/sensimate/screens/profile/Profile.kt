package com.example.sensimate.screens

import android.service.autofill.OnClickAction
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
    var visible by remember {
        mutableStateOf(true)
    }
    val colorOn = Color.Magenta;
    val colorOff = Color.Gray;

    Column(modifier = Modifier.fillMaxSize()) {
        ProfileCard()

        //buttons
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(70.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(40.dp, 5.dp)
        ) {
            if (visible){
                item {
                    val Bactive = true;
                    Button(onClick = {visible = !visible},
                        colors = ButtonDefaults.buttonColors(backgroundColor = colorOn)) {
                        Text("My events")
                    }
                }
                item {
                    Button(onClick = {visible = !visible},
                        colors = ButtonDefaults.buttonColors(backgroundColor = colorOff)) {
                        Text("Event history")
                    }
                }
            }else{
                item {
                    val Bactive = true;
                    Button(onClick = {visible = !visible},
                        colors = ButtonDefaults.buttonColors(backgroundColor = colorOff)) {
                        Text("My events")
                    }
                }
                item {
                    Button(onClick = {visible = !visible},
                        colors = ButtonDefaults.buttonColors(backgroundColor = colorOn)) {
                        Text("Event history")
                    }
                }
            }
        }

        //show profile or not based on buttons
        if (visible) {
            ProfileSelection()
        }else{
            ProfileSelection2()
        }
    }
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
}
}

@Composable
fun ProfileSelection() {
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

@Composable
fun ProfileSelection2() {
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
                        imageVector = Icons.Filled.Face,
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
                        imageVector = Icons.Filled.Face,
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
                        imageVector = Icons.Filled.Face,
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

