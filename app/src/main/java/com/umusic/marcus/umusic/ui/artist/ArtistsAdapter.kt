package com.umusic.marcus.umusic.ui.artist

import com.umusic.marcus.umusic.data.model.Artist
import android.widget.TextView
import android.support.v7.widget.RecyclerView
import com.squareup.picasso.Picasso
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import butterknife.BindView
import butterknife.ButterKnife
import com.umusic.marcus.umusic.R
import java.util.*
import kotlinx.android.synthetic.main.item_artist.*
import com.umusic.marcus.umusic.ui.artist.ArtistsAdapter.ItemClickListener
import kotlinx.android.synthetic.main.item_artist.view.*


class ArtistsAdapter : RecyclerView.Adapter<ArtistsAdapter.ArtistsViewHolder>() {

    private var artists: List<Artist>? = null
    private var itemClickListener: ItemClickListener? = null

    init {
        artists = Collections.emptyList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_artist, null)
        return ArtistsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ArtistsViewHolder, position: Int) {
        val artist = artists!![position]
        holder.bind(artist)

        holder.itemView.setOnClickListener({ view: View ->
            if (itemClickListener != null) {
                itemClickListener!!.onItemClick(artist, position)
            }
        })
    }

    override fun getItemCount(): Int {
        return artists!!.size
    }

    fun setArtists(artists: List<Artist>) {
        this.artists = artists
    }

    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    interface ItemClickListener {
        fun onItemClick(artist: Artist, position: Int)
    }

    class ArtistsViewHolder(internal var itemView1: View) : RecyclerView.ViewHolder(itemView1) {

        fun bind(artist: Artist) = with(itemView) {
            txt_artist_name.text = artist.name
            if (artist.images!!.isNotEmpty()) {

                for (i in artist.images!!.indices) {
                    if (artist.images!![i] != null && artist.images!!.size > 0) {
                        Picasso.with(img_view_artist_image.context)
                                .load(artist.images!![0].url)
                                .into(img_view_artist_image)
                    }
                }
            } else {
                val imageHolder = "http://www.iphonemode.com/wp-content/uploads/2016/07/Spotify-new-logo.jpg"
                Picasso.with(img_view_artist_image.context).load(imageHolder).into(img_view_artist_image)
            }

        }
    }
}
