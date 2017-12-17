package com.umusic.marcus.umusic.interactor

import com.umusic.marcus.umusic.data.model.PlaylistsContainer
import com.umusic.marcus.umusic.data.remote.client.SpotifyService
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by Marcus on 12/17/2017.
 */
class PlaylistInteractor @Inject constructor(private val spotifyService: SpotifyService) {

    fun loadCategoryPlaylists(category: String): Observable<PlaylistsContainer> {
        return spotifyService.getCategoriesPlaylists(category)
    }

}