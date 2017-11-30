package com.umusic.marcus.umusic.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Album : Parcelable {

    @SerializedName("album_type")
    @Expose
    var albumType: String? = null
    @SerializedName("artists")
    @Expose
    var artists: List<Artist>? = ArrayList<Artist>()
    @SerializedName("available_markets")
    @Expose
    var availableMarkets: List<String>? = ArrayList<String>()
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
    @SerializedName("type")
    @Expose
    var type: String? = null
    @SerializedName("uri")
    @Expose
    var uri: String? = null


    protected constructor(`in`: Parcel) {
        this.albumType = `in`.readValue(String::class.java.classLoader) as String
        `in`.readList(this.artists, com.umusic.marcus.umusic.data.model.Artist::class.java.classLoader)
        `in`.readList(this.availableMarkets, java.lang.String::class.java.classLoader)
        this.href = `in`.readValue(String::class.java.classLoader) as String
        this.id = `in`.readValue(String::class.java.classLoader) as String
        `in`.readList(this.images, com.umusic.marcus.umusic.data.model.Image::class.java.classLoader)
        this.name = `in`.readValue(String::class.java.classLoader) as String
        this.type = `in`.readValue(String::class.java.classLoader) as String
        this.uri = `in`.readValue(String::class.java.classLoader) as String
    }

    /**
     * No args constructor for use in serialization
     *
     */
    constructor()

    /**
     *
     * @param id
     * @param artists
     * @param externalUrls
     * @param albumType
     * @param name
     * @param availableMarkets
     * @param images
     * @param type
     * @param uri
     * @param href
     */
    constructor(albumType: String, artists: List<Artist>, availableMarkets: List<String>, href: String, id: String, images: List<Image>, name: String, type: String, uri: String) : super() {
        this.albumType = albumType
        this.artists = artists
        this.availableMarkets = availableMarkets
        this.href = href
        this.id = id
        this.images = images
        this.name = name
        this.type = type
        this.uri = uri
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeValue(albumType)
        dest.writeList(artists)
        dest.writeList(availableMarkets)
        dest.writeValue(href)
        dest.writeValue(id)
        dest.writeList(images)
        dest.writeValue(name)
        dest.writeValue(type)
        dest.writeValue(uri)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Album> = object : Parcelable.Creator<Album> {


            override fun createFromParcel(`in`: Parcel): Album {
                return Album(`in`)
            }

            override fun newArray(size: Int): Array<Album?> {
                return arrayOfNulls(size)
            }

        }
    }

}
