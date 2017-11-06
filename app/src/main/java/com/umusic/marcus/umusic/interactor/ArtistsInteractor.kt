package com.umusic.marcus.umusic.interactor

import com.umusic.marcus.umusic.data.model.ArtistsContainer
import com.umusic.marcus.umusic.data.remote.client.SpotifyService
import io.reactivex.Observable
import javax.inject.Inject


class ArtistsInteractor@Inject constructor(private val spotifyService: SpotifyService) {

    fun searchArtists(query: String): Observable<ArtistsContainer> {
        return spotifyService.search(query)
    }

}