package com.umusic.marcus.umusic.di

import android.app.Application
import com.umusic.marcus.umusic.UMusicApplication
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

    override fun inject(instance: DaggerApplication)


    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): AppComponent.Builder

        fun roomModule(roomModule: RoomModule): Builder
        fun build(): AppComponent

    }
}