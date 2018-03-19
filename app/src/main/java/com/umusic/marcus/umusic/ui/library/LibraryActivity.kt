package com.umusic.marcus.umusic.ui.library

import android.os.Bundle
import com.umusic.marcus.umusic.R
import com.umusic.marcus.umusic.ui.BaseActivity

class LibraryActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_library)
        intNav()
    }
}
