package com.example.sensimate.model2

import com.google.firebase.firestore.DocumentId

data class Event(
    @DocumentId val eventId: String = "",
    val title: String = "",

    val address: String = "",
    val date: String = "",
    val time: String = "",
    val description: String = "",

    val eventPublic: Boolean = false,


    val eventImage: String = "",
    val product: String = "",
    val disclaimerForAllergies: String = "",

    val surveyAvailable: Boolean = false,

//    //Survey

//    val numberOfQuestions: Int,
)