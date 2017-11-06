package com.umusic.marcus.umusic.data

import com.umusic.marcus.umusic.data.Constants.Params.ARTIST_ID


object Constants {

    val SPOTIFY_API = "https://api.spotify.com"
    val API_KEY = "Authorization"

    var ACCESS_TOKEN =  "Bearer BQBjS-XNFOad67-e6e340K1ro92l3gvstZQUgDs2H_2lFvqwQzo3Xt4yCSkuKSvmpwgfuXXMekpWrhALFNRo1PicpGLTum8-RhFdYIlOBTplbmQObE-hndR7bcfW-coCgr_5Lq7a_nc"

    object Endpoint {

       const val ARTIST_SEARCH = "/v1/search?type=artist"
        const val ARTIST_TRACKS =
                "v1/artists/{$ARTIST_ID}/top-tracks?country=SE"
    }

    object Params {
      const   val QUERY_SEARCH = "q"
        const val ARTIST_ID = "artistId"
    }

    object Serialized {

        const val NAME = "name"
        const val IMAGES = "images"
        const val FOLLOWERS = "followers"
        const val HREF = "href"
        const val ID = "id"
        const val POPULARITY = "popularity"
        const val HEIGHT = "height"
        const val URL = "url"
        const val WIDTH = "width"
        const val TOTAL = "total"
        const val PREVIEW_URL = "preview_url"
        const val TRACK_NUMBER = "track_number"
        const val ALBUM = "album"


    }

    object Deserializer {

        val ARTISTS = "artists"
        val ITEMS = "items"
        val TRACKS = "tracks"
    }
}