package com.umusic.marcus.umusic.interactor

import com.umusic.marcus.umusic.data.model.AlbumContainer
import com.umusic.marcus.umusic.data.model.CategoriesContainer
import com.umusic.marcus.umusic.data.remote.client.SpotifyService
import io.reactivex.Observable
import javax.inject.Inject


class HomeInteractor @Inject constructor(private val spotifyService: SpotifyService) {

    fun loadCategories(): Observable<CategoriesContainer> {
        return spotifyService.browseCategories()
    }


    fun loadNewReleases(): Observable<AlbumContainer> {
        return spotifyService.getNewReleases()
    }
}
