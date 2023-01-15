package com.example.sensimate.model2.service

import com.example.sensimate.model2.Event
import kotlinx.coroutines.flow.Flow

interface StorageService {
    val events: Flow<List<Event>>

    suspend fun getEvent(eventId: String): Event?

    suspend fun save(event: Event): String
    suspend fun update(event: Event)
    suspend fun delete(eventId: String)
    suspend fun deleteAllForUser(userId: String)
}
