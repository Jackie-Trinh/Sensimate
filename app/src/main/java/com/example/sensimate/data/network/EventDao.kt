package com.example.sensimate.data.network

import androidx.room.*
import androidx.room.OnConflictStrategy.Companion.IGNORE
import com.example.sensimate.core.Constants.Companion.EVENT_TABLE
import com.example.sensimate.domain.model.Event
import com.example.sensimate.domain.repository.Events
import kotlinx.coroutines.flow.Flow


@Dao
interface EventDao {
    @Query("SELECT * FROM $EVENT_TABLE ORDER BY id ASC")
    fun getEvents(): Flow<Events>

    @Query("SELECT * FROM $EVENT_TABLE WHERE id = :id")
    fun getEvent(id: Int): Event

    @Insert(onConflict = IGNORE)
    fun addEvent(event: Event)

    @Update
    fun updateEvent(event: Event)

    @Delete
    fun deleteEvent(event: Event)

}

