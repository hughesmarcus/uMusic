package com.umusic.marcus.umusic.ui.tracks

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.umusic.marcus.umusic.R
import com.umusic.marcus.umusic.data.model.Item
import java.util.*

/**
 * Created by Marcus on 12/18/2017.
 */
class TracksAdapter : RecyclerView.Adapter<TracksAdapter.TracksViewHolder>() {
    private var tracks: List<Item>? = null
    private var itemClickListener: ItemClickListener? = null

    init {
        tracks = Collections.emptyList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TracksViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_track2, parent, false)
        return TracksViewHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: TracksViewHolder, position: Int) {
        val track = tracks!![position]


        holder.txt_track_name!!.text = track.track!!.name
        holder.txt_number!!.text = position.toString()
        holder.txt_artist_name!!.text = track.track!!.artists!![0].name

        holder.itemView.setOnClickListener({
            if (itemClickListener != null) {
                itemClickListener!!.onItemClick(tracks!!, track, position)
            }
        })
    }

    override fun getItemCount(): Int {
        return tracks!!.size
    }

    fun setTracks(tracks: List<Item>) {
        this.tracks = tracks
    }

    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    interface ItemClickListener {
        fun onItemClick(tracks: List<Item>, track: Item, position: Int)
    }

    class TracksViewHolder(internal var itemView1: View) : RecyclerView.ViewHolder(itemView1) {
        @JvmField
        @BindView(R.id.track_title)
        var txt_track_name: TextView? = null
        @JvmField
        @BindView(R.id.list_number)
        var txt_number: TextView? = null
        @JvmField
        @BindView(R.id.authur_name)
        var txt_artist_name: TextView? = null

        init {
            ButterKnife.bind(this, itemView1)
        }
    }
}