package com.umusic.marcus.umusic.data.remote.client


import com.umusic.marcus.umusic.data.model.*
import io.reactivex.Observable


interface SpotifyService {

    fun search(query: String): Observable<ArtistsContainer>

    fun getArtistTracks(artistId: String): Observable<TracksContainer>

    fun getAlbums(artistId: String): Observable<AlbumContainer>

    fun getNewReleases(): Observable<AlbumContainer>

    fun browseCategories(): Observable<CategoriesContainer>

    fun getCategoriesPlaylists(category: String): Observable<PlaylistsContainer>

    fun getPlaylistTracks(owner: String, playlist: String): Observable<PlaylistTracksContainer>

    fun getAlbumTracks(album: String): Observable<TracksContainer>
}