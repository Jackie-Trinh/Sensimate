package com.example.sensimate.domain.repository

import com.example.sensimate.domain.model.Event
import com.example.sensimate.domain.model.Question
import kotlinx.coroutines.flow.Flow


typealias Events = List<Event>
typealias Questions = List<Question>

interface EventRepository {
    //Events
    fun getEventsFromRoom(): Flow<Events>

    fun getEventFromRoom(id: Int): Event

    fun addEventToRoom(event: Event)

    fun updateEventInRoom(event: Event)

    fun deleteEventFromRoom(event: Event)

    //Questions
    fun getQuestionsFromEventIdFromRoom(id: Int): Flow<Questions>

    fun getQuestionFromRoom(id: Int, questionNumber: Int): Question

    fun addQuestionToRoom(question: Question)

    fun updateQuestionInRoom(question: Question)

    fun deleteQuestionFromRoom(question: Question)
}