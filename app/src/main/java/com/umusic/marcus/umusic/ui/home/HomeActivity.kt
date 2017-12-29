package com.umusic.marcus.umusic.ui.home

import android.os.Bundle
import com.umusic.marcus.umusic.BaseActivity
import com.umusic.marcus.umusic.R
import kotlinx.android.synthetic.main.activity_home.*


class HomeActivity : BaseActivity() {

    lateinit var homePresenter: HomePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setupToolbar()
        intNav()
        val tag = "home"
        when {
            null == supportFragmentManager.findFragmentByTag(tag) -> {
                val ft = supportFragmentManager.beginTransaction()
                ft.replace(R.id.fragment_container, HomeFragment.newInstance(), tag)
                ft.addToBackStack("home")
                ft.commit()
            }
        }
    }


    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        when {
            actionBar != null -> {
                actionBar.setDisplayUseLogoEnabled(false)
                // actionBar.setDisplayHomeAsUpEnabled(true)
                actionBar.setDisplayShowTitleEnabled(false)
            }
        }
    }

}
