package com.example.sensimate.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.sensimate.core.Constants

@Entity(tableName = Constants.USER_TABLE)
data class User(
    @PrimaryKey(autoGenerate = true)
    val userId: Int,
    val mail: String,
    val dateOfBirth: String,
    val sex: String,
    val postcode: String,

    val userAnswers: UserAnswers
)