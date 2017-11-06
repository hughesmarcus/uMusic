package com.umusic.marcus.umusic.data.model

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Artist : Parcelable {

    @SerializedName("external_urls")
    @Expose
    var externalUrls: ExternalUrls? = null
    @SerializedName("followers")
    @Expose
    var followers: Followers? = null
    @SerializedName("genres")
    @Expose
    var genres: List<String>? = ArrayList<String>()
    @SerializedName("href")
    @Expose
    var href: String? = null
    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("images")
    @Expose
    var images: List<Image>? = ArrayList<Image>()
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("popularity")
    @Expose
    var popularity: Int? = null
    @SerializedName("type")
    @Expose
    var type: String? = null
    @SerializedName("uri")
    @Expose
    var uri: String? = null

    protected constructor(`in`: Parcel) {
        this.externalUrls = `in`.readValue(ExternalUrls::class.java.classLoader) as? ExternalUrls
        this.followers = `in`.readValue(Followers::class.java.classLoader) as? Followers
        `in`.readList(this.genres, java.lang.String::class.java.classLoader)
        this.href = `in`.readValue(String::class.java.classLoader) as? String
        this.id = `in`.readValue(String::class.java.classLoader) as? String
        `in`.readList(this.images, com.umusic.marcus.umusic.data.model.Image::class.java.classLoader)
        this.name = `in`.readValue(String::class.java.classLoader) as? String
        this.popularity = `in`.readValue(Int::class.java.classLoader) as? Int
        this.type = `in`.readValue(String::class.java.classLoader) as? String
        this.uri = `in`.readValue(String::class.java.classLoader) as? String
    }

    /**
     * No args constructor for use in serialization
     *
     */
    constructor() {}

    /**
     *
     * @param id
     * @param followers
     * @param genres
     * @param externalUrls
     * @param name
     * @param images
     * @param type
     * @param uri
     * @param href
     * @param popularity
     */
    constructor(externalUrls: ExternalUrls, followers: Followers, genres: List<String>, href: String, id: String, images: List<Image>, name: String, popularity: Int?, type: String, uri: String) : super() {
        this.externalUrls = externalUrls
        this.followers = followers
        this.genres = genres
        this.href = href
        this.id = id
        this.images = images
        this.name = name
        this.popularity = popularity
        this.type = type
        this.uri = uri
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeValue(externalUrls)
        dest.writeValue(followers)
        dest.writeList(genres)
        dest.writeValue(href)
        dest.writeValue(id)
        dest.writeList(images)
        dest.writeValue(name)
        dest.writeValue(popularity)
        dest.writeValue(type)
        dest.writeValue(uri)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Artist> = object : Parcelable.Creator<Artist> {


            override fun createFromParcel(`in`: Parcel): Artist {
                return Artist(`in`)
            }

            override fun newArray(size: Int): Array<Artist?> {
                return arrayOfNulls(size)
            }

        }
    }

}
