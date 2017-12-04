package com.umusic.marcus.umusic.ui.artists

import android.os.Bundle
import com.umusic.marcus.umusic.BaseActivity
import com.umusic.marcus.umusic.R


class ArtistsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_artists)
        intNav()
    }


}
