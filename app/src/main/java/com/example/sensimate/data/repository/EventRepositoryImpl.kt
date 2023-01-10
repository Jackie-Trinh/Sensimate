package com.example.sensimate.data.repository

import com.example.sensimate.data.network.EventDao
import com.example.sensimate.domain.model.Event
import com.example.sensimate.domain.model.Question
import com.example.sensimate.domain.repository.EventRepository


class EventRepositoryImpl(
    private val eventDao: EventDao
) : EventRepository {
    //Events
    override fun getEventsFromRoom() = eventDao.getEvents()

    override fun getEventFromRoom(id: Int) = eventDao.getEvent(id)

    override fun addEventToRoom(event: Event) = eventDao.addEvent(event)

    override fun updateEventInRoom(event: Event) = eventDao.updateEvent(event)

    override fun deleteEventFromRoom(event: Event) = eventDao.deleteEvent(event)

    //Questions
    override fun getQuestionsFromEventIdFromRoom(id: Int) = eventDao.getQuestionsFromEventId(id)

    override fun addQuestionToRoom(question: Question) = eventDao.addQuestion(question)

    override fun updateQuestionInRoom(question: Question) = eventDao.updateQuestion(question)

    override fun deleteQuestionFromRoom(question: Question) = eventDao.deleteQuestion(question)
}