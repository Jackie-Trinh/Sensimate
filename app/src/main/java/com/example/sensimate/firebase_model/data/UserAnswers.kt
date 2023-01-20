package com.example.sensimate.firebase_model.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.sensimate.core.Constants
import com.google.firebase.firestore.DocumentId


data class UserAnswer(
    @DocumentId val userAnswerId: String = "",
    val userId: String = "",
    val answers: MutableList<String>,


    )