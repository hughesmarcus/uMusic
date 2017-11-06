package com.umusic.marcus.umusic.data.remote.retrofit

import com.google.gson.GsonBuilder
import com.google.gson.Gson
import com.umusic.marcus.umusic.data.Constants
import com.umusic.marcus.umusic.data.remote.retrofit.deserializer.ArtistsDeserializer
import okhttp3.OkHttpClient
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import com.google.gson.reflect.TypeToken
import com.umusic.marcus.umusic.data.model.Track
import com.umusic.marcus.umusic.data.remote.retrofit.deserializer.TracksDeserializer


abstract class SpotifyRetrofitClient {

    protected var spotifyService: SpotifyRetrofitService? = null
        private set

    private val okHttpClient: OkHttpClient
        get() {
            val client = OkHttpClient.Builder()
            val apiInterceptor = ApiInterceptor()
            client.addInterceptor(apiInterceptor)
            return client.build()
        }


    private val spotifyServiceClass: Class<SpotifyRetrofitService>
        get() = SpotifyRetrofitService::class.java

   // private val spotifyDeserializer: Gson
     //   get() = GsonBuilder().registerTypeAdapter(object : TypeToken<ArtistContainer>() {
//   }.type, ArtistsDeserializer<Artist>())
          //      .registerTypeAdapter(object : TypeToken<List<Track>>() {

         //       }.type, TracksDeserializer<Track>())
         //       .create()

    init {
        initRetrofit()
    }

    private fun initRetrofit() {
        val retrofit = retrofitBuilder()
        spotifyService = retrofit.create(spotifyServiceClass)
    }

    private fun retrofitBuilder(): Retrofit {
        return Retrofit.Builder().baseUrl(Constants.SPOTIFY_API)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build()
    }
}