package com.umusic.marcus.umusic.di

import dagger.Provides
import javax.inject.Singleton
import com.umusic.marcus.umusic.data.remote.client.SpotifyClient
import dagger.Module


@Module
class SpotifyServicesModule {


    @Singleton
    @Provides
    internal fun provideSpotifyService(): SpotifyClient {
        return SpotifyClient()
    }

}