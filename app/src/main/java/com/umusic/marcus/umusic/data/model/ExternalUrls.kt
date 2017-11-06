package com.umusic.marcus.umusic.data.model

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ExternalUrls : Parcelable {

    @SerializedName("spotify")
    @Expose
    var spotify: String? = null

    protected constructor(`in`: Parcel) {
        this.spotify = `in`.readValue(String::class.java.classLoader) as String
    }

    /**
     * No args constructor for use in serialization
     *
     */
    constructor() {}

    /**
     *
     * @param spotify
     */
    constructor(spotify: String) : super() {
        this.spotify = spotify
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeValue(spotify)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<ExternalUrls> = object : Parcelable.Creator<ExternalUrls> {


            override fun createFromParcel(`in`: Parcel): ExternalUrls {
                return ExternalUrls(`in`)
            }

            override fun newArray(size: Int): Array<ExternalUrls?> {
                return arrayOfNulls(size)
            }

        }
    }

}
