package com.umusic.marcus.umusic.ui.tracks

import com.umusic.marcus.umusic.data.model.Item
import com.umusic.marcus.umusic.interactor.TracksInteractor
import com.umusic.marcus.umusic.ui.BasePresenter

/**
 * Created by Marcus on 12/18/2017.
 */
class TracksPresenter(private val interactor: TracksInteractor) : BasePresenter<TracksPresenter.View>() {
    fun getTracks(owner: String, playlist: String) {
        interactor.loadPlaylistTracks(owner, playlist).subscribe({ tracksList ->
            val tracks: List<Item>? = tracksList.items
            if (!tracks!!.isEmpty()) {
                view!!.renderTracks(tracks)
            } else {

            }

        }, Throwable::printStackTrace)
    }

    interface View : BasePresenter.View {
        fun renderTracks(tracks: List<Item>)
        fun launchTrack()
        fun launchTrackOptions()


    }
}