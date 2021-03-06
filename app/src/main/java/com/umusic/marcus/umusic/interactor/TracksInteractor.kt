package com.umusic.marcus.umusic.interactor

import com.umusic.marcus.umusic.data.model.PlaylistTracksContainer
import com.umusic.marcus.umusic.data.model.TracksContainer
import com.umusic.marcus.umusic.data.remote.client.SpotifyService
import io.reactivex.Observable
import javax.inject.Inject


class TracksInteractor@Inject constructor(private val spotifyService: SpotifyService) {

    fun loadData(artistId: String): Observable<TracksContainer> {
        return spotifyService.getArtistTracks(artistId)
    }

    fun loadPlaylistTracks(owner: String, playlist: String): Observable<PlaylistTracksContainer> {
        return spotifyService.getPlaylistTracks(owner, playlist)
    }

    fun loadAlbumTracks(album: String): Observable<TracksContainer> {
        return spotifyService.getAlbumTracks(album)
    }
}