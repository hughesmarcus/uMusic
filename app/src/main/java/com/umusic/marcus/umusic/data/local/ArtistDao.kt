package com.umusic.marcus.umusic.data.local

import android.arch.persistence.room.*
import com.umusic.marcus.umusic.data.model.Artist
import io.reactivex.Flowable


/**
 * Created by Marcus on 11/21/2017.
 */
@Dao
interface ArtistDao {
    @Query("SELECT * FROM artist WHERE id=:id")
    fun findById(id: Int): Flowable<Artist>

    @Query("SELECT * FROM Product")
    fun findAll(): Flowable<List<Artist>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(artist: Artist): Long

    @Delete
    fun delete(artist: Artist): Int

    @Update
    fun updateItem(artist: Artist)
}