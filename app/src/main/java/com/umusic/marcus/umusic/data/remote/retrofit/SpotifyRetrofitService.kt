package com.umusic.marcus.umusic.data.remote.retrofit


import com.umusic.marcus.umusic.data.Constants
import com.umusic.marcus.umusic.data.model.*
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
            @Path(Constants.Params.QUERY_SEARCH) artist: String): Observable<ArtistsContainer>

    @GET(Constants.Endpoint.ALBUM_SEARCH)
    fun searchAlbums(
            @Path(Constants.Params.QUERY_SEARCH) album: String): Observable<AlbumContainer>

    @GET(Constants.Endpoint.NEW_RELEASES)
    fun getNewReleases(): Observable<AlbumContainer>

    @GET(Constants.Endpoint.TRACK_SEARCH)
    fun searchTracks(
            @Path(Constants.Params.QUERY_SEARCH) album: String): Observable<TracksContainer>

    @GET(Constants.Endpoint.CATEGORIES_BROWSE)
    fun browseCategories(): Observable<CategoriesContainer>

    @GET(Constants.Endpoint.CATEGORY_PLAYLISTS)
    fun getCategoryPlayists(
            @Path(Constants.Params.CATEGORY_ID) category: String): Observable<PlaylistsContainer>

    @GET(Constants.Endpoint.PLAYLIST_TRACKS)
    fun getPlaylistTracks(
            @Path(Constants.Params.USER_ID) user: String,
            @Path(Constants.Params.PLAYLIST_ID) playlist: String): Observable<PlaylistTracksContainer>


}