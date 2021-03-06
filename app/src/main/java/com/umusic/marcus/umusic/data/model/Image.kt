package com.umusic.marcus.umusic.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Image : Parcelable {

    @SerializedName("height")
    @Expose
    var height: Int? = null
    @SerializedName("url")
    @Expose
    var url: String? = null
    @SerializedName("width")
    @Expose
    var width: Int? = null

    protected constructor(`in`: Parcel) {
        this.height = `in`.readValue(Int::class.java.classLoader) as Int
        this.url = `in`.readValue(String::class.java.classLoader) as String
        this.width = `in`.readValue(Int::class.java.classLoader) as Int
    }

    /**
     * No args constructor for use in serialization
     *
     */
    constructor()

    /**
     *
     * @param height
     * @param width
     * @param url
     */
    constructor(height: Int?, url: String, width: Int?) : super() {
        this.height = height
        this.url = url
        this.width = width
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeValue(height)
        dest.writeValue(url)
        dest.writeValue(width)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Image> = object : Parcelable.Creator<Image> {


            override fun createFromParcel(`in`: Parcel): Image {
                return Image(`in`)
            }

            override fun newArray(size: Int): Array<Image?> {
                return arrayOfNulls(size)
            }

        }
    }

}
