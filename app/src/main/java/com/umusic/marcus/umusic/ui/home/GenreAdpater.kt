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
import com.umusic.marcus.umusic.data.model.Category
import java.util.*

/**
 * Created by Marcus on 12/13/2017.
 */
class GenreAdapter : RecyclerView.Adapter<GenreAdapter.GenreViewHolder>() {
    private var genres: List<Category>? = null
    private var itemClickListener: ItemClickListener? = null

    init {
        genres = Collections.emptyList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_genre, parent, false)
        return GenreViewHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        val genre = genres!![position]


        holder.txt_album_name!!.text = genre.name
        if (genre.icons!!.isNotEmpty()) {
            holder.imageView!!.scaleType = ImageView.ScaleType.FIT_XY
            for (i in 0 until genre.icons!!.size) {
                if (genre.icons!!.isNotEmpty()) {
                    Picasso.with(holder.imageView!!.context)
                            .load(genre.icons!![0].url)
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
                itemClickListener!!.onItemClick(genres!!, genre, position)
            }
        })
    }

    override fun getItemCount(): Int {
        return genres!!.size
    }

    fun setGenres(genres: List<Category>) {
        this.genres = genres
    }

    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    interface ItemClickListener {
        fun onItemClick(genres: List<Category>, genre: Category, position: Int)
    }

    class GenreViewHolder(internal var itemView1: View) : RecyclerView.ViewHolder(itemView1) {
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