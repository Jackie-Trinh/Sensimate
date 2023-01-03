package com.example.sensimate.data

import androidx.room.*
import androidx.room.OnConflictStrategy.IGNORE
import com.example.sensimate.core.Constants.Companion.SURVEY_TABLE
import com.example.sensimate.domain.Survey
import kotlinx.coroutines.flow.Flow


@Dao
interface SurveyDao {
    @Query("SELECT * FROM $SURVEY_TABLE ORDER BY id ASC")
    fun getSurveys(): Flow<List<Survey>>

    @Query("SELECT * FROM $SURVEY_TABLE WHERE id = :id")
    fun getSurvey(id: Int): Flow<Survey>

    @Insert(onConflict = IGNORE)
    fun addSurvey(survey: Survey)

    @Update
    fun updateSurvey(survey: Survey)

    @Delete
    fun deleteSurvey(survey: Survey)
}