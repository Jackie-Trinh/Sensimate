package com.example.sensimate.screens.edit_event

import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.Start
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sensimate.R
import com.example.sensimate.core.*
import com.example.sensimate.core.composables.BasicField
import com.example.sensimate.core.composables.RegularCardEditor
import com.example.sensimate.firebase_model.data.Event
import com.example.sensimate.R.string as AppText
import com.example.sensimate.screens.edit_event.components.ActionToolbar
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat


@Composable
@ExperimentalMaterialApi
fun EditEventScreen(
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
        horizontalAlignment = CenterHorizontally
    ) {
        ActionToolbar(
            title = AppText.edit_event,
            modifier = Modifier.toolbarActions(),
            endActionIcon = Icons.Outlined.Check,
            endAction = { viewModel.onDoneClick(popUpScreen) },
            backAction = { viewModel.onBackClick(popUpScreen) }
        )

        Spacer(modifier = Modifier.spacer())



        Row(modifier = Modifier
            .padding(start = 28.dp, end = 28.dp)
            .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {

            val visibilityText =
                if (event.eventPublic) AppText.public_event
                else AppText.hidden_event

            val visibilityIcon =
                if (event.eventPublic) R.drawable.public_40px
                else R.drawable.public_off_40px

            //Change visibility
            EditEventScreenIconButton(
                icon = visibilityIcon,
                onClick = { viewModel.onEventPublicClick(!event.eventPublic) },
                iconText = visibilityText
            )

            //Go to survey
            EditEventScreenIconButton(
                icon = R.drawable.quiz_40px,
                onClick = { viewModel.onEventPublicClick(!event.eventPublic) },
                iconText = AppText.survey
            )

            val showDialogState: Boolean by viewModel.showEditImageDialog.collectAsState()
            SimpleAlertDialog(
                show = showDialogState,
                onDismiss = viewModel::onEditImageDialogDismiss,
                onConfirm = { imageUrl -> viewModel.onEditImageDialogConfirm(imageUrl) }
            )

            //Edit image
            EditEventScreenIconButton(
                icon = R.drawable.image_40px,
                onClick = { viewModel.onOpenEditImageDialogClick() },
                iconText = AppText.image
            )

            //Delete event
            EditEventScreenIconButton(
                icon = R.drawable.delete_40px,
                onClick = { viewModel.onDeleteEventClick(event, popUpScreen) },
                iconText = AppText.delete
            )

        }

        Spacer(modifier = Modifier.smallSpacer())

        val fieldModifier = Modifier.fieldModifier()

        Text(text = stringResource(id = AppText.title),
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier
                .align(Start)
                .padding(start = 18.dp))
        BasicField(AppText.title, event.title, viewModel::onTitleChange, fieldModifier)

        Text(text = stringResource(id = AppText.address),
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier
                .align(Start)
                .padding(start = 18.dp))
        BasicField(AppText.address, event.address, viewModel::onAddressChange, fieldModifier)

        Spacer(modifier = Modifier.smallSpacer())

        CardEditors(event, viewModel::onDateChange, viewModel::onTimeChange)
//        CardSelectors(event, viewModel::onPriorityChange, viewModel::onFlagToggle)

        Spacer(modifier = Modifier.smallSpacer())

        Text(text = stringResource(id = AppText.description),
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier
                .align(Start)
                .padding(start = 18.dp))
        BasicField(AppText.description, event.description, viewModel::onDescriptionChange, fieldModifier)

    }
}

@OptIn(ExperimentalMaterialApi::class)
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleAlertDialog(
    show: Boolean,
    onDismiss: () -> Unit,
    onConfirm: (String) -> Unit
) {

    var imageUrl by remember { mutableStateOf("") }

    if (show) {
        AlertDialog(
            onDismissRequest = onDismiss,
            confirmButton = {
                TextButton(
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                    onClick = { onConfirm(imageUrl) })
                { Text(
                    color = Color.White,
                    text = "OK") }
            },
            dismissButton = {
                TextButton(
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                    onClick = onDismiss
                )
                { Text(
                    color = Color.White,
                    text = "Cancel"
                ) }
            },
            title = { Text(text = "Please enter image url:") },
            text = {
                OutlinedTextField(
                    singleLine = true,
                    value = imageUrl,
                    onValueChange = { imageUrl = it },
                    placeholder = { Text(text = "image url") }
                ) }
        )
    }
}
//TODO Might use this
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
@Composable
fun EditEventScreenIconButton(
    icon: Int,
    onClick: () -> Unit,
    iconText: Int,
) {
    Column {
        IconButton(
            onClick = { onClick() })
        {
            Icon(
                painter = painterResource(icon),
                contentDescription = "Icon")
        }
        Text(
            text = stringResource(id = iconText),
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier
                .align(CenterHorizontally)
                .offset(y = (-2).dp)
        )
    }
}

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
