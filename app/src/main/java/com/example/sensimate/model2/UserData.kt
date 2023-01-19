package com.example.sensimate.model2

import com.google.firebase.firestore.DocumentId

data class UserData(
    @DocumentId val userId: String = "",
    val username: String = "",
    val email: String = "",
    val age: String = "",
    val sex: String = "",
    val postal: String = "",

    val isAdmin: Boolean = false,

    )
