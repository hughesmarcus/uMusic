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
import com.umusic.marcus.umusic.data.model.Album
import java.util.*

/**
 * Created by Marcus on 12/4/2017.
 */
class ReleasesAdapter : RecyclerView.Adapter<ReleasesAdapter.AlbumsViewHolder>() {
    private var albums: List<Album>? = null
    private var itemClickListener: ItemClickListener? = null

    init {
        albums = Collections.emptyList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_new_release, parent, false)
        return AlbumsViewHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: AlbumsViewHolder, position: Int) {
        val album = albums!![position]


        holder.txt_album_name!!.text = album.name
        if (album.images!!.isNotEmpty()) {
            holder.imageView!!.scaleType = ImageView.ScaleType.FIT_XY
            for (i in 0 until album.images!!.size) {
                if (album.images!!.isNotEmpty()) {
                    Picasso.with(holder.imageView!!.context)
                            .load(album.images!![0].url)
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
                itemClickListener!!.onItemClick(albums!!, album, position)
            }
        })
    }

    override fun getItemCount(): Int {
        return albums!!.size
    }

    fun setTracks(albums: List<Album>) {
        this.albums = albums
    }

    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    interface ItemClickListener {
        fun onItemClick(albums: List<Album>, album: Album, position: Int)
    }

    class AlbumsViewHolder(internal var itemView1: View) : RecyclerView.ViewHolder(itemView1) {
        @JvmField
        @BindView(R.id.img_view_artist_image)
        var imageView: ImageView? = null
        @JvmField
        @BindView(R.id.txt_album_name)
        var txt_album_name: TextView? = null

        init {
            ButterKnife.bind(this, itemView1)
        }
    }
}