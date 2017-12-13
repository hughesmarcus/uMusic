package com.umusic.marcus.umusic.data.remote.client


import com.umusic.marcus.umusic.data.model.AlbumContainer
import com.umusic.marcus.umusic.data.model.ArtistsContainer
import com.umusic.marcus.umusic.data.model.CategoriesContainer
import com.umusic.marcus.umusic.data.model.TracksContainer
import io.reactivex.Observable


interface SpotifyService {

    fun search(query: String): Observable<ArtistsContainer>

    fun getArtistTracks(artistId: String): Observable<TracksContainer>

    fun getAlbums(artistId: String): Observable<AlbumContainer>

    fun getNewReleases(): Observable<AlbumContainer>

    fun browseCategories(): Observable<CategoriesContainer>
}