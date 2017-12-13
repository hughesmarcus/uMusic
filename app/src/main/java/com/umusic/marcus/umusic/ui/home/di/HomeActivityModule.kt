package com.umusic.marcus.umusic.ui.home.di

import com.umusic.marcus.umusic.di.ActivityScope
import com.umusic.marcus.umusic.interactor.HomeInteractor
import com.umusic.marcus.umusic.ui.home.HomePresenter
import dagger.Module
import dagger.Provides

/**
 * Created by Marcus on 12/5/2017.
 */
@Module
class HomeActivityModule {

    @Provides
    @ActivityScope
    internal fun providesHomePresenter(interactor: HomeInteractor): HomePresenter {
        return HomePresenter(interactor)
    }
}