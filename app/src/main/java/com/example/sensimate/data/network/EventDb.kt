package com.example.sensimate.data.network

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.sensimate.domain.model.Event
import com.example.sensimate.domain.model.Question

@Database(
    entities = [
        Event::class,
        Question::class
               ],
    version = 6,
    exportSchema = false
)
abstract class EventDb : RoomDatabase() {
    abstract fun EventDao(): EventDao
}