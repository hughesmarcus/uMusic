package com.umusic.marcus.umusic.data.local

import com.umusic.marcus.umusic.data.model.Artist
import io.reactivex.Flowable


interface ArtistRepository {

    fun findById(id: Int): Flowable<Artist>

    fun findAll(): Flowable<List<Artist>>

    fun insert(artist: Artist): Long

    fun delete(artist: Artist): Int

}