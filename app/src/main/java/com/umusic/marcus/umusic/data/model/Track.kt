package com.umusic.marcus.umusic.data.model

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Track : Parcelable {

    @SerializedName("album")
    @Expose
    var album: Album? = null
    @SerializedName("artists")
    @Expose
    var artists: List<Artist>? = ArrayList<Artist>()
    @SerializedName("available_markets")
    @Expose
    var availableMarkets: List<String>? = ArrayList<String>()
    @SerializedName("disc_number")
    @Expose
    var discNumber: Int? = null
    @SerializedName("duration_ms")
    @Expose
    var durationMs: Int? = null
    @SerializedName("explicit")
    @Expose
    var explicit: Boolean? = null
    @SerializedName("href")
    @Expose
    var href: String? = null
    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("popularity")
    @Expose
    var popularity: Int? = null
    @SerializedName("preview_url")
    @Expose
    var previewUrl: String? = null
    @SerializedName("track_number")
    @Expose
    var trackNumber: Int? = null
    @SerializedName("type")
    @Expose
    var type: String? = null
    @SerializedName("uri")
    @Expose
    var uri: String? = null

    protected constructor(`in`: Parcel) {
        this.album = `in`.readValue(Album::class.java.classLoader) as Album
        `in`.readList(this.artists, com.umusic.marcus.umusic.data.model.Artist::class.java.classLoader)
        `in`.readList(this.availableMarkets, java.lang.String::class.java.classLoader)
        this.discNumber = `in`.readValue(Int::class.java.classLoader) as Int
        this.durationMs = `in`.readValue(Int::class.java.classLoader) as Int
        this.explicit = `in`.readValue(Boolean::class.java.classLoader) as Boolean
        this.href = `in`.readValue(String::class.java.classLoader) as String
        this.id = `in`.readValue(String::class.java.classLoader) as String
        this.name = `in`.readValue(String::class.java.classLoader) as String
        this.popularity = `in`.readValue(Int::class.java.classLoader) as Int
        this.previewUrl = `in`.readValue(String::class.java.classLoader) as String
        this.trackNumber = `in`.readValue(Int::class.java.classLoader) as Int
        this.type = `in`.readValue(String::class.java.classLoader) as String
        this.uri = `in`.readValue(String::class.java.classLoader) as String
    }

    /**
     * No args constructor for use in serialization
     *
     */
    constructor() {}

    /**
     *
     * @param externalIds
     * @param album
     * @param trackNumber
     * @param type
     * @param uri
     * @param discNumber
     * @param previewUrl
     * @param id
     * @param artists
     * @param durationMs
     * @param explicit
     * @param externalUrls
     * @param name
     * @param availableMarkets
     * @param href
     * @param popularity
     */
    constructor(album: Album, artists: List<Artist>, availableMarkets: List<String>, discNumber: Int?, durationMs: Int?, explicit: Boolean?, href: String, id: String, name: String, popularity: Int?, previewUrl: String, trackNumber: Int?, type: String, uri: String) : super() {
        this.album = album
        this.artists = artists
        this.availableMarkets = availableMarkets
        this.discNumber = discNumber
        this.durationMs = durationMs
        this.explicit = explicit
        this.href = href
        this.id = id
        this.name = name
        this.popularity = popularity
        this.previewUrl = previewUrl
        this.trackNumber = trackNumber
        this.type = type
        this.uri = uri
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeValue(album)
        dest.writeList(artists)
        dest.writeList(availableMarkets)
        dest.writeValue(discNumber)
        dest.writeValue(durationMs)
        dest.writeValue(explicit)
        dest.writeValue(href)
        dest.writeValue(id)
        dest.writeValue(name)
        dest.writeValue(popularity)
        dest.writeValue(previewUrl)
        dest.writeValue(trackNumber)
        dest.writeValue(type)
        dest.writeValue(uri)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Track> = object : Parcelable.Creator<Track> {


            override fun createFromParcel(`in`: Parcel): Track {
                return Track(`in`)
            }

            override fun newArray(size: Int): Array<Track?> {
                return arrayOfNulls(size)
            }

        }
    }

}
