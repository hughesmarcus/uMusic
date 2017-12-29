package com.umusic.marcus.umusic.ui.tracks

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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


class TracksFragment : Fragment(), TracksPresenter.View {
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

    override fun launchTrack(tracks: List<Track>, track: Track, position: Int) {
        //for(track in tracks){
        //    track.album = album
        //  }
        when {
            arguments.containsKey(PLAYLIST) ->
                PlayerFragment.newInstance(TracksUtil.setTracks(tracks), position)
                        .show(
                                activity.supportFragmentManager,

                                ""
                        )
            else -> PlayerFragment.newInstance(album, TracksUtil.setTracks(tracks), position)
                    .show(
                            activity.supportFragmentManager,

                            ""
                    )
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
            arguments.containsKey(PLAYLIST) -> tracksPresenter.getTracks(playlist.owner!!.id!!, playlist.id!!)
            else -> tracksPresenter.getAlbumTracks(album.id!!)
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
