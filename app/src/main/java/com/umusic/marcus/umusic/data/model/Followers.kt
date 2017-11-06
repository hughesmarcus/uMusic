package com.umusic.marcus.umusic.data.model

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Followers : Parcelable {

    @SerializedName("href")
    @Expose
    var href: String? = null
    @SerializedName("total")
    @Expose
    var total: Int? = null

    protected constructor(`in`: Parcel) {
        this.href = `in`.readValue(Any::class.java.classLoader) as? String
        this.total = `in`.readValue(Int::class.java.classLoader) as? Int
    }

    /**
     * No args constructor for use in serialization
     *
     */
    constructor() {}

    /**
     *
     * @param total
     * @param href
     */
    constructor(href: String?, total: Int?) : super() {
        this.href = href
        this.total = total
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeValue(href)
        dest.writeValue(total)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Followers> = object : Parcelable.Creator<Followers> {


            override fun createFromParcel(`in`: Parcel): Followers {
                return Followers(`in`)
            }

            override fun newArray(size: Int): Array<Followers?> {
                return arrayOfNulls(size)
            }

        }
    }

}
