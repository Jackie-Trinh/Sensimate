package com.example.sensimate.data.network

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.sensimate.domain.model.Event
import com.example.sensimate.domain.model.Question
import com.example.sensimate.domain.repository.type_converter.ArrayListConverter

@Database(
    entities = [
        Event::class,
        Question::class
               ],
    version = 10,
    exportSchema = false
)
@TypeConverters(ArrayListConverter::class)
abstract class EventDb : RoomDatabase() {
    abstract fun EventDao(): EventDao
}