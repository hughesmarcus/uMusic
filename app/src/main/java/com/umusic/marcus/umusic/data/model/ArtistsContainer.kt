package com.umusic.marcus.umusic.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ArtistsContainer : Parcelable {

    @SerializedName("artists")
    @Expose
    var artists: Artists? = null

    protected constructor(`in`: Parcel) {
        this.artists = `in`.readValue(Artists::class.java.classLoader) as Artists
    }

    /**
     * No args constructor for use in serialization
     *
     */
    constructor()

    /**
     *
     * @param artists
     */
    constructor(artists: Artists) : super() {
        this.artists = artists
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeValue(artists)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<ArtistsContainer> = object : Parcelable.Creator<ArtistsContainer> {


            override fun createFromParcel(`in`: Parcel): ArtistsContainer {
                return ArtistsContainer(`in`)
            }

            override fun newArray(size: Int): Array<ArtistsContainer?> {
                return arrayOfNulls(size)
            }

        }
    }

}
