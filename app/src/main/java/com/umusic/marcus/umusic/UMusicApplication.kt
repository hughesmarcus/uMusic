package com.umusic.marcus.umusic

import com.umusic.marcus.umusic.di.DaggerAppComponent
import com.umusic.marcus.umusic.di.RoomModule
import com.umusic.marcus.umusic.di.SpotifyServicesModule
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication


class UMusicApplication : DaggerApplication() {


    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val appComponent = DaggerAppComponent.builder().roomModule(RoomModule(applicationContext)).spotifyServicesModule(SpotifyServicesModule()).application(this).build()
        appComponent.inject(this)
        return appComponent
    }
}