package com.example.sensimate.domain.repository

import com.example.sensimate.domain.model.Survey
import kotlinx.coroutines.flow.Flow


typealias Surveys = List<Survey>

interface SurveyRepository {
    fun getSurveysFromRoom(): Flow<Surveys>

    fun getSurveyFromRoom(id: Int): Survey

    fun addSurveyToRoom(survey: Survey)

    fun updateSurveyInRoom(survey: Survey)

    fun deleteSurveyFromRoom(survey: Survey)
}