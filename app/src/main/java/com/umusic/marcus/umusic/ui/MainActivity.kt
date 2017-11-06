package com.umusic.marcus.umusic.ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.spotify.sdk.android.authentication.AuthenticationClient
import com.spotify.sdk.android.authentication.AuthenticationRequest
import com.spotify.sdk.android.authentication.AuthenticationResponse
import com.umusic.marcus.umusic.R
import com.umusic.marcus.umusic.data.Constants
import com.umusic.marcus.umusic.ui.artist.ArtistActivity

class MainActivity : AppCompatActivity() {
    // TODO: Replace with your client ID
    private val CLIENT_ID = "057c8004c8df456e8dcf30bf9e2233e2"
    // TODO: Replace with your redirect URI
    private val REDIRECT_URI = "spotifytestapp://callback"

    // Request code that will be used to verify if the result comes from correct activity
    // Can be any integer
    private val REQUEST_CODE = 1337
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val builder = AuthenticationRequest.Builder(CLIENT_ID,
                AuthenticationResponse.Type.TOKEN,
                REDIRECT_URI)
        builder.setScopes(arrayOf("user-read-private", "streaming"))
        val request = builder.build()

        AuthenticationClient.openLoginActivity(this, REQUEST_CODE, request)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent) {
        super.onActivityResult(requestCode, resultCode, intent)

        // Check if result comes from the correct activity
        if (requestCode == REQUEST_CODE) {
            val response = AuthenticationClient.getResponse(resultCode, intent)
            if (response.type == AuthenticationResponse.Type.TOKEN) {
                Constants.ACCESS_TOKEN = "Bearer " + response.accessToken
                val intent = Intent(this, ArtistActivity::class.java)
                startActivity(intent)
            }

        }
    }
}
