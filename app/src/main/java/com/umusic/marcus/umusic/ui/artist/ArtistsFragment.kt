package com.umusic.marcus.umusic.ui.artist

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.annotation.Nullable
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.*
import butterknife.BindView
import butterknife.ButterKnife
import com.umusic.marcus.umusic.R
import com.umusic.marcus.umusic.data.model.Artist
import com.umusic.marcus.umusic.data.remote.client.SpotifyClient
import com.umusic.marcus.umusic.interactor.ArtistsInteractor
import com.umusic.marcus.umusic.ui.tracks.TracksActivity
import kotlinx.android.synthetic.main.fragment_artists.*
import kotlinx.android.synthetic.main.toolbar.*


class ArtistsFragment : Fragment(), ArtistsPresenter.View, SearchView.OnQueryTextListener {


    lateinit var artistsPresenter: ArtistsPresenter
    @BindView(R.id.rv_artists)
    lateinit var rv_artist: RecyclerView


    init {
        setHasOptionsMenu(true)
    }

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        artistsPresenter = ArtistsPresenter(ArtistsInteractor(SpotifyClient()))
        artistsPresenter.view = this
    }

    @Nullable override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                                        savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_artists, container, false)

    }

    override fun onViewCreated(view: View?, @Nullable savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (view != null) {
            ButterKnife.bind(this, view)
        }
        setupToolbar()
        setupRecyclerView()
    }

    override fun onDestroy() {
        artistsPresenter.terminate()
        super.onDestroy()
    }

    override fun getContext(): Context {
        return activity
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_music, menu)
        setupSearchView(menu)
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        artistsPresenter.onSearchArtist(query)
        return true
    }

    override fun onQueryTextChange(newText: String): Boolean {
        return true
    }

    override fun showLoading() {
        pv_artists.visibility = View.VISIBLE
        iv_artists.visibility = View.GONE
        txt_line_artists.visibility = View.GONE
        txt_subline_artists!!.visibility = View.GONE
        rv_artist.visibility = View.GONE
    }

    override fun hideLoading() {
        pv_artists!!.visibility = View.GONE
        rv_artist.visibility = View.VISIBLE
    }

    override fun showArtistNotFoundMessage() {
        pv_artists!!.visibility = View.GONE
        txt_line_artists!!.visibility = View.VISIBLE
        iv_artists!!.visibility = View.VISIBLE
        txt_line_artists!!.text = getString(R.string.error_artist_not_found)
        iv_artists!!.setImageDrawable(ContextCompat.getDrawable(context, R.mipmap.ic_not_found))
    }

    override fun showConnectionErrorMessage() {
        pv_artists!!.visibility = View.GONE
        txt_line_artists!!.visibility = View.VISIBLE
        iv_artists!!.visibility = View.VISIBLE
        txt_line_artists!!.text = getString(R.string.error_internet_connection)
        iv_artists!!.setImageDrawable(ContextCompat.getDrawable(context, R.mipmap.ic_not_internet))
    }

    override fun showServerError() {
        pv_artists!!.visibility = View.GONE
        txt_line_artists!!.visibility = View.VISIBLE
        iv_artists!!.visibility = View.VISIBLE
        txt_line_artists!!.text = getString(R.string.error_server_internal)
        iv_artists!!.setImageDrawable(ContextCompat.getDrawable(context, R.mipmap.ic_not_found))
    }

    override fun renderArtists(artists: List<Artist>) {
        val adapter = rv_artist.adapter as ArtistsAdapter
        adapter.setArtists(artists)
        adapter.notifyDataSetChanged()
    }

    private fun setupSearchView(menu: Menu) {
        val searchManager = activity.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.menu_search).actionView as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(activity.componentName))
        searchView.queryHint = getString(R.string.search_hint)
        searchView.maxWidth = toolbar!!.width
        searchView.setOnQueryTextListener(this)
    }

    private fun setupToolbar() {
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        val actionBar = (activity as AppCompatActivity).supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(true)
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.mipmap.ic_action_navigation_menu)
        }
    }

    private fun setupRecyclerView() {
        val adapter = ArtistsAdapter()
        adapter.setItemClickListener(

                itemClickListener = object : ArtistsAdapter.ItemClickListener {

                    override fun onItemClick(artist: Artist, position: Int) {
                        artistsPresenter.launchArtistDetail(artist)
                    }

                }
        )
        rv_artist.adapter = adapter
    }

    override fun launchArtistDetail(artist: Artist) {
        val intent = Intent(context, TracksActivity::class.java)
        intent.putExtra(TracksActivity.EXTRA_REPOSITORY, artist)
        startActivity(intent)
    }


    override fun context(): Context? {
        return null
    }
}