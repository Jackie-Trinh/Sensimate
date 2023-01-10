package com.example.sensimate.screens.myEvents

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sensimate.core.Constants
import com.example.sensimate.domain.model.Event
import com.example.sensimate.domain.repository.EventRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyEventsViewModel @Inject constructor(
    private val repo: EventRepository
) : ViewModel() {

    //Event
    var event by mutableStateOf(Event(0, Constants.NO_VALUE, Constants.NO_VALUE, Constants.NO_VALUE, Constants.NO_VALUE, false, 0))
        private set

    val events = repo.getEventsFromRoom()

    fun getEvent(id: Int) = viewModelScope.launch(Dispatchers.IO) {
        event = repo.getEventFromRoom(id)
    }

}