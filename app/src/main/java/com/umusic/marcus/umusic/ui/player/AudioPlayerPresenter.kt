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
        view!!.onStartAudioService(trackUrl, serviceConnection!!)
    }

    fun setInfoMediaPlayer(trackPosition: Int) {
        view!!.setInfoTrackPlayer(trackPosition)
    }

    fun setInfoMediaPlayer(trackPosition: Int, album: Album) {
        view!!.setInfoTrackPlayer(trackPosition, album)
    }

    override fun terminate() {
        super.terminate()
        playerInteractor.destroyAudioService()
        view = null
    }

    override fun onPlay() {
        view!!.isPlay()
    }

    override fun onPause() {
        view!!.isPause()
    }

    override fun onSetTimeStart(trackCurrentPosition: Int) {
        view!!.setTimeStart(trackCurrentPosition)
    }

     override fun onSetTimeFinished(audioPlayerService: AudioPlayerService) {
        view!!.setTimeFinished(audioPlayerService)
    }

    override fun onResetTrackDuration() {
       view!!.onResetTrackDuration()
    }

    override fun onSetTrackPlayer(trackPosition: Int) {
        view!!.setTrackPlayer(trackPosition)
    }

    override fun onSetInfoTrackPlayer(trackPosition: Int) {
        view!!.setInfoTrackPlayer(trackPosition)
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