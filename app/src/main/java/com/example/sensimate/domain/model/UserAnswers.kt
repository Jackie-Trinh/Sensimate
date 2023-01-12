package com.example.sensimate.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.sensimate.core.Constants

@Entity(tableName = Constants.USER_ANSWERS_TABLE)
data class UserAnswers(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val userId: Int,

    val answers: List<Int>,
    val listOf: List<UserAnswersItem>, //placeholder for survey
)

//placeholder for survey
data class UserAnswersItem(
    val id: Int,
    val userId: Int,

    val answers: List<Int>,
)