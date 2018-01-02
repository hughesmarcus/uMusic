package com.umusic.marcus.umusic.ui.search

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import com.umusic.marcus.umusic.R
import com.umusic.marcus.umusic.data.model.Artist
import kotlinx.android.synthetic.main.item_artist.view.*
import java.util.*


class SearchAdapter : RecyclerView.Adapter<SearchAdapter.ArtistsViewHolder>() {

    private var artists: List<Artist>? = null
    private var itemClickListener: ItemClickListener? = null

    init {
        artists = Collections.emptyList()
    }

    @SuppressLint("InflateParams")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_artist, null)
        return ArtistsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ArtistsViewHolder, position: Int) {
        val artist = artists!![position]
        holder.bind(artist)

        holder.itemView.setOnClickListener({
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
                    if (artist.images!!.size > 0) {
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
