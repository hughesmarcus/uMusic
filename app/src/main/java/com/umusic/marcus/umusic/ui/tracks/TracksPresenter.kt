package com.umusic.marcus.umusic.ui.tracks

import com.umusic.marcus.umusic.data.model.Track
import io.reactivex.internal.util.ExceptionHelper.terminate
import com.umusic.marcus.umusic.interactor.TracksInteractor
import com.umusic.marcus.umusic.ui.BasePresenter
import io.reactivex.functions.Consumer


class TracksPresenter(private val interactor: TracksInteractor) : BasePresenter<TracksPresenter.View>() {

    override fun terminate() {
        super.terminate()
        view = null
    }

    fun onSearchTracks(string: String) {
        view!!.showLoading()
        interactor.loadData(string).subscribe({ tracksList ->
            var tracks = tracksList.tracks
            if (!tracks!!.isEmpty() && tracks.isNotEmpty()) {
                view!!.hideLoading()
                view!!.renderTracks(tracks!!)
            } else {
                view!!.showTracksNotFoundMessage()
            }
        }, Throwable::printStackTrace)
    }

    fun launchArtistDetail(tracks: List<Track>, track: Track, position: Int) {
        view!!.launchTrackDetail(tracks, track, position)
    }

    interface View : BasePresenter.View {

        fun showLoading()

        fun hideLoading()

        fun showTracksNotFoundMessage()

        fun showConnectionErrorMessage()

        fun renderTracks(tracks: List<Track>)

        fun launchTrackDetail(tracks: List<Track>, track: Track, position: Int)
    }
}