package com.umusic.marcus.umusic.data.local

import com.umusic.marcus.umusic.data.model.Artist
import io.reactivex.Flowable

/**
 * Created by Marcus on 11/21/2017.
 */
interface ArtistRepository {

    fun findById(id: Int): Flowable<Artist>

    fun findAll(): Flowable<List<Artist>>

    fun insert(artist: Artist): Long

    fun delete(artist: Artist): Int

}