package com.example.sensimate.domain.repository

import com.example.sensimate.domain.model.Event
import kotlinx.coroutines.flow.Flow


typealias Events = List<Event>

interface EventRepository {
    fun getEventsFromRoom(): Flow<Events>

    fun getEventFromRoom(id: Int): Event

    fun addEventToRoom(event: Event)

    fun updateEventInRoom(event: Event)

    fun deleteEventFromRoom(event: Event)
}