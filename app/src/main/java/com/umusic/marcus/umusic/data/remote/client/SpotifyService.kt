package com.umusic.marcus.umusic.data.remote.client


import com.umusic.marcus.umusic.data.model.ArtistsContainer
import com.umusic.marcus.umusic.data.model.TracksContainer
import io.reactivex.Observable


interface SpotifyService {

    fun search(query: String): Observable<ArtistsContainer>

    fun getTracks(artistId: String): Observable<TracksContainer>
}