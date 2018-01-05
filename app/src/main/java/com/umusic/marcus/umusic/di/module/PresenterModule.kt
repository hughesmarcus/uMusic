package com.umusic.marcus.umusic.di.module

import com.umusic.marcus.umusic.interactor.PlayerInteractor
import com.umusic.marcus.umusic.ui.BasePresenter
import com.umusic.marcus.umusic.ui.player.AudioPlayerPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PresenterModule {

    @Provides
    internal fun bindPlayerPresenter(playerInteractor: PlayerInteractor): BasePresenter<AudioPlayerPresenter.View> {
        return AudioPlayerPresenter(playerInteractor)
    }

    @Singleton
    @Provides
    internal fun providesPlayerInteractor(): PlayerInteractor {
        return PlayerInteractor()
    }
}