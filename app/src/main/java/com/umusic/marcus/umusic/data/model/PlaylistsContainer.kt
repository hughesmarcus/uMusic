package com.umusic.marcus.umusic.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class PlaylistsContainer : Parcelable {

    @SerializedName("playlists")
    @Expose
    var playlists: Playlists? = null

    protected constructor(`in`: Parcel) {
        this.playlists = `in`.readValue(Playlists::class.java.classLoader) as Playlists
    }

    constructor()

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeValue(playlists)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        val CREATOR: Parcelable.Creator<PlaylistsContainer> = object : Parcelable.Creator<PlaylistsContainer> {


            override fun createFromParcel(`in`: Parcel): PlaylistsContainer {
                return PlaylistsContainer(`in`)
            }

            override fun newArray(size: Int): Array<PlaylistsContainer?> {
                return arrayOfNulls(size)
            }

        }
    }

}