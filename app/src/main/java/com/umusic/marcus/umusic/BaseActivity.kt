package com.umusic.marcus.umusic

import android.content.Intent
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import com.umusic.marcus.umusic.ui.artists.ArtistsActivity
import com.umusic.marcus.umusic.ui.home.HomeActivity

/**
 * Created by Marcus on 12/4/2017.
 */
open class BaseActivity : AppCompatActivity() {


    internal val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.action_home -> {

                val intent = Intent(this, HomeActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                return@OnNavigationItemSelectedListener true
            }
            R.id.action_library -> {
            }
            R.id.action_search -> {
                val intent = Intent(this, ArtistsActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    fun intNav() {
        val navigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        navigation!!.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }


}