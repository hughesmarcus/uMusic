package com.umusic.marcus.umusic.ui.artist

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
import com.umusic.marcus.umusic.data.model.Track
import java.util.*


class ArtistAdapter : RecyclerView.Adapter<ArtistAdapter.TracksViewHolder>() {

    private var tracks: List<Track>? = null
    private var itemClickListener: ItemClickListener? = null

    init {
        tracks = Collections.emptyList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TracksViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_track, parent, false)
        return TracksViewHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: TracksViewHolder, position: Int) {
        val track = tracks!![position]

        holder.txt_title_tracks!!.text = (position + 1).toString() + "." + track.name
        holder.txt_track_album!!.text = track.album!!.name

        if (track.album!!.images!!.isNotEmpty()) {
            holder.imageView!!.scaleType = ImageView.ScaleType.FIT_XY
            for (i in 0 until track.album!!.images!!.size) {
                if (track.album!!.images!!.isNotEmpty()) {
                    Picasso.with(holder.imageView!!.context)
                            .load(track.album!!.images!!.get(0).url)
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
                itemClickListener!!.onItemClick(tracks!!, track, position)
            }
        })
    }

    override fun getItemCount(): Int {
        return tracks!!.size
    }

    fun setTracks(tracks: List<Track>) {
        this.tracks = tracks
    }

    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    interface ItemClickListener {
        fun onItemClick(tracks: List<Track>, track: Track, position: Int)
    }

    class TracksViewHolder(internal var itemView1: View) : RecyclerView.ViewHolder(itemView1) {
        @JvmField
        @BindView(R.id.iv_track)  var imageView: ImageView? = null
        @JvmField
        @BindView(R.id.txt_track_title) var txt_title_tracks: TextView? = null
        @JvmField
        @BindView(R.id.txt_track_album)  var txt_track_album: TextView? = null

        init {
            ButterKnife.bind(this, itemView1)
        }
    }
}