package com.umusic.marcus.umusic.ui.home

import com.umusic.marcus.umusic.data.model.Category
import com.umusic.marcus.umusic.data.model.Playlist
import com.umusic.marcus.umusic.interactor.PlaylistInteractor
import com.umusic.marcus.umusic.ui.BasePresenter


class PlaylistsPresenter(private val interactor: PlaylistInteractor) : BasePresenter<PlaylistsPresenter.View>() {

    fun launchPlaylistDetail(playlist: Playlist) {

    }

    fun getPlaylsts(category: Category) {
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


    }
}