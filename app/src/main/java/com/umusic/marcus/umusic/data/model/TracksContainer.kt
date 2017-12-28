package com.umusic.marcus.umusic.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class TracksContainer : Parcelable {

    @SerializedName("tracks")
    @Expose
    var tracks: List<Track>? = ArrayList()
    @SerializedName("items")
    @Expose
    var albumTracks: List<Track>? = ArrayList()

    protected constructor(`in`: Parcel) {
        `in`.readList(this.tracks, com.umusic.marcus.umusic.data.model.Track::class.java.classLoader)
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
    constructor(tracks: List<Track>) : super() {
        this.tracks = tracks
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeList(tracks)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<TracksContainer> = object : Parcelable.Creator<TracksContainer> {


            override fun createFromParcel(`in`: Parcel): TracksContainer {
                return TracksContainer(`in`)
            }

            override fun newArray(size: Int): Array<TracksContainer?> {
                return arrayOfNulls(size)
            }

        }
    }

}
