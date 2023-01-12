package com.example.sensimate.data.repository

import com.example.sensimate.data.network.EventDao
import com.example.sensimate.domain.model.Event
import com.example.sensimate.domain.repository.EventRepository


class EventRepositoryImpl(
    private val eventDao: EventDao
) : EventRepository {
    override fun getEventsFromRoom() = eventDao.getEvents()

    override fun getEventFromRoom(id: Int) = eventDao.getEvent(id)

    override fun addEventToRoom(event: Event) = eventDao.addEvent(event)

    override fun updateEventInRoom(event: Event) = eventDao.updateEvent(event)

    override fun deleteEventFromRoom(event: Event) = eventDao.deleteEvent(event)
}