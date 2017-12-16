package com.umusic.marcus.umusic.ui.home

import com.umusic.marcus.umusic.data.model.Album
import com.umusic.marcus.umusic.data.model.Category
import com.umusic.marcus.umusic.interactor.HomeInteractor
import com.umusic.marcus.umusic.ui.BasePresenter

class HomePresenter(private val interactor: HomeInteractor) : BasePresenter<HomePresenter.View>() {
    override fun terminate() {
        super.terminate()
        view = null
    }

    fun getNewReleases() {
        interactor.loadNewReleases().subscribe({ albumsList ->
            val albums: List<Album>? = albumsList.albums!!.items
            if (!albums!!.isEmpty()) {
                // view!!.hideLoading()
                view!!.renderNewReleases(albums)
            } else {
                //view!!.showTracksNotFoundMessage()
            }
        }, Throwable::printStackTrace)
    }

    fun getGenres() {
        interactor.loadCategories().subscribe({ genreList ->
            val genres: List<Category>? = genreList.categories!!.categories
            if (!genres!!.isEmpty()) {
                view!!.renderGenres(genres)
            } else {

            }

        }, Throwable::printStackTrace)
    }

    fun launchAlbumDetail(album: Album) {
        view!!.launchAlbumDetail(album)
    }

    fun launchGenreDetail(category: Category) {
        view!!.launchGenreDetail(category)
    }

    interface View : BasePresenter.View {


        fun renderNewReleases(albums: List<Album>)
        fun renderGenres(genres: List<Category>)
        fun launchAlbumDetail(album: Album)
        fun launchGenreDetail(category: Category)

    }
}