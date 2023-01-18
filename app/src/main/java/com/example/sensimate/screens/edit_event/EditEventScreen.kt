package com.example.sensimate.screens.edit_event

import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Alignment.Companion.Start
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.sensimate.R
import com.example.sensimate.core.*
import com.example.sensimate.core.composables.BasicField
import com.example.sensimate.core.composables.RegularCardEditor
import com.example.sensimate.model2.Event
import com.example.sensimate.R.string as AppText
import com.example.sensimate.screens.edit_event.components.ActionToolbar
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat


@Composable
@ExperimentalMaterialApi
fun EditEventScreen(
    navController: NavController,
    popUpScreen: () -> Unit,
    eventId: String,
    viewModel: EditEventViewModel = hiltViewModel()
) {
    val event by viewModel.event

    LaunchedEffect(Unit) { viewModel.initialize(eventId) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ActionToolbar(
            title = AppText.edit_event,
            modifier = Modifier.toolbarActions(),
            endActionIcon = Icons.Outlined.Check,
            endAction = { viewModel.onDoneClick(popUpScreen) },
            backAction = { viewModel.onBackClick(popUpScreen) }
        )

        Spacer(modifier = Modifier.spacer())

        val icon =
            if (event.public) R.drawable.public_40px
            else R.drawable.public_off_40px

        val visibility =
            if (event.public) AppText.public_event
            else AppText.hidden_event

        Row(modifier = Modifier
            .padding(start = 28.dp, end = 28.dp)
            .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {

            Column () {
                IconButton(onClick = { viewModel.onPublicChange(!event.public) }) {
                    Icon(painter = painterResource(id = icon), contentDescription = "Visibility")
                }
                Text(text = stringResource(id = visibility),
                    style = MaterialTheme.typography.subtitle2,
                    modifier = Modifier.align(CenterHorizontally)
                        .offset(y = (-2).dp)
                )
            }

            Column {
                IconButton(onClick = { viewModel.onPublicChange(!event.public) }) {
                    Icon(painter = painterResource(R.drawable.quiz_40px), contentDescription = "Survey")
                }
                Text(text = stringResource(id = AppText.survey),
                    style = MaterialTheme.typography.subtitle2,
                    modifier = Modifier.align(CenterHorizontally)
                        .offset(y = (-2).dp)
                )
            }

            Column {
                //TODO: Pop up that asks: "Are you sure you want to delete?"
                IconButton(onClick = { viewModel.onDeleteEventClick(event, popUpScreen) }) {
                    Icon(painter = painterResource(R.drawable.delete_40px), contentDescription = "Delete")
                }
                Text(text = stringResource(id = AppText.delete),
                    style = MaterialTheme.typography.subtitle2,
                    modifier = Modifier.align(CenterHorizontally)
                        .offset(y = (-2).dp)
                )
            }

        }

        Spacer(modifier = Modifier.smallSpacer())

        val fieldModifier = Modifier.fieldModifier()

        Text(text = stringResource(id = AppText.title),
            style = MaterialTheme.typography.subtitle2,
            modifier = Modifier
                .align(Start)
                .padding(start = 18.dp))
        BasicField(AppText.title, event.title, viewModel::onTitleChange, fieldModifier)

        Text(text = stringResource(id = AppText.address),
            style = MaterialTheme.typography.subtitle2,
            modifier = Modifier
                .align(Start)
                .padding(start = 18.dp))
        BasicField(AppText.address, event.address, viewModel::onAddressChange, fieldModifier)

        Spacer(modifier = Modifier.smallSpacer())

        CardEditors(event, viewModel::onDateChange, viewModel::onTimeChange)
//        CardSelectors(event, viewModel::onPriorityChange, viewModel::onFlagToggle)

        Spacer(modifier = Modifier.smallSpacer())

        Text(text = stringResource(id = AppText.description),
            style = MaterialTheme.typography.subtitle2,
            modifier = Modifier
                .align(Start)
                .padding(start = 18.dp))
        BasicField(AppText.description, event.description, viewModel::onDescriptionChange, fieldModifier)

    }
}

@ExperimentalMaterialApi
@Composable
private fun CardEditors(
    event: Event,
    onDateChange: (Long) -> Unit,
    onTimeChange: (Int, Int) -> Unit
) {
    val activity = LocalContext.current as AppCompatActivity

    RegularCardEditor(AppText.date, R.drawable.calendar_month_24px, event.date, Modifier.card()) {
        showDatePicker(activity, onDateChange)
    }

    RegularCardEditor(AppText.time, R.drawable.schedule_24px, event.time, Modifier.card()) {
        showTimePicker(activity, onTimeChange)
    }
}

//@Composable
//@ExperimentalMaterialApi
//private fun CardSelectors(
//    event: Event,
//    onPriorityChange: (String) -> Unit,
//    onFlagToggle: (String) -> Unit
//) {
//    val prioritySelection = Priority.getByName(task.priority).name
//    CardSelector(AppText.priority, Priority.getOptions(), prioritySelection, Modifier.card()) {
//            newValue ->
//        onPriorityChange(newValue)
//    }
//
//    val flagSelection = EditFlagOption.getByCheckedState(task.flag).name
//    CardSelector(AppText.flag, EditFlagOption.getOptions(), flagSelection, Modifier.card()) { newValue
//        ->
//        onFlagToggle(newValue)
//    }
//}

private fun showDatePicker(activity: AppCompatActivity?, onDateChange: (Long) -> Unit) {
    val picker = MaterialDatePicker.Builder.datePicker().build()

    activity?.let {
        picker.show(it.supportFragmentManager, picker.toString())
        picker.addOnPositiveButtonClickListener { timeInMillis -> onDateChange(timeInMillis) }
    }
}

private fun showTimePicker(activity: AppCompatActivity?, onTimeChange: (Int, Int) -> Unit) {
    val picker = MaterialTimePicker.Builder().setTimeFormat(TimeFormat.CLOCK_24H).build()

    activity?.let {
        picker.show(it.supportFragmentManager, picker.toString())
        picker.addOnPositiveButtonClickListener { onTimeChange(picker.hour, picker.minute) }
    }
}
