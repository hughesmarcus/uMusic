package com.umusic.marcus.umusic.ui.player

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Build
import android.os.Bundle
import android.support.annotation.NonNull
import android.support.annotation.Nullable
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.squareup.picasso.Picasso
import com.umusic.marcus.umusic.R
import com.umusic.marcus.umusic.data.model.Track
import com.umusic.marcus.umusic.interactor.PlayerInteractor
import com.umusic.marcus.umusic.ui.artist.ArtistActivity
import com.umusic.marcus.umusic.ui.utils.ServiceUtils
import kotlinx.android.synthetic.main.fragment_audio_player.*
import java.util.*




class PlayerFragment : DialogFragment(), AudioPlayerPresenter.View, SeekBar.OnSeekBarChangeListener {


    @BindView(R.id.iv_album_player)
    lateinit var iv_album_player: ImageView
    @BindView(R.id.txt_track_title_player)
    lateinit var txt_track_title_player: TextView
    @BindView(R.id.txt_album_title_player)
    lateinit var txt_album_title_player: TextView
    private  var audioPlayerService: AudioPlayerService? = null
    private val isPlayerPlaying = false
    private val isPlayerPaused = false
    private var trackCurrentPosition: Int = 0

    private var trackList: List<Track>? = ArrayList()
    private var trackPosition: Int = 0
    private lateinit var audioPlayerPresenter: AudioPlayerPresenter

    @NonNull
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        return dialog
    }

    @Nullable
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val rootView = inflater!!.inflate(R.layout.fragment_audio_player, container, false)
        ButterKnife.bind(this, rootView)

        trackList = getTrackList(arguments.getString(ArtistActivity.EXTRA_TRACKS))
        trackPosition = arguments.getInt(ArtistActivity.EXTRA_TRACK_POSITION)
        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.M -> audioPlayerPresenter = AudioPlayerPresenter(PlayerInteractor(trackList!!, context))
        }
        audioPlayerPresenter.view = this

        audioPlayerPresenter.setInfoMediaPlayer(trackPosition)
        audioPlayerPresenter.onStartAudioService(trackList!![trackPosition].previewUrl!!)

        return rootView
    }

    override fun onDestroyView() {
        when {
            dialog != null && retainInstance -> dialog.setDismissMessage(null)
        }
        audioPlayerPresenter.terminate()
        super.onDestroyView()
    }

    @OnClick(R.id.ib_preview_player)
    fun previewTrack() {
        audioPlayerPresenter.onPreviewTrack()
    }

    @OnClick(R.id.ib_next_player)
    fun nextTrack() {
        audioPlayerPresenter.onNextTrack()
    }

    @OnClick(R.id.ib_play_player)
    fun playTrack() {
        audioPlayerPresenter.onPlayPauseTrack()
    }

    override fun setInfoTrackPlayer(trackPosition: Int) {
        txt_track_title_player.text = trackList!![trackPosition].name
        txt_album_title_player.text = trackList!![trackPosition].album!!.name

        when {
            trackList!![trackPosition].album!!.images!!.isNotEmpty() -> (0 until trackList!![trackPosition].album!!.images!!.size)
                    .filter { trackList!![trackPosition].album!!.images!!.isNotEmpty() }
                    .forEach {
                        Picasso.with(activity)
                                .load(trackList!![trackPosition].album!!.images!![0].url)
                                .into(iv_album_player)
                    }
            else -> Picasso.with(activity)
                    .load("http://d2c87l0yth4zbw-2.global.ssl.fastly.net/i/_global/open-graph-default.png")
                    .into(iv_album_player)
        }
    }

    override fun onDestroy() {
        audioPlayerPresenter.terminate()
        super.onDestroy()
    }

    private fun getTrackList(tracks: String?): List<Track> {
        val gson = GsonBuilder().create()
        val trackType = object : TypeToken<List<Track>>() {

        }.type
        return gson.fromJson(tracks, trackType)
    }

    @SuppressLint("SetTextI18n")
    override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
        txt_time_start!!.text = "00:" + String.format("%02d", i)
        trackCurrentPosition = i
    }

    override fun onStartTrackingTouch(seekBar: SeekBar) {
        when {
            isPlayerPlaying -> audioPlayerService!!.noUpdateUI()
        }
    }

    override fun onStopTrackingTouch(seekBar: SeekBar) {
        trackCurrentPosition = seekBar.progress
        when {
            audioPlayerService != null -> audioPlayerService!!.toSeekTrack(trackCurrentPosition, isPlayerPaused)
        }
    }

    override fun onStartAudioService(trackUrl: String, serviceConnection: ServiceConnection) {

        val serviceIntent = Intent(activity, AudioPlayerService::class.java)
        serviceIntent.putExtra(AudioPlayerService.EXTRA_TRACK_PREVIEW_URL, trackUrl)

        when {
            ServiceUtils.isAudioPlayerServiceRunning(AudioPlayerService::class.java, activity) && !isPlayerPlaying -> {
                trackCurrentPosition = 0
                activity.applicationContext.stopService(serviceIntent)
                activity.applicationContext.startService(serviceIntent)
            }
            !ServiceUtils.isAudioPlayerServiceRunning(AudioPlayerService::class.java, activity) -> {
                trackCurrentPosition = 0
                activity.applicationContext.startService(serviceIntent)
            }
        }
        when (audioPlayerService) {
            null -> activity.applicationContext
                    .bindService(serviceIntent, serviceConnection, Context.BIND_AUTO_CREATE)
        }
    }

    override fun isPlay() {
        ib_play_player!!.setImageResource(android.R.drawable.ic_media_play)
    }

    override fun isPause() {
        ib_play_player!!.setImageResource(android.R.drawable.ic_media_pause)
    }

    override fun setTimeStart(trackCurrentPosition: Int) {
        sb_time_progress_player!!.progress = trackCurrentPosition
        txt_time_start!!.text = "00:" + String.format("%02d", trackCurrentPosition)
    }

    override fun setTimeFinished(audioPlayerService: AudioPlayerService) {
        sb_time_progress_player!!.max = audioPlayerService.trackDuration
        txt_time_end!!.text = audioPlayerService.trackDurationString
    }

    override fun onResetTrackDuration() {
        sb_time_progress_player!!.progress = 0
        txt_time_start!!.text = ""
        txt_time_end!!.text = ""
    }

    override fun context(): Context {
        return activity
    }

    companion object {
        fun newInstance(tracks: String, position: Int): PlayerFragment {
            val playerFragment = PlayerFragment()
            val bundle = Bundle()
            bundle.putString(ArtistActivity.EXTRA_TRACKS, tracks)
            bundle.putInt(ArtistActivity.EXTRA_TRACK_POSITION, position)
            playerFragment.arguments = bundle
            return playerFragment
        }
    }
}