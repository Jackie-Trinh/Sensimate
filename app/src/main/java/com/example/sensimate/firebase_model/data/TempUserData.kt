package com.example.sensimate.firebase_model.data

// temp userdata used during signup and login
data class TempUserData(
    val userId: String = "",
    val email: String = "",
    val password: String = "",
    val repeatPassword: String = "",
    val age: String = "",
    val postal: String = "",
    val sex: String = ""
)
