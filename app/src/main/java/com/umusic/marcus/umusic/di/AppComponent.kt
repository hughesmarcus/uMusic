package com.umusic.marcus.umusic.di

import android.app.Application
import com.umusic.marcus.umusic.UMusicApplication
import com.umusic.marcus.umusic.interactor.ArtistsInteractor
import com.umusic.marcus.umusic.interactor.HomeInteractor
import com.umusic.marcus.umusic.interactor.PlaylistInteractor
import com.umusic.marcus.umusic.interactor.TracksInteractor
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Singleton
@Component(modules = arrayOf(
    ApplicationModule::class,
    ActivityBindingModule::class,
    AndroidSupportInjectionModule::class,
        SpotifyServicesModule::class,
        RoomModule::class

))
interface AppComponent : AndroidInjector<DaggerApplication> {


    fun inject(application: UMusicApplication)
    fun inject(artistsInteractor: ArtistsInteractor)
    fun inject(homeInteractor: HomeInteractor)
    fun inject(tracksInteractor: TracksInteractor)
    fun inject(playlistInteractor: PlaylistInteractor)


    override fun inject(instance: DaggerApplication)


    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): AppComponent.Builder

        fun spotifyServicesModule(spotifyServicesModule: SpotifyServicesModule): Builder
        fun roomModule(roomModule: RoomModule): Builder
        fun build(): AppComponent

    }
}