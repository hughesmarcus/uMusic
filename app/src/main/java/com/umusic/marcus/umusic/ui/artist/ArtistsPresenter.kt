package com.umusic.marcus.umusic.ui.artist

import android.util.Log
import com.umusic.marcus.umusic.data.model.Artist
import io.reactivex.internal.util.ExceptionHelper.terminate
import com.umusic.marcus.umusic.interactor.ArtistsInteractor
import com.umusic.marcus.umusic.ui.BasePresenter


class ArtistsPresenter(private val interactor: ArtistsInteractor) : BasePresenter<ArtistsPresenter.View>() {

    fun onSearchArtist(name: String) {
        view!!.showLoading()
        val disposable = interactor.searchArtists(name).subscribe({ artistsList ->
            var artists: List<Artist>? = artistsList.artists!!.items
            if (!artists!!.isEmpty() && artists.isNotEmpty()) {
                view!!.hideLoading()
                view!!.renderArtists(artists)
            } else {
                view!!.showArtistNotFoundMessage()
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