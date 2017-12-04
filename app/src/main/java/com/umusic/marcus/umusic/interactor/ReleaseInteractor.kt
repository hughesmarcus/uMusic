package com.umusic.marcus.umusic.interactor

import com.umusic.marcus.umusic.data.model.AlbumContainer
import com.umusic.marcus.umusic.data.remote.client.SpotifyService
import io.reactivex.Observable
import javax.inject.Inject


class ReleaseInteractor @Inject constructor(private val spotifyService: SpotifyService) {

    fun loadNewReleases(): Observable<AlbumContainer> {
        return spotifyService.getNewReleases()
    }
}
