package com.umusic.marcus.umusic

import android.app.Application
import android.support.annotation.VisibleForTesting
import com.umusic.marcus.umusic.di.AppComponent
import com.umusic.marcus.umusic.di.DaggerAppComponent
import com.umusic.marcus.umusic.di.module.SpotifyServicesModule


class UMusicApplication : Application() {

    var appComponent: AppComponent? = null

    override fun onCreate() {
        createComponent()
        super.onCreate()


    }

    @VisibleForTesting
    fun createComponent() {
        appComponent =
                DaggerAppComponent.builder()
                        .spotifyServicesModule(SpotifyServicesModule())
                        .application(this)
                        .build()
    }

}