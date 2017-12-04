package com.umusic.marcus.umusic.ui.home

import com.umusic.marcus.umusic.data.model.Album
import com.umusic.marcus.umusic.interactor.ReleaseInteractor
import com.umusic.marcus.umusic.ui.BasePresenter

class HomePresenter(private val interactor: ReleaseInteractor) : BasePresenter<HomePresenter.View>() {
    override fun terminate() {
        super.terminate()
        view = null
    }

    fun getNewReleases() {
        interactor.loadNewReleases().subscribe({ albumsList ->
            val albums: List<Album>? = albumsList.albums!!.items
            if (!albums!!.isEmpty() && albums.isNotEmpty()) {
                // view!!.hideLoading()
                view!!.renderNewReleases(albums)
            } else {
                //view!!.showTracksNotFoundMessage()
            }
        }, Throwable::printStackTrace)
    }

    interface View : BasePresenter.View {


        fun renderNewReleases(albums: List<Album>)

    }
}