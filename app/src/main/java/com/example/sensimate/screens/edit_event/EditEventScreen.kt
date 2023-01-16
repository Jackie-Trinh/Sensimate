package com.example.sensimate.screens.edit_event

import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Check
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.sensimate.R
import com.example.sensimate.core.card
import com.example.sensimate.core.composables.BasicField
import com.example.sensimate.core.composables.RegularCardEditor
import com.example.sensimate.core.fieldModifier
import com.example.sensimate.core.spacer
import com.example.sensimate.core.toolbarActions
import com.example.sensimate.model2.Event
import com.example.sensimate.R.drawable as AppIcon
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
        modifier = Modifier.fillMaxWidth().fillMaxHeight().verticalScroll(rememberScrollState()),
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

        val fieldModifier = Modifier.fieldModifier()
        BasicField(AppText.title, event.title, viewModel::onTitleChange, fieldModifier)
        BasicField(AppText.description, event.description, viewModel::onDescriptionChange, fieldModifier)
        BasicField(AppText.address, event.address, viewModel::onAddressChange, fieldModifier)

        Spacer(modifier = Modifier.spacer())
        CardEditors(event, viewModel::onDateChange, viewModel::onTimeChange)
//        CardSelectors(event, viewModel::onPriorityChange, viewModel::onFlagToggle)

        Spacer(modifier = Modifier.spacer())
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

    RegularCardEditor(AppText.date, R.drawable.event_48px, event.date, Modifier.card()) {
        showDatePicker(activity, onDateChange)
    }

    RegularCardEditor(AppText.time, R.drawable.schedule_48px, event.time, Modifier.card()) {
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
