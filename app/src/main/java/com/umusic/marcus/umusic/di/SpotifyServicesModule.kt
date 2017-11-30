package com.umusic.marcus.umusic.di

import com.umusic.marcus.umusic.data.remote.client.SpotifyClient
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class SpotifyServicesModule {


    @Singleton
    @Provides
    internal fun provideSpotifyService(): SpotifyClient {
        return SpotifyClient()
    }

}