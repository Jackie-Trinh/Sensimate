package com.example.sensimate.data.network

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.sensimate.domain.model.Event

@Database(entities = [Event::class], version = 2, exportSchema = false)
abstract class EventDb : RoomDatabase() {
    abstract fun EventDao(): EventDao
}