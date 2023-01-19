package com.example.sensimate.core.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sensimate.core.contextMenu
import com.example.sensimate.firebase_model.data.Event
import java.lang.StringBuilder

@Composable
@ExperimentalMaterialApi
fun EventItem(
    event: Event,
    options: List<String>,
    onEventClick: (String) -> Unit,
    onActionClick: (String) -> Unit
) {
    Card(
        backgroundColor = MaterialTheme.colors.background,
        modifier = Modifier
            .padding(8.dp, 0.dp, 8.dp, 8.dp)
//            .height(height = 100.dp)
            .clickable { onEventClick("") },
        elevation = 6.dp,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
                .padding(16.dp, 8.dp)
        ) {
//            Checkbox(
//                checked = event.completed,
//                onCheckedChange = { onCheckChange() },
//                modifier = Modifier.padding(8.dp, 0.dp)
//            )

            Column(modifier = Modifier.weight(1f)) {
                Text(text = event.title, style = MaterialTheme.typography.h5)
                Text(text = event.address, fontSize = 16.sp)
                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                    Text(text = getDueDateAndTime(event), fontSize = 16.sp)
                }
            }

//            if (task.flag) {
//                Icon(
//                    painter = painterResource(AppIcon.ic_flag),
//                    tint = DarkOrange,
//                    contentDescription = "Flag"
//                )
//            }

            DropdownContextMenu(options, Modifier.contextMenu(), onActionClick)
        }
    }
}

private fun getDueDateAndTime(event: Event): String {
    val stringBuilder = StringBuilder("")

//    if (event.hasDueDate()) {
    stringBuilder.append(event.date)
    stringBuilder.append(" ")
//    }

//    if (event.hasDueTime()) {
    stringBuilder.append("at ")
    stringBuilder.append(event.time)
//    }

    return stringBuilder.toString()
}
