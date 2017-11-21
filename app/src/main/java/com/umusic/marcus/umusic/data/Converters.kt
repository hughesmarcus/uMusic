package com.umusic.marcus.umusic.data

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.umusic.marcus.umusic.data.model.Image


/**
 * Created by Marcus on 11/21/2017.
 */
object Converters {
    @TypeConverter
    fun fromTimestamp(value: String): ArrayList<Image> {
        val listType = object : TypeToken<ArrayList<Image>>() {

        }.type
        return Gson().fromJson(value, listType)

    }

    @TypeConverter
    fun arraylistToString(list: ArrayList<Image>): String {
        val gson = Gson()

        return gson.toJson(list)

    }
}