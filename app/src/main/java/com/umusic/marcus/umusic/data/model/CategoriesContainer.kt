package com.umusic.marcus.umusic.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class CategoriesContainer : Parcelable {

    @SerializedName("categories")
    @Expose
    var categories: Categories? = null

    protected constructor(`in`: Parcel) {
        this.categories = `in`.readValue(Categories::class.java.classLoader) as Categories
    }

    constructor()

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeValue(categories)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        val CREATOR: Parcelable.Creator<CategoriesContainer> = object : Parcelable.Creator<CategoriesContainer> {


            override fun createFromParcel(`in`: Parcel): CategoriesContainer {
                return CategoriesContainer(`in`)
            }

            override fun newArray(size: Int): Array<CategoriesContainer?> {
                return arrayOfNulls(size)
            }

        }
    }

}