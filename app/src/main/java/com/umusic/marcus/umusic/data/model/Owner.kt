package com.umusic.marcus.umusic.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class Owner : Parcelable {

    @SerializedName("display_name")
    @Expose
    var displayName: String? = null
    @SerializedName("external_urls")
    @Expose
    var externalUrls: ExternalUrls? = null
    @SerializedName("href")
    @Expose
    var href: String? = null
    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("type")
    @Expose
    var type: String? = null
    @SerializedName("uri")
    @Expose
    var uri: String? = null

    protected constructor(`in`: Parcel) {
        this.displayName = `in`.readValue(String::class.java.classLoader) as String
        this.externalUrls = `in`.readValue(ExternalUrls::class.java.classLoader) as ExternalUrls
        this.href = `in`.readValue(String::class.java.classLoader) as String
        this.id = `in`.readValue(String::class.java.classLoader) as String
        this.type = `in`.readValue(String::class.java.classLoader) as String
        this.uri = `in`.readValue(String::class.java.classLoader) as String
    }

    constructor()

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeValue(displayName)
        dest.writeValue(externalUrls)
        dest.writeValue(href)
        dest.writeValue(id)
        dest.writeValue(type)
        dest.writeValue(uri)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        val CREATOR: Parcelable.Creator<Owner> = object : Parcelable.Creator<Owner> {


            override fun createFromParcel(`in`: Parcel): Owner {
                return Owner(`in`)
            }

            override fun newArray(size: Int): Array<Owner?> {
                return arrayOfNulls(size)
            }

        }
    }

}