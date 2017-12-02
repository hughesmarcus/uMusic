package com.umusic.marcus.umusic.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class Playlist : Parcelable {

    @SerializedName("collaborative")
    @Expose
    var collaborative: Boolean? = null
    @SerializedName("external_urls")
    @Expose
    var externalUrls: ExternalUrls? = null
    @SerializedName("href")
    @Expose
    var href: String? = null
    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("images")
    @Expose
    var images: List<Image>? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("owner")
    @Expose
    var owner: Owner? = null
    @SerializedName("public")
    @Expose
    var public: Any? = null
    @SerializedName("snapshot_id")
    @Expose
    var snapshotId: String? = null
    @SerializedName("type")
    @Expose
    var type: String? = null
    @SerializedName("uri")
    @Expose
    var uri: String? = null

    protected constructor(`in`: Parcel) {
        this.collaborative = `in`.readValue(Boolean::class.java.classLoader) as Boolean
        this.externalUrls = `in`.readValue(ExternalUrls::class.java.classLoader) as ExternalUrls
        this.href = `in`.readValue(String::class.java.classLoader) as String
        this.id = `in`.readValue(String::class.java.classLoader) as String
        `in`.readList(this.images, com.umusic.marcus.umusic.data.model.Image::class.java.classLoader)
        this.name = `in`.readValue(String::class.java.classLoader) as String
        this.owner = `in`.readValue(Owner::class.java.classLoader) as Owner
        this.public = `in`.readValue(Any::class.java.classLoader) as Any
        this.snapshotId = `in`.readValue(String::class.java.classLoader) as String
        this.type = `in`.readValue(String::class.java.classLoader) as String
        this.uri = `in`.readValue(String::class.java.classLoader) as String
    }

    constructor()

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeValue(collaborative)
        dest.writeValue(externalUrls)
        dest.writeValue(href)
        dest.writeValue(id)
        dest.writeList(images)
        dest.writeValue(name)
        dest.writeValue(owner)
        dest.writeValue(public)
        dest.writeValue(snapshotId)
        dest.writeValue(type)
        dest.writeValue(uri)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        val CREATOR: Parcelable.Creator<Playlist> = object : Parcelable.Creator<Playlist> {


            override fun createFromParcel(`in`: Parcel): Playlist {
                return Playlist(`in`)
            }

            override fun newArray(size: Int): Array<Playlist?> {
                return arrayOfNulls(size)
            }

        }
    }

}