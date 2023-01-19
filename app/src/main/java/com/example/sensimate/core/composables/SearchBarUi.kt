package com.example.sensimate.core.composables

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sensimate.firebase_model.data.Event
import java.util.*



//Create the search bar
@Composable
fun SearchView(state: MutableState<TextFieldValue>) {
    TextField(
        value = state.value,
        onValueChange = { value ->
            state.value = value
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .border(width = 2.dp,
        color = Color.Gray,
        shape = RoundedCornerShape(percent = 100)
            ),
        textStyle = TextStyle(color = Color.Black, fontSize = 18.sp),
        leadingIcon = {
            Icon(
                Icons.Default.Search,
                contentDescription = "",
                modifier = Modifier
                    .padding(15.dp)
                    .size(24.dp)
            )
        },
        placeholder = { Text("Search here...") },
        trailingIcon = {
            if (state.value != TextFieldValue("")) {
                IconButton(
                    onClick = {
                        state.value = TextFieldValue("") // Remove text from TextField when you press the 'X' icon
                    }
                ) {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = "",
                        modifier = Modifier
                            .padding(15.dp)
                            .size(24.dp)
                    )
                }
            }
        },
        singleLine = true,
        shape = RoundedCornerShape(percent = 100), // The TextFiled has rounded corners top left and right by default
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.Black,
            cursorColor = Color.Black,
            leadingIconColor = Color.Black,
            trailingIconColor = Color.Black,
            backgroundColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}

//search function
@Composable
fun SearchFunction(events: List<Event>,
                   textState: MutableState<TextFieldValue>,
                   list: MutableList<Event>) {

    //clear list before use
    while(list.isNotEmpty()){
        list.removeAt(0)
    }
        //check for the searched word in title
    for (item in events) { // first make a copy of the list
        if (item.title.lowercase(Locale.getDefault()).contains(textState.value.text.lowercase())||
            item.address.lowercase(Locale.getDefault()).contains(textState.value.text.lowercase())||
            item.description.lowercase(Locale.getDefault()).contains(textState.value.text.lowercase())||
            item.date.lowercase(Locale.getDefault()).contains(textState.value.text.lowercase())) {
            list.add(item) // if the item contains that text add it to the list
        }
    }
}

