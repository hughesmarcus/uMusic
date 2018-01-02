package com.umusic.marcus.umusic.ui.search

import android.util.Log
import com.umusic.marcus.umusic.data.model.Artist
import com.umusic.marcus.umusic.interactor.ArtistsInteractor
import com.umusic.marcus.umusic.ui.BasePresenter


class SearchPresenter(private val interactor: ArtistsInteractor) : BasePresenter<SearchPresenter.View>() {

    fun onSearchArtist(name: String) {
        view!!.showLoading()
        val disposable = interactor.searchArtists(name).subscribe({ artistsList ->
            val artists: List<Artist>? = artistsList.artists!!.items
            when {
                !artists!!.isEmpty() && artists.isNotEmpty() -> {
                    view!!.hideLoading()
                    view!!.renderArtists(artists)
                }
                else -> view!!.showArtistNotFoundMessage()
            }
        }, { throwable -> Log.v("hello",throwable.message) })

        addDisposableObserver(disposable)
    }

    fun launchArtistDetail(artist: Artist) {
        view!!.launchArtistDetail(artist)
    }

    override fun terminate() {
        super.terminate()
        view=null
    }

    interface View : BasePresenter.View {

        fun showLoading()

        fun hideLoading()

        fun showArtistNotFoundMessage()

        fun showConnectionErrorMessage()

        fun showServerError()

        fun renderArtists(artists: List<Artist>)

        fun launchArtistDetail(artist: Artist)
    }
}