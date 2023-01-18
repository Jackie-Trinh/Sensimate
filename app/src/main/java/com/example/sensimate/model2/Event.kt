package com.example.sensimate.model2

import com.google.firebase.firestore.DocumentId

data class Event(
    @DocumentId val eventId: String = "",
    val title: String = "",
    //TODO: Place instead of address?
    val address: String = "",
    val date: String = "",
    val time: String = "",
    val description: String = "",

    val public: Boolean = false,

    //TODO: Add
//    val eventImage: //???,
    //val product: String
    //val disclaimerForAllergies


//    //Survey
//    val hasSurvey: Boolean,
//    val numberOfQuestions: Int,
)