package com.umusic.marcus.umusic.data.remote.retrofit


import com.umusic.marcus.umusic.data.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


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