package com.example.sensimate.firebase_model.data

import com.google.firebase.firestore.DocumentId

data class UserData(
    @DocumentId val userId: String = "",
    val email: String = "",
    val age: String = "",
    val sex: String = "",
    val postal: String = "",

    val admin: Boolean = false,
    val followedEventIds: List<String> = emptyList(),

    )
