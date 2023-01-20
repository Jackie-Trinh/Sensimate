package com.example.sensimate.firebase_model.service


import com.example.sensimate.firebase_model.data.Event
import com.example.sensimate.firebase_model.data.Question
import com.example.sensimate.firebase_model.data.UserAnswer
import com.example.sensimate.firebase_model.data.UserData
import kotlinx.coroutines.flow.Flow

interface StorageService {
    //<-- UserData -->
//    val userDatas: Flow<List<UserData>>

    suspend fun getUserData(userDataId: String): UserData?

    suspend fun saveUserData(userData: UserData)

//    suspend fun saveUserData1(userData: UserData)
    suspend fun updateUserData(userData: UserData)
    suspend fun deleteUserData(userDataId: String)


    //<-- Events -->
    val events: Flow<List<Event>>

    suspend fun getEventsForUser(userData: UserData): ArrayList<Event>

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

    suspend fun deleteAllQuestionsForEvent(eventId: String)

    //<-- UserAnswer -->
    suspend fun getUserAnswerForEvent(eventId: String): ArrayList<UserAnswer>

    suspend fun getUserAnswer(eventId: String, userAnswerId: String): UserAnswer?

    suspend fun saveUserAnswer(eventId: String, userAnswer: UserAnswer): String
    suspend fun updateUserAnswer(eventId: String, userAnswer: UserAnswer)
    suspend fun deleteUserAnswer(eventId: String, userAnswerId: String)

    suspend fun deleteAllUserAnswerForEvent(eventId: String)


}
