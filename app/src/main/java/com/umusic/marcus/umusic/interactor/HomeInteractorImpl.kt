package com.umusic.marcus.umusic.interactor

import com.umusic.marcus.umusic.data.model.AlbumContainer
import com.umusic.marcus.umusic.data.model.CategoriesContainer
import com.umusic.marcus.umusic.data.remote.client.SpotifyService
import io.reactivex.Observable
import javax.inject.Inject


class HomeInteractorImpl @Inject constructor(private val spotifyService: SpotifyService) : HomeInteractor {

    override fun loadCategories(): Observable<CategoriesContainer> {
        return spotifyService.browseCategories()
    }


    override fun loadNewReleases(): Observable<AlbumContainer> {
        return spotifyService.getNewReleases()
    }
}
