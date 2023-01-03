package com.example.sensimate.domain

import kotlinx.coroutines.flow.Flow

interface SurveyRepository {
    suspend fun getSurveyFromRoom(): Flow<List<Survey>>

    suspend fun getSurveyFromRoom(id: Int): Flow<Survey>

    suspend fun addSurveyToRoom(survey: Survey)

    suspend fun updateSurveyInRoom(survey: Survey)

    suspend fun deleteSurveyFromRoom(survey: Survey)
}