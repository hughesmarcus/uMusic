package com.umusic.marcus.umusic.data

import com.umusic.marcus.umusic.data.Constants.Params.ARTIST_ID
import com.umusic.marcus.umusic.data.Constants.Params.CATEGORY_ID
import com.umusic.marcus.umusic.data.Constants.Params.PLAYLIST_ID
import com.umusic.marcus.umusic.data.Constants.Params.TRACK_ID
import com.umusic.marcus.umusic.data.Constants.Params.USER_ID


object Constants {

    val SPOTIFY_API = "https://api.spotify.com"
    val API_KEY = "Authorization"

    var ACCESS_TOKEN = "Bearer BQBjS-XNFOad67-e6e340K1ro92l3gvstZQUgDs2H_2lFvqwQzo3Xt4yCSkuKSvmpwgfuXXMekpWrhALFNRo1PicpGLTum8-RhFdYIlOBTplbmQObE-hndR7bcfW-coCgr_5Lq7a_nc"

    object Endpoint {

        const val ARTIST_SEARCH = "/v1/search?type=artist"

        const val ARTIST_TRACKS =
                "v1/artists/{$ARTIST_ID}/top-tracks?country=US"

        const val ARTIST_ALBUMS = "/v1/artists/{$ARTIST_ID}/albums"

        const val ARTIST_RELATED = "/v1/artists/{$ARTIST_ID}/related-artists"

        const val TRACK = "/v1/tracks/{$TRACK_ID}"

        const val TRACK_SEARCH = "/v1/search?type=track"

        const val ALBUM_SEARCH = "/v1/search?type=album"

        const val NEW_RELEASES = "/v1/browse/new-releases"

        const val CATEGORIES_BROWSE = "/v1/browse/categories"

        const val CATEGORY_PLAYLISTS = "/v1/browse/categories/{$CATEGORY_ID}/playlists"

        const val PLAYLIST_TRACKS = "/v1/users/{$USER_ID}/playlists/{$PLAYLIST_ID}/tracks"

    }

    object Params {
        const val QUERY_SEARCH = "q"
        const val ARTIST_ID = "artistId"
        const val TRACK_ID = "trackId"
        const val CATEGORY_ID = "categoryId"
        const val USER_ID = "userId"
        const val PLAYLIST_ID = "playlistId"
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
        val ITEMS = "categories"
        val TRACKS = "artist"
    }
}