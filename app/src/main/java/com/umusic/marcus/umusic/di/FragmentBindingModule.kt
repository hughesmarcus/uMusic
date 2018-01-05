package com.umusic.marcus.umusic.di

import com.umusic.marcus.umusic.ui.player.PlayerFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector




@Module
abstract class FragmentBindingModule {

    @ContributesAndroidInjector
    abstract fun playerFragment(): PlayerFragment
}


