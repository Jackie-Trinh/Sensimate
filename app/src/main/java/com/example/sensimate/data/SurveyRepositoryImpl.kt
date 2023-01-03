package com.example.sensimate.data

import com.example.sensimate.domain.Survey
import com.example.sensimate.domain.SurveyRepository

class SurveyRepositoryImpl(
    private val surveyDao: SurveyDao
) : SurveyRepository {
    override suspend fun getSurveyFromRoom() = surveyDao.getSurveys()

    override suspend fun getSurveyFromRoom(id: Int) = surveyDao.getSurvey(id)

    override suspend fun addSurveyToRoom(survey: Survey) = surveyDao.addSurvey(survey)

    override suspend fun updateSurveyInRoom(survey: Survey) = surveyDao.updateSurvey(survey)

    override suspend fun deleteSurveyFromRoom(survey: Survey) = surveyDao.deleteSurvey(survey)
}