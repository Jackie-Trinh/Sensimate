package com.example.sensimate.screens.survey

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sensimate.domain.model.Event
import com.example.sensimate.domain.repository.EventRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class SurveyViewModel @Inject constructor(
    private val repo: EventRepository
) : ViewModel() {

    //current page we are at
    var currentPage =  1
    //new page checker
    var newPageChecker = { mutableStateOf(true) }


    fun increaseCurrentPage() = viewModelScope.launch(Dispatchers.IO) {
        currentPage += 1
    }

    fun decreaseCurrentPage() = viewModelScope.launch(Dispatchers.IO) {
        currentPage -= 1
    }

    fun addEvent(event: Event) = viewModelScope.launch(Dispatchers.IO) {
        repo.addEventToRoom(event)
    }


}