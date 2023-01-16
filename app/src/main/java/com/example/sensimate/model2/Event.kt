package com.example.sensimate.model2

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.sensimate.core.Constants
import com.google.firebase.firestore.DocumentId

data class Event(
    @DocumentId val id: String = "",
    val title: String = "",
    val address: String = "",
    val date: String = "",
    val time: String = "",
    val description: String = "",
    //val eventImage: //???,

//    //Survey
//    val hasSurvey: Boolean,
//    val numberOfQuestions: Int,
)