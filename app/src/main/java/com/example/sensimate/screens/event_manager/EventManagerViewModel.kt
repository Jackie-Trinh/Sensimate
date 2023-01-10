package com.example.sensimate.screens.event_manager

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sensimate.core.Constants
import com.example.sensimate.domain.model.Event
import com.example.sensimate.domain.model.Question
import com.example.sensimate.domain.repository.EventRepository
import com.example.sensimate.domain.repository.Questions
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventManagerViewModel @Inject constructor(
    private val repo: EventRepository
) : ViewModel() {

    //Event
    var event by mutableStateOf(Event(0, "", "", "", "", false, 0))
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

    fun updateEventTitle(title: String) {
        event = event.copy(
            title = title
        )
    }

    fun updateEventAddress(address: String) {
        event = event.copy(
            address = address
        )
    }

    fun updateEventNumberOfQuestions(numberOfQuestions: Int) {
        event = event.copy(
            numberOfQuestions = numberOfQuestions
        )
        updateEvent(event)
    }



    //Survey
    var question by mutableStateOf(Question(id = 0, questionNumber = 0, questionText = ""))
        private set

    var questions = repo.getQuestionsFromEventIdFromRoom(id = event.id)

    fun getQuestions(id: Int) = viewModelScope.launch(Dispatchers.IO) {
        questions = repo.getQuestionsFromEventIdFromRoom(id = id)
    }

    fun addQuestion(question: Question) = viewModelScope.launch(Dispatchers.IO) {
        repo.addQuestionToRoom(question)
    }

    fun updateQuestion(question: Question) = viewModelScope.launch(Dispatchers.IO) {
        repo.updateQuestionInRoom(question)
    }

    fun deleteQuestion(question: Question) = viewModelScope.launch(Dispatchers.IO) {
        repo.deleteQuestionFromRoom(question)
    }

}