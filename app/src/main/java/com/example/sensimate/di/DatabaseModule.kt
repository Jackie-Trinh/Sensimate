package com.example.sensimate.di

import android.content.Context
import androidx.room.Room
import com.example.sensimate.core.Constants.Companion.EVENT_TABLE
import com.example.sensimate.data.network.EventDao
import com.example.sensimate.data.network.EventDb
import com.example.sensimate.data.repository.EventRepositoryImpl
import com.example.sensimate.domain.repository.EventRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Provides
    fun provideEventDb(
        @ApplicationContext
        context : Context
    ) = Room.databaseBuilder(
        context,
        EventDb::class.java,
//        EVENT_TABLE
    "SensiMateDatabase"
    ).fallbackToDestructiveMigration().allowMainThreadQueries().build()

    @Provides
    fun provideEventDao(
        eventDb: EventDb
    ) = eventDb.EventDao()

    @Provides
    fun provideEventRepository(
        eventDao: EventDao
    ): EventRepository = EventRepositoryImpl(
        eventDao = eventDao
    )
}