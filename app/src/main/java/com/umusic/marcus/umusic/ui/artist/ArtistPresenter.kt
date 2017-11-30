package com.umusic.marcus.umusic.ui.artist

import com.umusic.marcus.umusic.data.model.Track
import com.umusic.marcus.umusic.interactor.TracksInteractor
import com.umusic.marcus.umusic.ui.BasePresenter


class ArtistPresenter(private val interactor: TracksInteractor) : BasePresenter<ArtistPresenter.View>() {

    override fun terminate() {
        super.terminate()
        view = null
    }

    fun onSearchTracks(string: String) {
        view!!.showLoading()
        interactor.loadData(string).subscribe({ tracksList ->
            val tracks = tracksList.tracks
            if (!tracks!!.isEmpty() && tracks.isNotEmpty()) {
                view!!.hideLoading()
                view!!.renderTracks(tracks)
            } else {
                view!!.showTracksNotFoundMessage()
            }
        }, Throwable::printStackTrace)
    }

    fun launchTrackDetail(tracks: List<Track>, track: Track, position: Int) {
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