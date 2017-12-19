package com.umusic.marcus.umusic.ui.home

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.umusic.marcus.umusic.R
import com.umusic.marcus.umusic.data.model.Category
import com.umusic.marcus.umusic.data.model.Playlist
import com.umusic.marcus.umusic.data.remote.client.SpotifyClient
import com.umusic.marcus.umusic.interactor.PlaylistInteractor
import com.umusic.marcus.umusic.ui.tracks.TracksFragment
import kotlinx.android.synthetic.main.fragment_playlists.*

class PlaylistsFragment : Fragment(), PlaylistsPresenter.View {
    override fun launchPlaylistDetail(playlist: Playlist) {
        val ft = activity.supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragment_container, TracksFragment.newInstance(playlist))
        ft.addToBackStack(null)
        ft.commit()
    }

    override fun renderPlaylists(playlists: List<Playlist>) {
        val adapter = rv_playlists.adapter as PlaylistsAdapter
        adapter.setGenres(playlists)
        adapter.notifyDataSetChanged()
    }

    override fun context(): Context {
        return activity
    }

    // TODO: Customize parameters
    private var mColumnCount = 2
    private lateinit var category: Category
    private val CATEGORY = "category"
    private lateinit var playlistsPresenter: PlaylistsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        category = arguments.getParcelable(CATEGORY)
        if (arguments != null) {
            mColumnCount = arguments.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_playlists, container, false)

        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        setupRecyclerView()
        playlistsPresenter = PlaylistsPresenter(PlaylistInteractor(SpotifyClient()))
        playlistsPresenter.view = this

        playlistsPresenter.getPlaylsts(category)
    }

    private fun setupRecyclerView() {

        rv_playlists.layoutManager = GridLayoutManager(context, 2)
        val playlistsAdapter = PlaylistsAdapter()
        playlistsAdapter.setItemClickListener(

                itemClickListener = object : PlaylistsAdapter.ItemClickListener {

                    override fun onItemClick(playlists: List<Playlist>, playlist: Playlist, position: Int) {
                        playlistsPresenter.launchPlaylistDetail(playlist)
                    }

                }

        )

        rv_playlists.adapter = playlistsAdapter

        // appbar_artist!!.addOnOffsetChangedListener(this)
    }

    companion object {

        // TODO: Customize parameter argument names
        private val ARG_COLUMN_COUNT = "column-count"
        private val CATEGORY = "category"
        fun newInstance(category: Category): PlaylistsFragment {
            val playerFragment = PlaylistsFragment()
            val bundle = Bundle()
            bundle.putParcelable(CATEGORY, category)
            playerFragment.arguments = bundle
            return playerFragment
        }
    }
}
