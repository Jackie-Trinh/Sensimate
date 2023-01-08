package com.example.sensimate.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.sensimate.core.Constants

@Entity(tableName = Constants.EVENT_TABLE)
data class UserAnswers(
    @PrimaryKey(autoGenerate = true)
    val surveyID: Int,

    val answers: List<String>,
)