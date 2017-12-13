package com.umusic.marcus.umusic.ui.home.di

import com.umusic.marcus.umusic.di.ActivityScope
import com.umusic.marcus.umusic.di.AppComponent
import com.umusic.marcus.umusic.ui.home.HomeActivity
import dagger.Component

/**
 * Created by Marcus on 12/5/2017.
 */
@ActivityScope
@Component(dependencies = arrayOf(AppComponent::class), modules = arrayOf(HomeActivityModule::class))
interface HomeActivityComponent {

    fun inject(homeActivity: HomeActivity)
}