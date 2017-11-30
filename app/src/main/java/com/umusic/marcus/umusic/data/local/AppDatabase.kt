package com.umusic.marcus.umusic.data.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.umusic.marcus.umusic.data.Converters
import com.umusic.marcus.umusic.data.model.Artist

@Database(entities = arrayOf(Artist::class), version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun artistDao(): ArtistDao
}