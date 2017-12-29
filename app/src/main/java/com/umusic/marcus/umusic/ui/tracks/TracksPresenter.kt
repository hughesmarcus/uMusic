package com.umusic.marcus.umusic.ui.tracks

import com.umusic.marcus.umusic.data.model.Item
import com.umusic.marcus.umusic.data.model.Track
import com.umusic.marcus.umusic.interactor.TracksInteractor
import com.umusic.marcus.umusic.ui.BasePresenter

/**
 * Created by Marcus on 12/18/2017.
 */
class TracksPresenter(private val interactor: TracksInteractor) : BasePresenter<TracksPresenter.View>() {
    override fun terminate() {
        super.terminate()
        view = null
    }

    fun getTracks(owner: String, playlist: String) {
        interactor.loadPlaylistTracks(owner, playlist).subscribe({ tracksList ->
            val tracks: List<Item>? = tracksList.items
            if (!tracks!!.isEmpty()) {
                view!!.renderTracks(tracks)
            } else {

            }

        }, Throwable::printStackTrace)
    }

    fun getAlbumTracks(album: String) {
        interactor.loadAlbumTracks(album).subscribe({ tracksList ->
            val tracks: List<Track>? = tracksList.albumTracks
            if (!tracks!!.isEmpty()) {
                view!!.renderAlbumTracks(tracks)
            } else {

            }

        }, Throwable::printStackTrace)
    }
    fun launchTrackDetail(tracks: List<Track>, track: Track, position: Int) {
        view!!.launchTrack(tracks, track, position)
    }
    interface View : BasePresenter.View {
        fun renderTracks(tracks: List<Item>)
        fun launchTrack(tracks: List<Track>, track: Track, position: Int)
        fun launchTrackOptions()
        fun renderAlbumTracks(tracks: List<Track>)


    }
}