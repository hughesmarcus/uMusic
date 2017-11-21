package com.umusic.marcus.umusic.ui.tracks

import android.content.Context
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import android.view.View
import butterknife.ButterKnife
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.squareup.picasso.Picasso
import com.umusic.marcus.umusic.R
import com.umusic.marcus.umusic.data.model.Artist
import com.umusic.marcus.umusic.data.model.Track
import com.umusic.marcus.umusic.data.remote.client.SpotifyClient
import com.umusic.marcus.umusic.interactor.TracksInteractor
import com.umusic.marcus.umusic.ui.utils.BlurEffectUtils
import kotlinx.android.synthetic.main.activity_tracks.*


class TracksActivity : AppCompatActivity(), TracksPresenter.View, AppBarLayout.OnOffsetChangedListener {



    private var tracksPresenter: TracksPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tracks)

        ButterKnife.bind(this)
        setupToolbar()
        setupRecyclerView()

        tracksPresenter = TracksPresenter(TracksInteractor(SpotifyClient()))
        tracksPresenter!!.view = this

        val artist = intent.getParcelableExtra<Artist>(EXTRA_REPOSITORY)
        initializeViews(artist)

        tracksPresenter!!.onSearchTracks(artist.id!!)
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
        iv_tracks!!.setImageDrawable(ContextCompat.getDrawable(context(), R.mipmap.ic_not_found))
    }

    override fun showConnectionErrorMessage() {
        pv_tracks!!.visibility = View.GONE
        txt_line_tracks!!.visibility = View.VISIBLE
        iv_tracks!!.visibility = View.VISIBLE
        txt_line_tracks!!.text = getString(R.string.error_internet_connection)
        iv_tracks!!.setImageDrawable(ContextCompat.getDrawable(context(), R.mipmap.ic_not_internet))
    }

    override fun renderTracks(tracks: List<Track>) {
        val adapter = rv_tracks!!.adapter as TracksAdapter
        adapter.setTracks(tracks)
        adapter.notifyDataSetChanged()
    }

    override fun launchTrackDetail(tracks: List<Track>, track: Track, position: Int) {
        PlayerFragment.newInstance(setTracks(tracks), position)
              .show(
                      supportFragmentManager,

                       ""
               )
    }

    override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
        onOffsetChangedState(appBarLayout, verticalOffset)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId === android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun onOffsetChangedState(appBarLayout: AppBarLayout, verticalOffset: Int) {
        if (verticalOffset == 0) {
            hideAndShowTitleToolbar(View.GONE)
        } else if (Math.abs(verticalOffset) >= appBarLayout.totalScrollRange) {
            hideAndShowTitleToolbar(View.VISIBLE)
        } else {
            hideAndShowTitleToolbar(View.GONE)
        }
    }

    private fun hideAndShowTitleToolbar(visibility: Int) {
        txt_title_tracks!!.visibility = visibility
        txt_subtitle_artist!!.visibility = visibility
    }

    private fun setupRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv_tracks!!.layoutManager = linearLayoutManager
        val adapter = TracksAdapter()
        adapter.setItemClickListener(

                itemClickListener = object : TracksAdapter.ItemClickListener {

                    override fun onItemClick(tracks: List<Track>, track: Track, position: Int) {
                        tracksPresenter!!.launchArtistDetail(tracks, track, position)
                    }


                }
        )
        rv_tracks!!.adapter = adapter

        appbar_artist!!.addOnOffsetChangedListener(this)
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayUseLogoEnabled(false)
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setDisplayShowTitleEnabled(false)
        }
    }

    private fun initializeViews(artist: Artist) {

        if (artist.images!!.size > 0) {
            Picasso.with(this)
                    .load(artist.images!![0].url)
                    .transform(BlurEffectUtils(this, 20))
                    .into(iv_collapsing_artist)
            Picasso.with(this).load(artist.images!![0].url).into(civ_artist)
        } else {
            val imageHolder = "http://d2c87l0yth4zbw-2.global.ssl.fastly.net/i/_global/open-graph-default.png"
            civ_artist!!.visibility = View.GONE
            Picasso.with(this)
                    .load(imageHolder)
                    .transform(BlurEffectUtils(this, 20))
                    .into(iv_collapsing_artist)
        }

        txt_title_artist!!.text = artist.name
        txt_subtitle_artist!!.text = artist.name
        val totalFollowers = resources.getQuantityString(R.plurals.numberOfFollowers,
                artist.followers!!.total!!, artist.followers!!.total)
        txt_followers_artist!!.text = totalFollowers
    }

    private fun setTracks(tracks: List<Track>): String {
        val gson = GsonBuilder().create()
        val trackType = object : TypeToken<List<Track>>() {

        }.type
        return gson.toJson(tracks, trackType)
    }

    override fun context(): Context {
        return this@TracksActivity
    }

    companion object {

        val EXTRA_REPOSITORY = "EXTRA_ARTIST"
        val EXTRA_TRACK_POSITION = "EXTRA_TRACK_POSITION"
        val EXTRA_TRACKS = "EXTRA_TRACKS"
    }
}