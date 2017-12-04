package com.umusic.marcus.umusic.ui.home

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.umusic.marcus.umusic.BaseActivity
import com.umusic.marcus.umusic.R
import com.umusic.marcus.umusic.data.model.Album
import com.umusic.marcus.umusic.data.remote.client.SpotifyClient
import com.umusic.marcus.umusic.interactor.ReleaseInteractor
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : BaseActivity(), HomePresenter.View {


    private var homePresenter: HomePresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setupToolbar()
        setupRecyclerView()
        intNav()
        homePresenter = HomePresenter(ReleaseInteractor(SpotifyClient()))
        homePresenter!!.view = this

        homePresenter!!.getNewReleases()
    }

    private fun setupRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rv_releases!!.layoutManager = linearLayoutManager
        val adapter = ReleasesAdapter()
        adapter.setItemClickListener(

                itemClickListener = object : ReleasesAdapter.ItemClickListener {

                    override fun onItemClick(albums: List<Album>, album: Album, position: Int) {
                        // homePresenter!!.launchTrackDetail(albums, album, position)
                    }

                }

        )
        rv_releases!!.adapter = adapter

        // appbar_artist!!.addOnOffsetChangedListener(this)
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        when {
            actionBar != null -> {
                actionBar.setDisplayUseLogoEnabled(false)
                // actionBar.setDisplayHomeAsUpEnabled(true)
                actionBar.setDisplayShowTitleEnabled(false)
            }
        }
    }


    override fun context(): Context {
        return this@HomeActivity
    }

    override fun renderNewReleases(albums: List<Album>) {
        val adapter = rv_releases!!.adapter as ReleasesAdapter
        adapter.setTracks(albums)
        adapter.notifyDataSetChanged()
    }


}
