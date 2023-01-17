package com.example.sensimate.model2.service


import com.example.sensimate.model2.Event
import com.example.sensimate.model2.Question
import kotlinx.coroutines.flow.Flow

interface StorageService {
    //<-- Events -->
    val events: Flow<List<Event>>

    suspend fun getEvent(eventId: String): Event?

    suspend fun saveEvent(event: Event): String
    suspend fun updateEvent(event: Event)
    suspend fun deleteEvent(eventId: String)
//    suspend fun deleteAllForUser(userId: String)

    //<-- Question -->
    suspend fun getQuestionsForEvent(eventId: String): ArrayList<Question>

    suspend fun getQuestion(eventId: String, questionId: String): Question?

    suspend fun saveQuestion(eventId: String, question: Question): String
    suspend fun updateQuestion(eventId: String, question: Question)
    suspend fun deleteQuestion(eventId: String, questionId: String)
//    suspend fun deleteAllQuestionsForUser(userId: String)
}
