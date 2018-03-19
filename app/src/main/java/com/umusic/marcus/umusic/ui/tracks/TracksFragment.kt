package com.umusic.marcus.umusic.ui.tracks

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.squareup.picasso.Picasso
import com.umusic.marcus.umusic.R
import com.umusic.marcus.umusic.data.model.Album
import com.umusic.marcus.umusic.data.model.Item
import com.umusic.marcus.umusic.data.model.Playlist
import com.umusic.marcus.umusic.data.model.Track
import com.umusic.marcus.umusic.data.remote.client.SpotifyClient
import com.umusic.marcus.umusic.interactor.TracksInteractor
import com.umusic.marcus.umusic.ui.player.PlayerFragment
import com.umusic.marcus.umusic.ui.utils.TracksUtil
import kotlinx.android.synthetic.main.fragment_tracks.*


class TracksFragment : Fragment(), AppBarLayout.OnOffsetChangedListener, TracksPresenter.View {
    override fun renderTracks(tracks: List<Item>) {
        val adapter = rv_tracks.adapter as TracksAdapter
        adapter.setTracks(tracks)
        adapter.notifyDataSetChanged()
    }

    override fun renderAlbumTracks(tracks: List<Track>) {
        val adapter = rv_tracks.adapter as AlbumTracksAdapter
        adapter.setTracks(tracks)
        adapter.notifyDataSetChanged()
    }

    @SuppressLint("CommitTransaction")
    override fun launchTrack(tracks: List<Track>, track: Track, position: Int) {
        //for(track in tracks){
        //    track.album = album
        //  }
        //  val ft = fragmentManager.beginTransaction()
        // val prev = activity.supportFragmentManager.findFragmentByTag("play")
        // if (prev != null) {
        //      ft.remove(prev)
        //  }
        when {
            arguments.containsKey(PLAYLIST) -> {
                val ft = activity.supportFragmentManager.beginTransaction()
                ft.replace(R.id.fragment_container, PlayerFragment.newInstance(TracksUtil.setTracks(tracks), position))
                ft.addToBackStack(null)
                ft.commit()
            }
            else -> {
                val ft = activity.supportFragmentManager.beginTransaction()
                ft.replace(R.id.fragment_container, PlayerFragment.newInstance(album, TracksUtil.setTracks(tracks), position))
                ft.addToBackStack(null)
                ft.commit()
            }
        }
    }

    override fun launchTrackOptions() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun context(): Context? {
        return activity
    }


    private var PLAYLIST = "playlist"
    private lateinit var playlist: Playlist
    private val ALBUM = "album"
    private lateinit var tracksPresenter: TracksPresenter
    private lateinit var album: Album

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        when {
            arguments.containsKey(PLAYLIST) -> playlist = arguments.getParcelable(PLAYLIST)
            else -> album = arguments.getParcelable(ALBUM)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_tracks, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        setupRecyclerView()

        tracksPresenter = TracksPresenter(TracksInteractor(SpotifyClient()))
        tracksPresenter.view = this
        when {
            arguments.containsKey(PLAYLIST) -> {
                initializeViews(playlist)
                tracksPresenter.getTracks(playlist.owner!!.id!!, playlist.id!!)
            }
            else -> {
                initializeViews(album)
                tracksPresenter.getAlbumTracks(album.id!!)
            }
        }

    }


    private fun setupRecyclerView() {

        rv_tracks.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        when {
            arguments.containsKey(PLAYLIST) -> {
                val tracksAdapter = TracksAdapter()
                tracksAdapter.setItemClickListener(

                        itemClickListener = object : TracksAdapter.ItemClickListener {

                            override fun onItemClick(tracks: List<Item>, track: Item, position: Int) {
                                if (track.track!!.previewUrl != null) {
                                    val tracklist: MutableList<Track> = mutableListOf<Track>()
                                    tracks.mapTo(tracklist) { it.track!! }
                                    tracksPresenter.launchTrackDetail(tracklist, track.track!!, position)
                                } else {
                                    val toast = Toast.makeText(activity, "This track is not available", Toast.LENGTH_LONG)
                                    toast.show()
                                }
                            }

                        }

                )
                tracksAdapter.setImageClickListener(

                        imageClickListener = object : TracksAdapter.ImageClickListener {

                            override fun onImageClick(tracks: List<Item>, track: Item, position: Int) {
                                TrackOptionsFragment.newInstance().show(
                                        activity.supportFragmentManager,
                                        null
                                )
                            }

                        }

                )
                rv_tracks.adapter = tracksAdapter
            }
            else -> {
                val tracksAlbumAdapter = AlbumTracksAdapter()
                tracksAlbumAdapter.setItemClickListener(

                        itemClickListener = object : AlbumTracksAdapter.ItemClickListener {

                            override fun onItemClick(tracks: List<Track>, track: Track, position: Int) {
                                if (track.previewUrl != null) {
                                    tracksPresenter.launchTrackDetail(tracks, track, position)
                                } else {
                                    val toast = Toast.makeText(activity, "This track is not available", Toast.LENGTH_LONG)
                                    toast.show()
                                }
                            }

                        }

                )
                rv_tracks.adapter = tracksAlbumAdapter
            }
        }


    }

    override fun showLoading() {
        pv_tracks!!.visibility = View.VISIBLE
        iv_tracks!!.visibility = View.GONE
        txt_line_tracks!!.visibility = View.GONE
        rv_tracks!!.visibility = View.GONE
    }

    override fun hideLoading() {
        pv_tracks!!.visibility = View.GONE
        rv_tracks!!.visibility = View.VISIBLE
    }

    override fun showTracksNotFoundMessage() {
        pv_tracks!!.visibility = View.GONE
        txt_line_tracks!!.visibility = View.VISIBLE
        iv_tracks!!.visibility = View.VISIBLE
        txt_line_tracks!!.text = getString(R.string.error_tracks_not_found)
        iv_tracks!!.setImageDrawable(ContextCompat.getDrawable(context, R.mipmap.ic_not_found))
    }

    override fun showConnectionErrorMessage() {
        pv_tracks!!.visibility = View.GONE
        txt_line_tracks!!.visibility = View.VISIBLE
        iv_tracks!!.visibility = View.VISIBLE
        txt_line_tracks!!.text = getString(R.string.error_internet_connection)
        iv_tracks!!.setImageDrawable(ContextCompat.getDrawable(context, R.mipmap.ic_not_internet))
    }

    private fun hideAndShowTitleToolbar(visibility: Int) {
        txt_title_tracks!!.visibility = visibility
        txt_subtitle_artist!!.visibility = visibility
    }

    private fun initializeViews(playlist: Playlist) {

        when {
            playlist.images!!.size > 0 -> {
                Picasso.with(activity)
                        .load(playlist.images!![0].url)
                        .fit()
                        .centerInside()
                        .into(iv_collapsing_artist)
            }
            else -> {
                val imageHolder = "http://d2c87l0yth4zbw-2.global.ssl.fastly.net/i/_global/open-graph-default.png"
                Picasso.with(activity)
                        .load(imageHolder)
                        .into(iv_collapsing_artist)
            }
        }

        txt_title_artist!!.text = playlist.name
        txt_subtitle_artist!!.text = playlist.name

    }

    private fun initializeViews(album: Album) {

        when {
            album.images!!.size > 0 -> {
                Picasso.with(activity)
                        .load(album.images!![0].url)
                        .fit()
                        .centerInside()
                        .into(iv_collapsing_artist)
            }
            else -> {
                val imageHolder = "http://d2c87l0yth4zbw-2.global.ssl.fastly.net/i/_global/open-graph-default.png"
                Picasso.with(activity)
                        .load(imageHolder)
                        .into(iv_collapsing_artist)
            }
        }

        txt_title_artist!!.text = album.name
        txt_subtitle_artist!!.text = album.name

    }

    override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
        onOffsetChangedState(appBarLayout, verticalOffset)
    }

    private fun onOffsetChangedState(appBarLayout: AppBarLayout, verticalOffset: Int) {
        when {
            verticalOffset == 0 -> hideAndShowTitleToolbar(View.GONE)
            Math.abs(verticalOffset) >= appBarLayout.totalScrollRange -> hideAndShowTitleToolbar(View.VISIBLE)
            else -> hideAndShowTitleToolbar(View.GONE)
        }
    }

    override fun onDestroy() {
        tracksPresenter.terminate()
        super.onDestroy()

    }

    companion object {
        private val PLAYLIST = "playlist"
        private val ALBUM = "album"
        fun newInstance(album: Album): TracksFragment {
            val fragment = TracksFragment()
            val bundle = Bundle()
            bundle.putParcelable(ALBUM, album)
            fragment.arguments = bundle
            return fragment
        }

        fun newInstance(playlist: Playlist): TracksFragment {
            val fragment = TracksFragment()
            val bundle = Bundle()
            bundle.putParcelable(PLAYLIST, playlist)
            fragment.arguments = bundle
            return fragment
        }
    }
}
