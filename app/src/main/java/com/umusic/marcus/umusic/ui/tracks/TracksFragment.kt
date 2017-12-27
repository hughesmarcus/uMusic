package com.umusic.marcus.umusic.ui.tracks

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.umusic.marcus.umusic.R
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

    override fun launchTrack(tracks: List<Track>, track: Track, position: Int) {
        PlayerFragment.newInstance(TracksUtil.setTracks(tracks), position)
                .show(
                        activity.supportFragmentManager,

                        ""
                )
    }

    override fun launchTrackOptions() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun context(): Context? {
        return activity
    }


    private var PLAYLIST = "playlist"
    private lateinit var playlist: Playlist
    private lateinit var tracksPresenter: TracksPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        playlist = arguments.getParcelable(PLAYLIST)
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

        tracksPresenter.getTracks(playlist.owner!!.id!!, playlist.id!!)
    }

    private fun setupRecyclerView() {

        rv_tracks.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val tracksAdapter = TracksAdapter()
        tracksAdapter.setItemClickListener(

                itemClickListener = object : TracksAdapter.ItemClickListener {

                    override fun onItemClick(tracks: List<Item>, track: Item, position: Int) {
                        val tracklist: List<Track> = emptyList()
                        for(track in tracks){
                            tracklist.plus(track.track)
                        }
                        tracksPresenter!!.launchTrackDetail(tracklist, track.track!!, position)
                    }

                }

        )

        rv_tracks.adapter = tracksAdapter

        // appbar_artist!!.addOnOffsetChangedListener(this)
    }

    companion object {
        private val PLAYLIST = "playlist"

        fun newInstance(playlist: Playlist): TracksFragment {
            val fragment = TracksFragment()
            val bundle = Bundle()
            bundle.putParcelable(PLAYLIST, playlist)
            fragment.arguments = bundle
            return fragment
        }
    }
}// Required empty public constructor
