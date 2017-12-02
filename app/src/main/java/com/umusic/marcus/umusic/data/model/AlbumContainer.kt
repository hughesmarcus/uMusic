package com.umusic.marcus.umusic.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class AlbumContainer : Parcelable {

    @SerializedName("artist")
    @Expose
    var albums: List<Album>? = ArrayList()

    protected constructor(`in`: Parcel) {
        `in`.readList(this.albums, com.umusic.marcus.umusic.data.model.Album::class.java.classLoader)
    }

    /**
     * No args constructor for use in serialization
     *
     */
    constructor()

    /**
     *
     * @param tracks
     */
    constructor(tracks: List<Album>) : super() {
        this.albums = albums
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeList(albums)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<AlbumContainer> = object : Parcelable.Creator<AlbumContainer> {


            override fun createFromParcel(`in`: Parcel): AlbumContainer {
                return AlbumContainer(`in`)
            }

            override fun newArray(size: Int): Array<AlbumContainer?> {
                return arrayOfNulls(size)
            }

        }
    }

}