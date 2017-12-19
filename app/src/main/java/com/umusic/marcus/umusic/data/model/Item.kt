package com.umusic.marcus.umusic.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class Item : Parcelable {

    @SerializedName("added_at")
    @Expose
    var addedAt: String? = null
    @SerializedName("added_by")
    @Expose
    var addedBy: Any? = null
    @SerializedName("is_local")
    @Expose
    var isLocal: Boolean? = null
    @SerializedName("track")
    @Expose
    var track: Track? = null

    protected constructor(`in`: Parcel) {
        this.addedAt = `in`.readValue(String::class.java.classLoader) as String
        this.addedBy = `in`.readValue(Any::class.java.classLoader) as Any
        this.isLocal = `in`.readValue(Boolean::class.java.classLoader) as Boolean
        this.track = `in`.readValue(Track::class.java.classLoader) as Track
    }

    constructor()

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeValue(addedAt)
        dest.writeValue(addedBy)
        dest.writeValue(isLocal)
        dest.writeValue(track)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        val CREATOR: Parcelable.Creator<Item> = object : Parcelable.Creator<Item> {


            override fun createFromParcel(`in`: Parcel): Item {
                return Item(`in`)
            }

            override fun newArray(size: Int): Array<Item?> {
                return arrayOfNulls(size)
            }

        }
    }

}