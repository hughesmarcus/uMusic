package com.umusic.marcus.umusic.ui.player

import android.content.ServiceConnection
import com.umusic.marcus.umusic.data.model.Album
import com.umusic.marcus.umusic.interactor.PlayerInteractor
import com.umusic.marcus.umusic.ui.BasePresenter


class AudioPlayerPresenter(private val playerInteractor: PlayerInteractor) : BasePresenter<AudioPlayerPresenter.View>(), AudioFinishedListener {

    private var serviceConnection: ServiceConnection? = null

    init {
        this.playerInteractor.setAudioFinishedListener(this)
    }

    fun onPreviewTrack() {
        playerInteractor.onPreview()
    }

    fun onNextTrack() {
        playerInteractor.onNext()
    }

    fun onPlayPauseTrack() {
        playerInteractor.onPlayStop()
    }

    fun onStartAudioService(trackUrl: String) {
        if (view != null)
            view!!.onStartAudioService(trackUrl, serviceConnection!!)
    }

    fun setInfoMediaPlayer(trackPosition: Int) {
        if (view != null)
            view!!.setInfoTrackPlayer(trackPosition)
    }

    fun setInfoMediaPlayer(trackPosition: Int, album: Album) {
        if (view != null)
            view!!.setInfoTrackPlayer(trackPosition, album)
    }

    override fun terminate() {
        super.terminate()
        playerInteractor.destroyAudioService()
        view = null
    }

    override fun onPlay() {
        if (view != null)
            view!!.isPlay()
    }

    override fun onPause() {
        if (view != null)
            view!!.isPause()
    }

    override fun onSetTimeStart(trackCurrentPosition: Int) {
        if (view != null)
            view!!.setTimeStart(trackCurrentPosition)
    }

     override fun onSetTimeFinished(audioPlayerService: AudioPlayerService) {
         if (view != null)
             view!!.setTimeFinished(audioPlayerService)
    }

    override fun onResetTrackDuration() {
        if (view != null)
            view!!.onResetTrackDuration()
    }

    override fun onSetTrackPlayer(trackPosition: Int) {
        if (view != null)
            view!!.setTrackPlayer(trackPosition)
    }

    override fun onSetInfoTrackPlayer(trackPosition: Int) {
        if (view != null) {
            view!!.setInfoTrackPlayer(trackPosition)
        }
    }

    override fun onServiceConnection(serviceConnection: ServiceConnection) {
        this.serviceConnection = serviceConnection
    }

    interface View : BasePresenter.View {

        fun onStartAudioService(trackUrl: String, serviceConnection: ServiceConnection)

        fun setInfoTrackPlayer(trackPosition: Int)

        fun isPlay()

        fun isPause()

        fun setTimeStart(trackCurrentPosition: Int)

        fun setTimeFinished(audioPlayerService: AudioPlayerService)

        fun onResetTrackDuration()

        fun setInfoTrackPlayer(trackPosition: Int, album: Album)

        fun setTrackPlayer(trackPosition: Int)
    }
}