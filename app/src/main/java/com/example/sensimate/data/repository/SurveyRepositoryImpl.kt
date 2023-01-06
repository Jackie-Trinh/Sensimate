package com.example.sensimate.data.repository

import com.example.sensimate.data.network.SurveyDao
import com.example.sensimate.domain.model.Survey
import com.example.sensimate.domain.repository.SurveyRepository


class SurveyRepositoryImpl(
    private val surveyDao: SurveyDao
) : SurveyRepository {
    override fun getSurveysFromRoom() = surveyDao.getSurveys()

    override fun getSurveyFromRoom(id: Int) = surveyDao.getSurvey(id)

    override fun addSurveyToRoom(survey: Survey) = surveyDao.addSurvey(survey)

    override fun updateSurveyInRoom(survey: Survey) = surveyDao.updateSurvey(survey)

    override fun deleteSurveyFromRoom(survey: Survey) = surveyDao.deleteSurvey(survey)
}