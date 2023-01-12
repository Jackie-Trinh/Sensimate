package com.example.sensimate.data

import com.auth0.android.jwt.JWT

data class Userdata(val idToken: String? = null) {

    // ID token of the user
    var id = ""
    // Username of the user
    var name = ""
    // Email of the user
    var email = ""
    // Verified state of email of the user
    var emailVerified = ""
    // Profile picture of the user. Not implemented
    var picture = ""
    // Last change to user
    var updatedAt = ""
    // Initialization of current user
    init {
        try {
            val jwt = JWT(idToken ?: "")

            id = jwt.subject ?: ""
            name = jwt.getClaim("name").asString() ?: ""
            email = jwt.getClaim("email").asString() ?: ""
            emailVerified = jwt.getClaim("email_verified").asString() ?: ""
            picture = jwt.getClaim("picture").asString() ?: ""
            updatedAt = jwt.getClaim("updated_at").asString() ?: ""
        } catch (e: com.auth0.android.jwt.DecodeException) {
            // The ID is invalid so the properties remain empty
        }
    }
}