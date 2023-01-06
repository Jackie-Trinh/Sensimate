package com.example.sensimate.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.sensimate.core.Constants.Companion.SURVEY_TABLE


@Entity(tableName = SURVEY_TABLE)
data class Survey(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val author: String
)