package com.umusic.marcus.umusic.di

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module


@Module
abstract class ApplicationModule {
    //expose Application as an injectable context
    @Binds
    internal abstract fun bindContext(application: Application): Context
}