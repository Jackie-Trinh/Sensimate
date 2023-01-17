package com.example.sensimate.screens.event_manager

import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.ImageLoader
import coil.request.ImageRequest
import com.example.sensimate.domain.model.Event
import com.example.sensimate.domain.model.Question
import com.example.sensimate.domain.repository.EventRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.InputStream
import java.net.URL
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
    var question by mutableStateOf(Question(id = 0, questionNumber = 0, questionText = "", answerOptions = ArrayList(), questionType = "SingleChoice"))
        private set

    var questions = repo.getQuestionsFromEventIdFromRoom(id = event.id)

    fun getQuestions(id: Int) = viewModelScope.launch(Dispatchers.IO) {
        questions = repo.getQuestionsFromEventIdFromRoom(id = id)
    }

    fun getQuestion(id: Int, questionNumber: Int) = viewModelScope.launch(Dispatchers.IO) {
        question = repo.getQuestionFromRoom(id, questionNumber)
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

    fun updateQuestionAnswerOptions(AnswerOptions: ArrayList<String>) {
        question = question.copy(
            answerOptions = AnswerOptions
        )
        updateQuestion(question)
    }

    fun getImageFromURL(context: Context, imageURL: URL) {
        val loader = ImageLoader(context)
        val req = ImageRequest.Builder(context)
            .data("https://images.dog.ceo/breeds/saluki/n02091831_3400.jpg") // demo link
            .target { result ->
                val bitmap = (result as BitmapDrawable).bitmap
            }
            .build()

        val disposable = loader.enqueue(req)
    }




}