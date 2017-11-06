package com.umusic.marcus.umusic.ui.artist

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.umusic.marcus.umusic.R
import com.spotify.sdk.android.authentication.LoginActivity.REQUEST_CODE
import com.spotify.sdk.android.authentication.AuthenticationClient
import com.spotify.sdk.android.authentication.AuthenticationRequest
import com.spotify.sdk.android.authentication.AuthenticationResponse
import com.spotify.sdk.android.authentication.LoginActivity.REQUEST_CODE
import android.content.Intent
import android.util.Log
import com.umusic.marcus.umusic.data.Constants


class ArtistActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_artists)
    }


}
