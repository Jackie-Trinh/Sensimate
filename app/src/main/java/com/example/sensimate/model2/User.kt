package com.example.sensimate.model2

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.sensimate.core.Constants

data class User(
    val id: String = "",
    val isAnonymous: Boolean = true
)
