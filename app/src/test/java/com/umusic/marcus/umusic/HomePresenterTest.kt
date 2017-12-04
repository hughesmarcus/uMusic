package com.umusic.marcus.umusic

import com.umusic.marcus.umusic.data.model.Album
import com.umusic.marcus.umusic.data.model.AlbumContainer
import com.umusic.marcus.umusic.data.model.Albums
import com.umusic.marcus.umusic.interactor.ReleaseInteractor
import com.umusic.marcus.umusic.ui.home.HomePresenter
import io.reactivex.Observable
import org.junit.Before
import org.junit.ClassRule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.runners.MockitoJUnitRunner
import java.util.*


@RunWith(MockitoJUnitRunner::class)
class HomePresenterTest {
    var album: AlbumContainer = AlbumContainer()
    @Mock
    internal lateinit var view: HomePresenter.View
    @Mock
    internal lateinit var interactor: ReleaseInteractor
    @Captor
    lateinit var albums: ArgumentCaptor<List<Album>>

    internal lateinit var presenter: HomePresenter

    companion object {
        @ClassRule
        @JvmField
        val schedulers = RxImmediateSchedulerRule()
    }

    @Before
    fun setup() {
        presenter = HomePresenter(interactor)

        presenter.view = view

    }

    @Test
    fun shouldLoadReleases() {
        album.albums = Albums()
        album.albums!!.items = Arrays.asList(mock(Album::class.java), mock(Album::class.java), mock(Album::class.java))
        `when`(interactor.loadNewReleases()).thenReturn(Observable.just(album))
        presenter.getNewReleases()
        verify(view).renderNewReleases(Mockito.anyList())
    }


}