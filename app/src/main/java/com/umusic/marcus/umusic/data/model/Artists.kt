package com.umusic.marcus.umusic.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Artists : Parcelable {

    @SerializedName("href")
    @Expose
    var href: String? = null
    @SerializedName("items")
    @Expose
    var items: List<Artist>? = ArrayList<Artist>()
    @SerializedName("limit")
    @Expose
    var limit: Int? = null
    @SerializedName("next")
    @Expose
    var next: String? = null
    @SerializedName("offset")
    @Expose
    var offset: Int? = null
    @SerializedName("previous")
    @Expose
    var previous: Any? = null
    @SerializedName("total")
    @Expose
    var total: Int? = null

    protected constructor(`in`: Parcel) {
        this.href = `in`.readValue(String::class.java.classLoader) as String
        `in`.readList(this.items, com.umusic.marcus.umusic.data.model.Artist::class.java.classLoader)
        this.limit = `in`.readValue(Int::class.java.classLoader) as Int
        this.next = `in`.readValue(String::class.java.classLoader) as String
        this.offset = `in`.readValue(Int::class.java.classLoader) as Int
        this.previous = `in`.readValue(Any::class.java.classLoader) as Any
        this.total = `in`.readValue(Int::class.java.classLoader) as Int
    }

    /**
     * No args constructor for use in serialization
     *
     */
    constructor()

    /**
     *
     * @param total
     * @param limit
     * @param previous
     * @param items
     * @param next
     * @param offset
     * @param href
     */
    constructor(href: String, items: List<Artist>, limit: Int?, next: String, offset: Int?, previous: Any, total: Int?) : super() {
        this.href = href
        this.items = items
        this.limit = limit
        this.next = next
        this.offset = offset
        this.previous = previous
        this.total = total
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeValue(href)
        dest.writeList(items)
        dest.writeValue(limit)
        dest.writeValue(next)
        dest.writeValue(offset)
        dest.writeValue(previous)
        dest.writeValue(total)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Artists> = object : Parcelable.Creator<Artists> {


            override fun createFromParcel(`in`: Parcel): Artists {
                return Artists(`in`)
            }

            override fun newArray(size: Int): Array<Artists?> {
                return arrayOfNulls(size)
            }

        }
    }

}
