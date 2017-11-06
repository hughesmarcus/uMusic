package com.umusic.marcus.umusic

import android.support.annotation.VisibleForTesting
import com.umusic.marcus.umusic.di.AppComponent
import com.umusic.marcus.umusic.di.DaggerAppComponent
import javax.inject.Inject
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;


class UMusicApplication : DaggerApplication() {


    protected override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val appComponent = DaggerAppComponent.builder().application(this).build()
        appComponent.inject(this)
        return appComponent
    }
}