package com.umusic.marcus.umusic.ui.home

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.squareup.picasso.Picasso
import com.umusic.marcus.umusic.R
import com.umusic.marcus.umusic.data.model.Playlist
import java.util.*

class PlaylistsAdapter : RecyclerView.Adapter<PlaylistsAdapter.PlaylistsViewHolder>() {
    private var playlists: List<Playlist>? = null
    private var itemClickListener: ItemClickListener? = null

    init {
        playlists = Collections.emptyList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_playlist, parent, false)
        return PlaylistsViewHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: PlaylistsViewHolder, position: Int) {
        val playlist = playlists!![position]


        holder.txt_playlist_name!!.text = playlist.name
        if (playlist.images!!.isNotEmpty()) {
            holder.imageView!!.scaleType = ImageView.ScaleType.FIT_XY
            for (i in 0 until playlist.images!!.size) {
                if (playlist.images!!.isNotEmpty()) {
                    Picasso.with(holder.imageView!!.context)
                            .load(playlist.images!![0].url)
                            .into(holder.imageView)
                }
            }
        } else {

            Picasso.with(holder.imageView!!.context)
                    .load("http://d2c87l0yth4zbw-2.global.ssl.fastly.net/i/_global/open-graph-default.png")
                    .into(holder.imageView)
        }

        holder.itemView.setOnClickListener({
            if (itemClickListener != null) {
                itemClickListener!!.onItemClick(playlists!!, playlist, position)
            }
        })
    }

    override fun getItemCount(): Int {
        return playlists!!.size
    }

    fun setGenres(playlists: List<Playlist>) {
        this.playlists = playlists
    }

    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    interface ItemClickListener {
        fun onItemClick(playlits: List<Playlist>, playlist: Playlist, position: Int)
    }

    class PlaylistsViewHolder(internal var itemView1: View) : RecyclerView.ViewHolder(itemView1) {
        @JvmField
        @BindView(R.id.img_view_playlist_image)
        var imageView: ImageView? = null
        @JvmField
        @BindView(R.id.txt_playlist_name)
        var txt_playlist_name: TextView? = null

        init {
            ButterKnife.bind(this, itemView1)
        }
    }
}