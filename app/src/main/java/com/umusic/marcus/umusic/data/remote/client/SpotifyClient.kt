package com.umusic.marcus.umusic.data.remote.client

import com.umusic.marcus.umusic.data.model.AlbumContainer
import com.umusic.marcus.umusic.data.model.ArtistsContainer
import com.umusic.marcus.umusic.data.model.TracksContainer
import com.umusic.marcus.umusic.data.remote.retrofit.SpotifyRetrofitClient
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class SpotifyClient : SpotifyRetrofitClient(), SpotifyService {
    override fun getAlbums(artistId: String): Observable<AlbumContainer> {
        return spotifyService!!.getArtistAlbums(artistId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getArtistTracks(artistId: String): Observable<TracksContainer> {
        return spotifyService!!.getArtistTracks(artistId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }


    override fun search(query: String): Observable<ArtistsContainer> {
        return spotifyService!!.searchArtist(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }


}