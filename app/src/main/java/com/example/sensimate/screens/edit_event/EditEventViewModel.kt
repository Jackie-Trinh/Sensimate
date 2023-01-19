package com.example.sensimate.screens.edit_event

import androidx.compose.runtime.mutableStateOf
import com.example.sensimate.core.Constants.Companion.EVENT_DEFAULT_ID
import com.example.sensimate.core.idFromParameter
import com.example.sensimate.model2.Event
import com.example.sensimate.model2.service.StorageService
import com.example.sensimate.screens.SensiMateViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class EditEventViewModel @Inject constructor(
//    logService: LogService,
    private val storageService: StorageService,
) : SensiMateViewModel() {
    val event = mutableStateOf(Event())

    fun initialize(eventId: String) {
        launchCatching {
            if (eventId != EVENT_DEFAULT_ID) {
                event.value = storageService.getEvent(eventId.idFromParameter()) ?: Event()
            }
        }
    }



    fun onTitleChange(newValue: String) {
        event.value = event.value.copy(title = newValue)
    }

    fun onDescriptionChange(newValue: String) {
        event.value = event.value.copy(description = newValue)
    }

    fun onAddressChange(newValue: String) {
        event.value = event.value.copy(address = newValue)
    }


    fun onEventPublicClick(newValue: Boolean) {
        event.value = event.value.copy(eventPublic = newValue)
    }


    private val _showEditImageDialog = MutableStateFlow(false)
    val showEditImageDialog: StateFlow<Boolean> = _showEditImageDialog.asStateFlow()

    fun onOpenEditImageDialogClick() {
        _showEditImageDialog.value = true
    }

    fun onEditImageDialogConfirm(imageURL: String) {
        _showEditImageDialog.value = false
        event.value = event.value.copy(eventImage = imageURL)
    }

    fun onEditImageDialogDismiss() {
        _showEditImageDialog.value = false
    }

    fun onDeleteEventClick(event: Event, popUpScreen: () -> Unit) {
        launchCatching {
            storageService.deleteEvent(event.eventId)
            popUpScreen()
        }
    }

//    fun onEditSurveyClick(event: Event, popUpScreen: () -> Unit) {
//        launchCatching {
//            storageService.deleteEvent(event.eventId)
//            popUpScreen()
//        }
//    }

    fun onDateChange(newValue: Long) {
        val calendar = Calendar.getInstance(TimeZone.getTimeZone(UTC))
        calendar.timeInMillis = newValue
        val newDueDate = SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH).format(calendar.time)
        event.value = event.value.copy(date = newDueDate)
    }

    fun onTimeChange(hour: Int, minute: Int) {
        val newDueTime = "${hour.toClockPattern()}:${minute.toClockPattern()}"
        event.value = event.value.copy(time = newDueTime)
    }

    fun onBackClick(popUpScreen: () -> Unit) {
        launchCatching {
            popUpScreen()
        }
    }

    fun onDoneClick(popUpScreen: () -> Unit) {
        launchCatching {
            val editedEvent = event.value
            if (editedEvent.eventId.isBlank()) {
                storageService.saveEvent(editedEvent)
            } else {
                storageService.updateEvent(editedEvent)
            }
            popUpScreen()
        }
    }


    private fun Int.toClockPattern(): String {
        return if (this < 10) "0$this" else "$this"
    }

    companion object {
        private const val UTC = "UTC"
        private const val DATE_FORMAT = "EEE, d MMM yyyy"
    }
}
