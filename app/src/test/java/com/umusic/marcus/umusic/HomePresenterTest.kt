package com.umusic.marcus.umusic

import com.umusic.marcus.umusic.data.model.*
import com.umusic.marcus.umusic.interactor.HomeInteractorImpl
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
import org.mockito.junit.MockitoJUnitRunner
import java.util.*


@RunWith(MockitoJUnitRunner::class)
class HomePresenterTest {
    var album: AlbumContainer = AlbumContainer()
    var category: CategoriesContainer = CategoriesContainer()
    @Mock
    internal lateinit var view: HomePresenter.View
    @Mock
    internal lateinit var interactorImpl: HomeInteractorImpl
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
        presenter = HomePresenter(interactorImpl)

        presenter.view = view

    }

    @Test
    fun shouldLoadReleases() {
        album.albums = Albums()
        album.albums!!.items = Arrays.asList(mock(Album::class.java), mock(Album::class.java), mock(Album::class.java))
        `when`(interactorImpl.loadNewReleases()).thenReturn(Observable.just(album))
        presenter.getNewReleases()
        verify(view).renderNewReleases(Mockito.anyList())
    }

    @Test
    fun shouldLoadGenres() {
        category.categories = Categories()
        category.categories!!.categories = Arrays.asList(mock(Category::class.java), mock(Category::class.java), mock(Category::class.java))
        `when`(interactorImpl.loadCategories()).thenReturn(Observable.just(category))
        presenter.getGenres()
        verify(view).renderGenres(Mockito.anyList())
    }


}