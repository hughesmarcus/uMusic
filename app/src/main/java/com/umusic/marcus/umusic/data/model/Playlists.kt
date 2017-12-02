package com.umusic.marcus.umusic.data.model

import android.content.ClipData.Item
import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class Playlists : Parcelable {

    @SerializedName("href")
    @Expose
    var href: String? = null
    @SerializedName("items")
    @Expose
    var items: List<Item>? = null
    @SerializedName("limit")
    @Expose
    var limit: Int? = null
    @SerializedName("next")
    @Expose
    var next: String? = null
    @SerializedName("offset")
    @Expose
    var offset: Int? = null
    @SerializedName("previous")
    @Expose
    var previous: String? = null
    @SerializedName("total")
    @Expose
    var total: Int? = null

    protected constructor(`in`: Parcel) {
        this.href = `in`.readValue(String::class.java.classLoader) as String
        `in`.readList(this.items, com.umusic.marcus.umusic.data.model.Playlist::class.java.classLoader)
        this.limit = `in`.readValue(Int::class.java.classLoader) as Int
        this.next = `in`.readValue(String::class.java.classLoader) as String
        this.offset = `in`.readValue(Int::class.java.classLoader) as Int
        this.previous = `in`.readValue(String::class.java.classLoader) as String
        this.total = `in`.readValue(Int::class.java.classLoader) as Int
    }

    constructor()

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeValue(href)
        dest.writeList(items)
        dest.writeValue(limit)
        dest.writeValue(next)
        dest.writeValue(offset)
        dest.writeValue(previous)
        dest.writeValue(total)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        val CREATOR: Parcelable.Creator<Playlists> = object : Parcelable.Creator<Playlists> {


            override fun createFromParcel(`in`: Parcel): Playlists {
                return Playlists(`in`)
            }

            override fun newArray(size: Int): Array<Playlists?> {
                return arrayOfNulls(size)
            }

        }
    }

}