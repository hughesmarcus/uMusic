package com.umusic.marcus.umusic.interactor

import android.annotation.SuppressLint
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Handler
import android.os.IBinder
import android.os.Message
import com.umusic.marcus.umusic.data.model.Track
import com.umusic.marcus.umusic.ui.player.AudioFinishedListener
import com.umusic.marcus.umusic.ui.player.AudioPlayerService


class PlayerInteractor(private val trackList: List<Track>, private val context: Context, private var trackPosition: Int) {

    private var audioFinishedListener: AudioFinishedListener? = null
    private var audioPlayerService: AudioPlayerService? = null
    private var isServiceBounded: Boolean = false
    private var isPlayerPlaying = false
    private var isPlayerPaused = false
    private var trackDuration = 0
    private var trackCurrentPosition: Int = 0
    @SuppressLint("HandlerLeak") private val playerHandler = object : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            if (trackDuration == 0) {
                setTrackDuration()
            }
            if (msg.data.getInt(AudioPlayerService.EXTRA_COMPLETE) == 1) {
                onNext()
            } else
                trackCurrentPosition = msg.data.getInt(AudioPlayerService.EXTRA_CURRENT_TRACK_POSITION)
            audioFinishedListener!!.onSetTimeStart(trackCurrentPosition)

            if (trackCurrentPosition == trackDuration && trackCurrentPosition != 0) {
                isPlayerPlaying = false
                isPlayerPaused = false
                trackCurrentPosition = 0
            }
            if (isPlayerPlaying) {
                audioFinishedListener!!.onPause()
            } else {
                audioFinishedListener!!.onPlay()
            }
        }
    }
    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(componentName: ComponentName, iBinder: IBinder) {
            val playerBinder = iBinder as AudioPlayerService.PlayerBinder
            audioPlayerService = playerBinder.service
            isServiceBounded = true
            if (!isPlayerPlaying) {
                isPlayerPlaying = true
            }
            setTrackDuration()
            audioPlayerService!!.setAudioPlayerHandler(playerHandler)
        }

        override fun onServiceDisconnected(componentName: ComponentName) {
            isServiceBounded = false
        }
    }

    fun setAudioFinishedListener(audioFinishedListener: AudioFinishedListener) {
        this.audioFinishedListener = audioFinishedListener
        audioFinishedListener.onServiceConnection(serviceConnection)
    }

    private fun setTrackDuration() {
        if (audioPlayerService != null) {
            trackDuration = audioPlayerService!!.trackDuration
            audioFinishedListener!!.onSetTimeFinished(audioPlayerService!!)
        }
    }

    private fun changeTrack(currtrackPosition: Int) {
        var track = trackPosition
        isPlayerPlaying = true
        isPlayerPaused = false
        // if (trackList[trackPosition].previewUrl == null) {
        //    changeTrack(trackPosition++)
//        //   if (trackList[trackNumber].previewUrl == null) {
        //      destroyAudioService()
        //  }
        if (trackList[trackPosition].album == null) {
            audioFinishedListener!!.onSetTrackPlayer(trackPosition)
        } else {
            audioFinishedListener!!.onSetInfoTrackPlayer(trackPosition)
        }
        audioPlayerService!!.setTrackPreviewUrl(trackList[trackPosition].previewUrl!!)
        audioPlayerService!!.noUpdateUI()
        audioPlayerService!!.onPlayAudio(0)
        audioFinishedListener!!.onResetTrackDuration()
        trackDuration = 0
    }

    fun destroyAudioService() {

        if (audioPlayerService != null) {
            audioPlayerService!!.noUpdateUI()
            if (isServiceBounded) {
                context.applicationContext.unbindService(serviceConnection)
                isServiceBounded = false
            }
        }
        if (!isPlayerPaused && !isPlayerPlaying) {
            context.applicationContext.stopService(Intent(context, AudioPlayerService::class.java))
        }
    }

    fun onPreview() {
        trackPosition -= 1
        if (trackPosition < 0) {
            trackPosition = trackList.size - 1
        }
        while (trackList[trackPosition].previewUrl == null) {
            trackPosition--
        }
        changeTrack(trackPosition)
    }

    fun onNext() {
        trackPosition = (trackPosition + 1) % trackList.size
        while (trackList[trackPosition].previewUrl == null) {
            trackPosition = (trackPosition + 1) % trackList.size
        }
        changeTrack(trackPosition)
    }

    fun onPlayStop() {

        if (isPlayerPlaying) {
            audioFinishedListener!!.onPlay()
            audioPlayerService!!.onPauseAudio()
            isPlayerPaused = true
            isPlayerPlaying = false
        } else {
            audioFinishedListener!!.onPause()
            audioPlayerService!!.onPlayAudio(trackCurrentPosition)
            isPlayerPaused = true
            isPlayerPlaying = true
        }
    }
}