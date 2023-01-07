package com.example.sensimate.data.network

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.sensimate.domain.model.Survey

@Database(entities = [Survey::class], version = 1, exportSchema = false)
abstract class SurveyDb : RoomDatabase() {
    abstract fun surveyDao(): SurveyDao
}