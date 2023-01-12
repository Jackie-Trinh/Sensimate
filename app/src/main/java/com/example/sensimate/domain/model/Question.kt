package com.example.sensimate.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.sensimate.core.Constants

@Entity(tableName = Constants.QUESTION_TABLE)
data class Question(
    @PrimaryKey
    val id: Int,
    val questionNumber: Int,

    //val questionType: String,
    //val questionImage: //???,

    val questionText: String,

    val answerOptions: List<String>,
    val listOf: List<QuestionItem>, //placeholder question

    )

//placeholder question for survey
data class QuestionItem(
    val id: Int,
    val questionNumber: Int,

    //val questionType: String,
    //val questionImage: //???,

    val questionText: String,

    val answerOptions: List<String>,

    )