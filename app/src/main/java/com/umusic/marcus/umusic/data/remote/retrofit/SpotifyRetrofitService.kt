package com.umusic.marcus.umusic.data.remote.retrofit


import com.umusic.marcus.umusic.data.Constants
import com.umusic.marcus.umusic.data.model.AlbumContainer
import com.umusic.marcus.umusic.data.model.ArtistsContainer
import com.umusic.marcus.umusic.data.model.TracksContainer
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface SpotifyRetrofitService {

    @GET(Constants.Endpoint.ARTIST_SEARCH)
    fun searchArtist(
            @Query(Constants.Params.QUERY_SEARCH) artist: String): Observable<ArtistsContainer>

    @GET(Constants.Endpoint.ARTIST_TRACKS)
    fun getArtistTracks(
            @Path(Constants.Params.ARTIST_ID) artistId: String): Observable<TracksContainer>

    @GET(Constants.Endpoint.ARTIST_ALBUMS)
    fun getArtistAlbums(
            @Path(Constants.Params.ARTIST_ID) artistId: String): Observable<AlbumContainer>

    @GET(Constants.Endpoint.ARTIST_RELATED)
    fun getRelatedArtist(
            @Query(Constants.Params.QUERY_SEARCH) artist: String): Observable<ArtistsContainer>

    @GET(Constants.Endpoint.ALBUM_SEARCH)
    fun searchAlbums(
            @Query(Constants.Params.QUERY_SEARCH) album: String): Observable<AlbumContainer>

    @GET(Constants.Endpoint.NEW_RELEASES)
    fun getNewReleases(): Observable<AlbumContainer>

    @GET(Constants.Endpoint.TRACK_SEARCH)
    fun searchTracks(
            @Query(Constants.Params.QUERY_SEARCH) album: String): Observable<TracksContainer>

}