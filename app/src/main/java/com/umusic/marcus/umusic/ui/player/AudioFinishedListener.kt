package com.umusic.marcus.umusic.ui.player

import android.content.ServiceConnection


interface AudioFinishedListener {

    fun onPlay()

    fun onPause()

    fun onSetTimeStart(trackCurrentPosition: Int)

    fun onSetTimeFinished(audioPlayerService: AudioPlayerService)

    fun onResetTrackDuration()

    fun onSetInfoTrackPlayer(trackPosition: Int)

    fun onSetTrackPlayer(trackPosition: Int)

    fun onServiceConnection(serviceConnection: ServiceConnection)
}