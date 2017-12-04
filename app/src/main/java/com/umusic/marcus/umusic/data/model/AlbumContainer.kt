package com.umusic.marcus.umusic.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class AlbumContainer : Parcelable {

    @SerializedName("albums")
    @Expose
    var albums: Albums? = null

    protected constructor(`in`: Parcel) {
        this.albums = `in`.readValue(Albums::class.java.classLoader) as Albums
    }

    constructor()

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeValue(albums)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
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