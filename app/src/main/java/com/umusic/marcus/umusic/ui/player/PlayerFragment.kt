package com.umusic.marcus.umusic.ui.player

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Build
import android.os.Bundle
import android.support.annotation.Nullable
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import com.umusic.marcus.umusic.UMusicApplication
import com.umusic.marcus.umusic.data.model.Album
import com.umusic.marcus.umusic.data.model.Track
import com.umusic.marcus.umusic.ui.artist.ArtistFragment
import com.umusic.marcus.umusic.ui.utils.ServiceUtils
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.fragment_audio_player.*
import java.util.*
import javax.inject.Inject


class PlayerFragment : Fragment(), AudioPlayerPresenter.View, SeekBar.OnSeekBarChangeListener {


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
    @Inject
    lateinit var audioPlayerPresenter: AudioPlayerPresenter


    override fun onCreate(savedInstanceState: Bundle?) {

        ((activity.application as UMusicApplication).appComponent)!!.inject(this)
        super.onCreate(savedInstanceState)
    }
    @Nullable
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val rootView = inflater!!.inflate(R.layout.fragment_audio_player2, container, false)
        ButterKnife.bind(this, rootView)


        trackList = getTrackList(arguments.getString(ArtistFragment.EXTRA_TRACKS))
        trackPosition = arguments.getInt(ArtistFragment.EXTRA_TRACK_POSITION)
        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.M -> audioPlayerPresenter.providestracks(trackPosition, context, trackList!!)
        }

        audioPlayerPresenter.view = this
        when {
            arguments.containsKey("album") -> {
                val album = arguments.getParcelable<Album>("album")
                audioPlayerPresenter.setInfoMediaPlayer(trackPosition, album)
            }
            else -> audioPlayerPresenter.setInfoMediaPlayer(trackPosition)
        }
        audioPlayerPresenter.onStartAudioService(trackList!![trackPosition].previewUrl!!)
        return rootView
    }


    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {

        activity.fragment_mini_player_container.visibility = View.GONE
    }
    override fun onDestroyView() {
        val tag1 = "mini"
        when {
            null == activity.supportFragmentManager.findFragmentByTag(tag1) -> {
                val ft = activity.supportFragmentManager.beginTransaction()
                ft.replace(R.id.fragment_mini_player_container, MiniPlayerFragment.newInstance(), tag1)
                ft.commit()
            }
        }
        activity.fragment_mini_player_container.visibility = View.VISIBLE
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

    override fun setTrackPlayer(trackPosition: Int) {
        txt_track_title_player.text = trackList!![trackPosition].name
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

    /**
     * To use if using album for list due to album image being in the album and not track object
     */
    override fun setInfoTrackPlayer(trackPosition: Int, album: Album) {
        txt_track_title_player.text = trackList!![trackPosition].name
        txt_album_title_player.text = album.name

        when {
            album.images!!.isNotEmpty() -> (0 until album.images!!.size)
                    .filter { album.images!!.isNotEmpty() }
                    .forEach {
                        Picasso.with(activity)
                                .load(album.images!![0].url)
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

    /**
     * Start AudioPlayerService
     */
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
        /**
         * To use if using album for list due to album image being in the album and not track object
         */
        fun newInstance(album: Album, tracks: String, position: Int): PlayerFragment {
            val ALBUM = "album"
            val playerFragment = PlayerFragment()
            val bundle = Bundle()
            bundle.putString(ArtistFragment.EXTRA_TRACKS, tracks)
            bundle.putInt(ArtistFragment.EXTRA_TRACK_POSITION, position)
            bundle.putParcelable(ALBUM, album)
            playerFragment.arguments = bundle
            return playerFragment
        }

        fun newInstance(tracks: String, position: Int): PlayerFragment {
            val playerFragment = PlayerFragment()
            val bundle = Bundle()
            bundle.putString(ArtistFragment.EXTRA_TRACKS, tracks)
            bundle.putInt(ArtistFragment.EXTRA_TRACK_POSITION, position)
            playerFragment.arguments = bundle
            return playerFragment
        }
    }
}