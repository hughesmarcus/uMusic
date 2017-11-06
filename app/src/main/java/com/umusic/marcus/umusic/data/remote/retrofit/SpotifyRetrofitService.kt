package com.umusic.marcus.umusic.data.remote.retrofit


import com.umusic.marcus.umusic.data.Constants
import com.umusic.marcus.umusic.data.model.ArtistsContainer
import retrofit2.http.GET
import com.umusic.marcus.umusic.data.model.Track
import com.umusic.marcus.umusic.data.model.TracksContainer
import io.reactivex.Observable
import retrofit2.http.Path
import retrofit2.http.Query


interface SpotifyRetrofitService {

    @GET(Constants.Endpoint.ARTIST_SEARCH)
    fun searchArtist(
            @Query(Constants.Params.QUERY_SEARCH) artist: String): Observable<ArtistsContainer>

    @GET(Constants.Endpoint.ARTIST_TRACKS)
    fun getTracks(
            @Path(Constants.Params.ARTIST_ID) artistId: String): Observable<TracksContainer>
}