package com.umusic.marcus.umusic.ui.home.di

import com.umusic.marcus.umusic.di.ActivityScope
import com.umusic.marcus.umusic.ui.home.HomeActivity
import dagger.Subcomponent

/**
 * Created by Marcus on 12/5/2017.
 */
@ActivityScope
@Subcomponent(modules = [(HomeActivityModule::class)])
interface HomeActivityComponent {

    fun inject(homeActivity: HomeActivity)
}