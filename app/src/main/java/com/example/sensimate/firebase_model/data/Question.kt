package com.example.sensimate.firebase_model.data

import com.google.firebase.firestore.DocumentId

data class Question(
    @DocumentId val questionId: String = "",
    //TODO: add
    //val questionImage: String = "",
    val questionText: String = "",

    val questionType: String = "",
    val answerOptions: List<String> = emptyList(),





    )