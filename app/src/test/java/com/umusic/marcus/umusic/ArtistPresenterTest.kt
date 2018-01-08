package com.umusic.marcus.umusic

import com.umusic.marcus.umusic.data.model.Track
import com.umusic.marcus.umusic.data.model.TracksContainer
import com.umusic.marcus.umusic.interactor.TracksInteractor
import com.umusic.marcus.umusic.ui.artist.ArtistPresenter
import io.reactivex.Observable
import org.junit.Before
import org.junit.ClassRule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import java.util.*

/**
 * Created by Marcus on 12/4/2017.
 */
@RunWith(MockitoJUnitRunner::class)
class ArtistPresenterTest {

    private fun <T> any(): T {
        Mockito.any<T>()
        return uninitialized()
    }

    private fun <T> uninitialized(): T = null as T
    var track: TracksContainer = TracksContainer()
    @Mock
    internal lateinit var view: ArtistPresenter.View
    @Mock
    internal lateinit var interactor: TracksInteractor


    internal lateinit var presenter: ArtistPresenter

    companion object {
        @ClassRule
        @JvmField
        val schedulers = RxImmediateSchedulerRule()
    }

    @Before
    fun setup() {
        presenter = ArtistPresenter(interactor)

        presenter.view = view
        track.tracks = Arrays.asList(Mockito.mock(Track::class.java), Mockito.mock(Track::class.java), Mockito.mock(Track::class.java))
    }

    @Test
    fun shouldLoadReleases() {


        Mockito.`when`(interactor.loadData(anyString())).thenReturn(Observable.just(track))
        presenter.onSearchTracks("hello")
        verify(view).hideLoading()
        verify(view).renderTracks(Mockito.anyList())
    }

    @Test
    fun shouldLaunchTrack() {
        presenter.launchTrackDetail(track.tracks!!, track.tracks!![0], 0)
        verify(view).launchTrackDetail(
                Mockito.anyList(),
                any(),
                Mockito.anyInt()
        )
    }


}