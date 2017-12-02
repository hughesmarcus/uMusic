package com.umusic.marcus.umusic.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class Category : Parcelable {

    @SerializedName("href")
    @Expose
    var href: String? = null
    @SerializedName("icons")
    @Expose
    var icons: List<Icon>? = null
    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("name")
    @Expose
    var name: String? = null

    protected constructor(`in`: Parcel) {
        this.href = `in`.readValue(String::class.java.classLoader) as String
        `in`.readList(this.icons, com.umusic.marcus.umusic.data.model.Icon::class.java.classLoader)
        this.id = `in`.readValue(String::class.java.classLoader) as String
        this.name = `in`.readValue(String::class.java.classLoader) as String
    }

    constructor()

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeValue(href)
        dest.writeList(icons)
        dest.writeValue(id)
        dest.writeValue(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        val CREATOR: Parcelable.Creator<Category> = object : Parcelable.Creator<Category> {


            override fun createFromParcel(`in`: Parcel): Category {
                return Category(`in`)
            }

            override fun newArray(size: Int): Array<Category?> {
                return arrayOfNulls(size)
            }

        }
    }

}