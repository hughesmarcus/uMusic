package com.umusic.marcus.umusic.di

import android.app.Application
import com.umusic.marcus.umusic.UMusicApplication
import com.umusic.marcus.umusic.di.module.ApplicationModule
import com.umusic.marcus.umusic.di.module.InteractorModule
import com.umusic.marcus.umusic.di.module.PresenterModule
import com.umusic.marcus.umusic.di.module.SpotifyServicesModule
import com.umusic.marcus.umusic.interactor.ArtistsInteractor
import com.umusic.marcus.umusic.interactor.HomeInteractor
import com.umusic.marcus.umusic.interactor.PlaylistInteractor
import com.umusic.marcus.umusic.interactor.TracksInteractor
import com.umusic.marcus.umusic.ui.player.MiniPlayerFragment
import com.umusic.marcus.umusic.ui.player.PlayerFragment
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Singleton
@Component(modules = arrayOf(
    ApplicationModule::class,
        FragmentBindingModule::class,
    AndroidSupportInjectionModule::class,
        SpotifyServicesModule::class,
        //  RoomModule::class,
        PresenterModule::class,
        InteractorModule::class
        //FragmentBindingModule::class
))
interface AppComponent : AndroidInjector<DaggerApplication> {

    //operator fun plus(module: FragmentModule): FragmentComponent
    fun inject(application: UMusicApplication)
    fun inject(artistsInteractor: ArtistsInteractor)
    fun inject(homeInteractor: HomeInteractor)
    fun inject(tracksInteractor: TracksInteractor)
    fun inject(playlistInteractor: PlaylistInteractor)
    fun inject(playerFragment: PlayerFragment)
    fun inject(miniPlayerFragment: MiniPlayerFragment)

    override fun inject(instance: DaggerApplication)


    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): AppComponent.Builder

        fun spotifyServicesModule(spotifyServicesModule: SpotifyServicesModule): Builder
        //fun roomModule(roomModule: RoomModule): Builder
        fun build(): AppComponent

    }
}