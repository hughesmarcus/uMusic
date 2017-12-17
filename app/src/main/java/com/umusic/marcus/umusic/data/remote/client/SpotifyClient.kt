package com.umusic.marcus.umusic.data.remote.client

import com.umusic.marcus.umusic.data.model.*
import com.umusic.marcus.umusic.data.remote.retrofit.SpotifyRetrofitClient
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class SpotifyClient : SpotifyRetrofitClient(), SpotifyService {
    override fun getNewReleases(): Observable<AlbumContainer> {
        return spotifyService!!.getNewReleases()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
    override fun getAlbums(artistId: String): Observable<AlbumContainer> {
        return spotifyService!!.getArtistAlbums(artistId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getArtistTracks(artistId: String): Observable<TracksContainer> {
        return spotifyService!!.getArtistTracks(artistId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }


    override fun search(query: String): Observable<ArtistsContainer> {
        return spotifyService!!.searchArtist(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun browseCategories(): Observable<CategoriesContainer> {
        return spotifyService!!.browseCategories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getCategoriesPlaylists(category: String): Observable<PlaylistsContainer> {
        return spotifyService!!.getCategoryPlayists(category)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }


}