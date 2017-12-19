package com.umusic.marcus.umusic.ui.home


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.umusic.marcus.umusic.R
import com.umusic.marcus.umusic.data.model.Album
import com.umusic.marcus.umusic.data.model.Category
import com.umusic.marcus.umusic.data.remote.client.SpotifyClient
import com.umusic.marcus.umusic.interactor.HomeInteractor
import kotlinx.android.synthetic.main.fragment_home.*


/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment(), HomePresenter.View {

    override fun launchAlbumDetail(album: Album) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun launchGenreDetail(category: Category) {

        val ft = activity.supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragment_container, PlaylistsFragment.newInstance(category))
        ft.addToBackStack(null)
        ft.commit()

    }


    lateinit var homePresenter: HomePresenter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootview = inflater!!.inflate(R.layout.fragment_home, container, false)

        return rootview
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        setupRecyclerView()
        homePresenter = HomePresenter(HomeInteractor(SpotifyClient()))
        homePresenter.view = this

        homePresenter.getNewReleases()
        homePresenter.getGenres()

    }

    companion object {

        // TODO: Rename and change types and number of parameters
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }

    private fun setupRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rv_releases.layoutManager = linearLayoutManager
        rv_genres.layoutManager = GridLayoutManager(context, 2)

        val genreAdapter = GenreAdapter()
        genreAdapter.setItemClickListener(

                itemClickListener = object : GenreAdapter.ItemClickListener {

                    override fun onItemClick(genres: List<Category>, genre: Category, position: Int) {
                        homePresenter.launchGenreDetail(genre)
                    }

                }

        )
        val releasesAdapter = ReleasesAdapter()
        releasesAdapter.setItemClickListener(

                itemClickListener = object : ReleasesAdapter.ItemClickListener {

                    override fun onItemClick(albums: List<Album>, album: Album, position: Int) {
                        // homePresenter!!.launchTrackDetail(albums, album, position)
                    }

                }

        )
        rv_releases.adapter = releasesAdapter
        rv_genres.adapter = genreAdapter
        rv_genres.isNestedScrollingEnabled = false
        // appbar_artist!!.addOnOffsetChangedListener(this)
    }


    override fun context(): Context {
        return activity
    }

    override fun renderNewReleases(albums: List<Album>) {
        val adapter = rv_releases.adapter as ReleasesAdapter
        adapter.setTracks(albums)
        adapter.notifyDataSetChanged()
    }

    override fun renderGenres(genres: List<Category>) {
        val adapter = rv_genres.adapter as GenreAdapter
        adapter.setGenres(genres)
        adapter.notifyDataSetChanged()
    }

}// Required empty public constructor
