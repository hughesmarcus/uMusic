package com.umusic.marcus.umusic.data.remote.client

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import com.umusic.marcus.umusic.data.model.ArtistsContainer
import com.umusic.marcus.umusic.data.model.Track
import com.umusic.marcus.umusic.data.model.TracksContainer
import com.umusic.marcus.umusic.data.remote.retrofit.SpotifyRetrofitClient
import io.reactivex.Observable


class SpotifyClient : SpotifyRetrofitClient(), SpotifyService {
    override fun search(query: String): Observable<ArtistsContainer> {
        return spotifyService!!.searchArtist(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getTracks(artistId: String): Observable<TracksContainer> {
        return spotifyService!!.getTracks(artistId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}