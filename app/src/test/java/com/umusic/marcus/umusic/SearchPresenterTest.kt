package com.umusic.marcus.umusic

import com.umusic.marcus.umusic.data.model.Artist
import com.umusic.marcus.umusic.data.model.Artists
import com.umusic.marcus.umusic.data.model.ArtistsContainer
import com.umusic.marcus.umusic.interactor.ArtistsInteractor
import com.umusic.marcus.umusic.ui.search.SearchPresenter
import io.reactivex.Observable
import org.junit.Before
import org.junit.ClassRule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.util.*

/**
 * Created by Marcus on 1/7/2018.
 */
@RunWith(MockitoJUnitRunner::class)
class SearchPresenterTest {
    var artist: ArtistsContainer = ArtistsContainer()
    @Mock
    internal lateinit var view: SearchPresenter.View
    @Mock
    internal lateinit var interactor: ArtistsInteractor


    internal lateinit var presenter: SearchPresenter

    companion object {
        @ClassRule
        @JvmField
        val schedulers = RxImmediateSchedulerRule()
    }

    @Before
    fun setup() {
        presenter = SearchPresenter(interactor)

        presenter.view = view

    }

    @Test
    fun shouldloadArtist() {
        artist.artists = Artists()
        artist.artists!!.items = Arrays.asList(Mockito.mock(Artist::class.java), Mockito.mock(Artist::class.java), Mockito.mock(Artist::class.java))
        Mockito.`when`(interactor.searchArtists("drake")).thenReturn(Observable.just(artist))
        presenter.onSearchArtist("drake")
        Mockito.verify(view).renderArtists(Mockito.anyList())
    }


}