package com.umusic.marcus.umusic.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class Icon : Parcelable {

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

    constructor()

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeValue(height)
        dest.writeValue(url)
        dest.writeValue(width)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        val CREATOR: Parcelable.Creator<Icon> = object : Parcelable.Creator<Icon> {


            override fun createFromParcel(`in`: Parcel): Icon {
                return Icon(`in`)
            }

            override fun newArray(size: Int): Array<Icon?> {
                return arrayOfNulls(size)
            }

        }
    }

}