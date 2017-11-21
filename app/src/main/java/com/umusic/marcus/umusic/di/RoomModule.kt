package com.umusic.marcus.umusic.di

import android.arch.persistence.room.Room
import android.content.Context
import com.umusic.marcus.umusic.data.local.AppDatabase
import com.umusic.marcus.umusic.data.local.ArtistDao
import com.umusic.marcus.umusic.data.local.ArtistDataSource
import com.umusic.marcus.umusic.data.local.ArtistRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


/**
 * Created by Marcus on 11/21/2017.
 */

@Module
class RoomModule(context: Context) {

    private val appDatebase: AppDatabase

    init {
        appDatebase = Room.databaseBuilder(context, AppDatabase::class.java, "app-db").build()
    }

    @Singleton
    @Provides
    internal fun providesappDatabase(): AppDatabase {
        return appDatebase
    }

    @Singleton
    @Provides
    internal fun providesArtistDao(appDatabase: AppDatabase): ArtistDao {
        return appDatabase.artistDao()
    }

    @Singleton
    @Provides
    internal fun providesartistRepository(artistDao: ArtistDao): ArtistRepository {
        return ArtistDataSource(artistDao)
    }

}