package com.umusic.marcus.umusic.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import com.umusic.marcus.umusic.R
import com.umusic.marcus.umusic.ui.home.HomeActivity
import com.umusic.marcus.umusic.ui.library.LibraryActivity
import com.umusic.marcus.umusic.ui.search.SearchActivity


@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity() {


    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.action_home -> {

                val intent = Intent(this, HomeActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                return@OnNavigationItemSelectedListener true
            }
            R.id.action_library -> {
                val intent = Intent(this, LibraryActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                return@OnNavigationItemSelectedListener true
            }
            R.id.action_search -> {
                val intent = Intent(this, SearchActivity::class.java)
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