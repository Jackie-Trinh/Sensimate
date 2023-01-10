package com.example.sensimate.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.sensimate.core.Constants

@Entity(tableName = Constants.SURVEY_TABLE)
data class Survey(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val numberOfQuestions: Int,

)