package com.example.sensimate.model2

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.sensimate.core.Constants
import com.google.firebase.firestore.DocumentId

data class Question(
    @DocumentId val questionId: String = "",
    //TODO: remove?
    //val questionImage: String = "",
    val questionText: String = "",

    val questionType: String = "",
    val answerOptions: List<String> = emptyList(),





    )