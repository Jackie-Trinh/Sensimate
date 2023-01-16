package com.example.sensimate.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.sensimate.core.Constants.Companion.EVENT_TABLE

@Entity(tableName = EVENT_TABLE)
data class Event(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val address: String,
    val date: String,
    val description: String,
    //val eventImage: //???,

    //Survey
    val hasSurvey: Boolean,
    val numberOfQuestions: Int,
)