package com.example.sensimate.screens.discover

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sensimate.core.Constants
import com.example.sensimate.domain.model.Event
import com.example.sensimate.domain.repository.EventRepository
import javax.inject.Inject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@HiltViewModel
class DiscoverViewModel @Inject constructor(
    private val repo: EventRepository
) : ViewModel() {

    //Dialog
    var openDialog by mutableStateOf(false)

    fun openDialog() {
        openDialog = true
    }

    fun closeDialog() {
        openDialog = false
    }

    //Survey
    var event by mutableStateOf(Event(0, Constants.NO_VALUE, Constants.NO_VALUE, Constants.NO_VALUE, Constants.NO_VALUE, false))
        private set

    val events = repo.getEventsFromRoom()

    fun getEvent(id: Int) = viewModelScope.launch(Dispatchers.IO) {
        event = repo.getEventFromRoom(id)
    }

    fun addEvent(event: Event) = viewModelScope.launch(Dispatchers.IO) {
        repo.addEventToRoom(event)
    }

    fun updateEvent(event: Event) = viewModelScope.launch(Dispatchers.IO) {
        repo.updateEventInRoom(event)
    }

    fun deleteEvent(event: Event) = viewModelScope.launch(Dispatchers.IO) {
        repo.deleteEventFromRoom(event)
    }

    fun updateTitle(title: String) {
        event = event.copy(
            title = title
        )
    }

    fun updateAddress(address: String) {
        event = event.copy(
            address = address
        )
    }

}

