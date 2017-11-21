package com.umusic.marcus.umusic.data.local

import com.umusic.marcus.umusic.data.model.Artist
import io.reactivex.Flowable
import javax.inject.Inject


/**
 * Created by Marcus on 11/21/2017.
 */
class ArtistDataSource @Inject
constructor(private val artistDao: ArtistDao) : ArtistRepository {

    override fun findById(id: Int): Flowable<Artist> {
        return artistDao.findById(id)
    }

    override fun findAll(): Flowable<List<Artist>> {
        return artistDao.findAll()
    }

    override fun insert(artist: Artist): Long {
        return artistDao.insert(artist)
    }

    override fun delete(artist: Artist): Int {
        return artistDao.delete(artist)
    }
}
