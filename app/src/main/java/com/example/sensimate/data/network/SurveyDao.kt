package com.example.sensimate.data.network

import androidx.room.*
import androidx.room.OnConflictStrategy.Companion.IGNORE
import com.example.sensimate.core.Constants.Companion.SURVEY_TABLE
import com.example.sensimate.domain.model.Survey
import com.example.sensimate.domain.repository.Surveys
import kotlinx.coroutines.flow.Flow


@Dao
interface SurveyDao {
    @Query("SELECT * FROM $SURVEY_TABLE ORDER BY id ASC")
    fun getSurveys(): Flow<Surveys>

    @Query("SELECT * FROM $SURVEY_TABLE WHERE id = :id")
    fun getSurvey(id: Int): Survey

    @Insert(onConflict = IGNORE)
    fun addSurvey(survey: Survey)

    @Update
    fun updateSurvey(survey: Survey)

    @Delete
    fun deleteSurvey(survey: Survey)

}

