package com.umusic.marcus.umusic.interactor

import com.umusic.marcus.umusic.data.model.TracksContainer
import com.umusic.marcus.umusic.data.remote.client.SpotifyService
import io.reactivex.Observable
import javax.inject.Inject


class TracksInteractor@Inject constructor(private val spotifyService: SpotifyService) {

    fun loadData(artistId: String): Observable<TracksContainer> {
        return spotifyService.getTracks(artistId)
    }
}