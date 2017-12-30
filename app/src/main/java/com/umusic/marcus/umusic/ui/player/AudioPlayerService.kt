package com.umusic.marcus.umusic.ui.player

import android.app.Service
import android.content.Intent
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.*
import android.support.annotation.Nullable
import java.io.IOException
import java.util.*

@Suppress("DEPRECATION")
class AudioPlayerService : Service(), MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener {

    private var mediaPlayerBinder: PlayerBinder? = null
    private var mediaPlayerHandler: Handler? = null
    private var timer: Timer? = null
    private var currentTrackPosition: Int = 0
    private var isPlayerPaused: Boolean = false
    private var mediaPlayer: MediaPlayer? = null
    private var trackPreviewUrl: String? = null

    val trackDurationString: String
        get() = "00:" + String.format("%02d", trackDuration)

    val trackDuration: Int
        get() = if (mediaPlayer != null && (isPlayerPaused || mediaPlayer!!.isPlaying)) {
            mediaPlayer!!.duration / 1000
        } else {
            0
        }


    override fun onCreate() {
        super.onCreate()
        mediaPlayerBinder = PlayerBinder()
    }


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        when {
            intent != null
                    && intent.hasExtra(EXTRA_TRACK_PREVIEW_URL)
                    && intent.getStringExtra(EXTRA_TRACK_PREVIEW_URL) != null -> {
                setTrackPreviewUrl(intent.getStringExtra(EXTRA_TRACK_PREVIEW_URL))
                onPlayAudio(0)
            }
        }
        return Service.START_STICKY
    }


    @Nullable override fun onBind(intent: Intent): IBinder? {
        return mediaPlayerBinder
    }


    override fun onPrepared(mediaPlayer: MediaPlayer) {
        mediaPlayer.start()
        if (currentTrackPosition != 0) {
            mediaPlayer.seekTo(currentTrackPosition * 1000)
        }

        updateUI()
    }


    override fun onCompletion(mediaPlayer: MediaPlayer) {

        val completionMessage = Message()
        val completionBundle = Bundle()
        completionBundle.putBoolean(EXTRA_IS_PLAYER, false)
        completionBundle.putInt(EXTRA_COMPLETE, 1)
        completionMessage.data = completionBundle
        if (mediaPlayerHandler != null) {
            mediaPlayerHandler!!.sendMessage(completionMessage)
        }

        noUpdateUI()
    }

    override fun onError(mediaPlayer: MediaPlayer, i: Int, i1: Int): Boolean {
        return false
    }

    /**
     * @param trackPreviewUrl
     */
    fun setTrackPreviewUrl(trackPreviewUrl: String) {
        this.trackPreviewUrl = trackPreviewUrl
    }

    fun onPlayAudio(trackPosition: Int) {
        currentTrackPosition = trackPosition
        when {
            mediaPlayer != null -> {
                when {
                    mediaPlayer!!.isPlaying -> mediaPlayer!!.stop()
                }

                mediaPlayer!!.reset()
            }
        }
        setupAudioPlayer()
        isPlayerPaused = false
    }

    fun onPauseAudio(): Int {
        return when {
            mediaPlayer != null && mediaPlayer!!.isPlaying -> {
                mediaPlayer!!.pause()
                isPlayerPaused = true
                noUpdateUI()
                mediaPlayer!!.duration / 1000
            }
            else -> 0
        }
    }


    private fun setupAudioPlayer() {

        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer()
        }

        mediaPlayer!!.setAudioStreamType(AudioManager.STREAM_MUSIC)
        try {
            mediaPlayer!!.setDataSource(trackPreviewUrl)
            mediaPlayer!!.prepareAsync()
            mediaPlayer!!.setOnCompletionListener(this@AudioPlayerService)
            mediaPlayer!!.setOnPreparedListener(this@AudioPlayerService)
        } catch (e: IOException) {
            e.printStackTrace()
        }

        mediaPlayer!!.setOnErrorListener(this@AudioPlayerService)
    }

    override fun onDestroy() {
        super.onDestroy()
        when {
            timer != null -> noUpdateUI()
        }
        when {
            mediaPlayer != null -> {
                mediaPlayer!!.release()
                mediaPlayer = null
            }
        }
        when {
            mediaPlayerHandler != null -> mediaPlayerHandler = null
        }
    }

    fun noUpdateUI() {
        if (timer != null) {
            timer!!.cancel()
            timer!!.purge()
        }
    }

    fun updateUI() {
        timer = Timer()
        timer!!.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                sendCurrentTrackPosition()
            }
        }, 0, 1000)
    }

    private fun sendCurrentTrackPosition() {
        val message = Message()
        message.data = getCurrentTrackPosition()
        if (mediaPlayerHandler != null) {
            mediaPlayerHandler!!.sendMessage(message)
        }
    }

    private fun getCurrentTrackPosition(): Bundle {
        val uiBundle = Bundle()
        if (mediaPlayer != null && (isPlayerPaused || mediaPlayer!!.isPlaying)) {
            uiBundle.putBoolean(EXTRA_IS_PLAYER, true)
            val trackPosition = Math.ceil(mediaPlayer!!.currentPosition.toDouble() / 1000).toInt()
            uiBundle.putInt(EXTRA_CURRENT_TRACK_POSITION, trackPosition)
        }
        return uiBundle
    }

    fun toSeekTrack(trackProgress: Int, isTrackPaused: Boolean) {
        if (mediaPlayer != null && isTrackPaused && !mediaPlayer!!.isPlaying || mediaPlayer != null && mediaPlayer!!.isPlaying) {
            mediaPlayer!!.seekTo(trackProgress * 1000)
            if (mediaPlayer!!.isPlaying) {
                updateUI()
            }
        }
    }

    fun setAudioPlayerHandler(spotifyPlayerHandler: Handler) {

        this.mediaPlayerHandler = spotifyPlayerHandler
        val playerMessage = Message()
        val playerBundle: Bundle
        when {
            this.mediaPlayerHandler != null && (isPlayerPaused || mediaPlayer!!.isPlaying) -> {
                playerBundle = getCurrentTrackPosition()

                if (!isPlayerPaused) {
                    updateUI()
                } else {
                    playerBundle.putBoolean(EXTRA_IS_PLAYER, false)
                }
                playerMessage.data = playerBundle
                if (this.mediaPlayerHandler != null) {
                    this.mediaPlayerHandler!!.sendMessage(playerMessage)
                }
            }
        }
    }

    inner class PlayerBinder : Binder() {
        val service: AudioPlayerService
            get() = this@AudioPlayerService
    }

    companion object {

        val EXTRA_IS_PLAYER = "is_player"
        val EXTRA_CURRENT_TRACK_POSITION = "current_track_position"
        val EXTRA_TRACK_PREVIEW_URL = "track_preview_url"
        val EXTRA_COMPLETE = "track_complete"
    }
}