package com.example.sensimate.di

import android.content.Context
import androidx.room.Room
import com.example.sensimate.core.Constants.Companion.SURVEY_TABLE
import com.example.sensimate.data.network.SurveyDao
import com.example.sensimate.data.network.SurveyDb
import com.example.sensimate.data.repository.SurveyRepositoryImpl
import com.example.sensimate.domain.repository.SurveyRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    fun provideSurveyDb(
        @ApplicationContext
        context : Context
    ) = Room.databaseBuilder(
        context,
        SurveyDb::class.java,
        SURVEY_TABLE
    ).build()

    @Provides
    fun provideSurveyDao(
        surveyDb: SurveyDb
    ) = surveyDb.surveyDao()

    @Provides
    fun provideSurveyRepository(
        surveyDao: SurveyDao
    ): SurveyRepository = SurveyRepositoryImpl(
        surveyDao = surveyDao
    )
}