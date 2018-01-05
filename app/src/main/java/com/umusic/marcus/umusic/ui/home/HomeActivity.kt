package com.umusic.marcus.umusic.ui.home

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.support.v4.content.LocalBroadcastManager
import android.util.Log
import android.view.View
import butterknife.ButterKnife
import com.umusic.marcus.umusic.BaseActivity
import com.umusic.marcus.umusic.R
import kotlinx.android.synthetic.main.activity_home.*


class HomeActivity : BaseActivity() {

    lateinit var homePresenter: HomePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        ButterKnife.bind(this)
        LocalBroadcastManager.getInstance(this).registerReceiver(messageReceiver, IntentFilter("send"))
        //  setupToolbar()
        intNav()
        val tag = "home"
        when {
            null == supportFragmentManager.findFragmentByTag(tag) -> {
                val ft = supportFragmentManager.beginTransaction()
                ft.replace(R.id.fragment_container, HomeFragment.newInstance(), tag)
                ft.commit()
            }
        }
    }

    override fun onDestroy() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(messageReceiver)
        super.onDestroy()
    }

    //  private fun setupToolbar() {
    //     setSupportActionBar(toolbar)
    //     val actionBar = supportActionBar
    //     when {
    // //       actionBar != null -> {
    //           actionBar.setDisplayUseLogoEnabled(false)
    //         actionBar.setDisplayShowTitleEnabled(false)
    //     }
    //  }
    // }
    /**
     * show or not show player depending on Service
     */
    private val messageReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            // Get extra data included in the Intent
            val message = intent.getStringExtra("message")

            if (message == "started") {
                Log.d("HOMEAct", message)

                //  fragment_mini_player_container.visibility = View.VISIBLE
            }
            if (message == "stopped") {
                Log.d("HOME", message)
                fragment_mini_player_container.visibility = View.GONE
            }


        }
    }



}
