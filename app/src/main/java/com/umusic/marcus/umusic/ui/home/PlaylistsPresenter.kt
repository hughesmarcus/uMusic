package com.umusic.marcus.umusic.ui.home

import com.umusic.marcus.umusic.data.model.Category
import com.umusic.marcus.umusic.data.model.Playlist
import com.umusic.marcus.umusic.interactor.PlaylistInteractor
import com.umusic.marcus.umusic.ui.BasePresenter


class PlaylistsPresenter(private val interactor: PlaylistInteractor) : BasePresenter<PlaylistsPresenter.View>() {
    override fun terminate() {
        super.terminate()
        view = null
    }

    fun launchPlaylistDetail(playlist: Playlist) {
        view!!.launchPlaylistDetail(playlist)
    }

    fun getPlaylists(category: Category) {
        interactor.loadCategoryPlaylists(category.id!!).subscribe({ playlistsList ->
            val playlists: List<Playlist>? = playlistsList.playlists!!.items
            if (!playlists!!.isEmpty()) {
                view!!.renderPlaylists(playlists)
            } else {

            }

        }, Throwable::printStackTrace)
    }

    interface View : BasePresenter.View {
        fun renderPlaylists(playlists: List<Playlist>)
        fun launchPlaylistDetail(playlist: Playlist)


    }
}