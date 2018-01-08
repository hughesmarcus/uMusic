package com.umusic.marcus.umusic.interactor

import com.umusic.marcus.umusic.data.model.AlbumContainer
import com.umusic.marcus.umusic.data.model.CategoriesContainer
import io.reactivex.Observable

/**
 * Created by Marcus on 1/7/2018.
 */
interface HomeInteractor {
    fun loadCategories(): Observable<CategoriesContainer>


    fun loadNewReleases(): Observable<AlbumContainer>
}