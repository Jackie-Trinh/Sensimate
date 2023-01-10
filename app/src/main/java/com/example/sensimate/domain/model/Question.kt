package com.example.sensimate.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.sensimate.core.Constants

@Entity(tableName = Constants.QUESTION_TABLE, primaryKeys = ["id", "questionNumber"])
data class Question(
    val id: Int,
    val questionNumber: Int,

    //val questionType: String,
    //val questionImage: //???,

    val questionText: String,

//    val answerOptions: List<String>,


)