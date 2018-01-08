package com.umusic.marcus.umusic.ui.search

import android.annotation.SuppressLint
import android.app.SearchManager
import android.content.Context
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
import com.umusic.marcus.umusic.ui.artist.ArtistFragment

import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.toolbar.*


class SearchFragment : Fragment(), SearchPresenter.View, SearchView.OnQueryTextListener {

    lateinit var searchView: SearchView
    lateinit var searchPresenter: SearchPresenter
    @BindView(R.id.rv_artists)
    lateinit var rv_artist: RecyclerView


    init {
        setHasOptionsMenu(true)
    }

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        searchPresenter = SearchPresenter(ArtistsInteractor(SpotifyClient()))
        searchPresenter.view = this
    }

    @Nullable override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                                        savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_search, container, false)

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
        searchPresenter.terminate()
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
        searchPresenter.onSearchArtist(query)
        searchView.clearFocus()
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
        val adapter = rv_artist.adapter as SearchAdapter
        adapter.setArtists(artists)
        adapter.notifyDataSetChanged()
    }

    private fun setupSearchView(menu: Menu) {
        val searchManager = activity.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView = menu.findItem(R.id.menu_search).actionView as SearchView
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

        }
    }

    private fun setupRecyclerView() {
        val adapter = SearchAdapter()
        adapter.setItemClickListener(

                itemClickListener = object : SearchAdapter.ItemClickListener {

                    override fun onItemClick(artist: Artist, position: Int) {
                        searchPresenter.launchArtistDetail(artist)
                    }

                }
        )
        rv_artist.adapter = adapter
    }

    @SuppressLint("CommitTransaction")
    override fun launchArtistDetail(artist: Artist) {
        val ft = activity.supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragment_container_search, ArtistFragment.newInstance(artist))
        ft.addToBackStack(null)
        ft.commit()
    }


    override fun context(): Context? {
        return null
    }

    companion object {

        fun newInstance(): SearchFragment {
            return SearchFragment()
        }
    }
}