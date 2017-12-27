package com.umusic.marcus.umusic.ui.utils

import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.umusic.marcus.umusic.data.model.Track

/**
 * Created by marcus on 12/27/17.
 */
object TracksUtil {
     fun setTracks(tracks: List<Track>): String {
        val gson = GsonBuilder().create()
        val trackType = object : TypeToken<List<Track>>() {

        }.type
        return gson.toJson(tracks, trackType)
    }
}