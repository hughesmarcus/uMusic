package com.umusic.marcus.umusic.di.module

import com.umusic.marcus.umusic.data.remote.client.SpotifyClient
import com.umusic.marcus.umusic.data.remote.client.SpotifyService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class SpotifyServicesModule {


    @Singleton
    @Provides
    internal fun provideSpotifyService(): SpotifyService {
        return SpotifyClient()
    }

}