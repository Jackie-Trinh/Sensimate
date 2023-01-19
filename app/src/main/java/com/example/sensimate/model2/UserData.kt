package com.example.sensimate.model2

import com.google.firebase.firestore.DocumentId

data class UserData(
    @DocumentId val userId: String = "",
    val email: String = "",
    val age: Int = 0,
    val sex: String = "",
    val postcode: String = "",
)
