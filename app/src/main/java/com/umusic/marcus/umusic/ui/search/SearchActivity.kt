package com.umusic.marcus.umusic.ui.search

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.support.v4.content.LocalBroadcastManager
import android.util.Log
import android.view.View
import butterknife.OnClick
import com.umusic.marcus.umusic.BaseActivity
import com.umusic.marcus.umusic.R
import kotlinx.android.synthetic.main.activity_home.*

class SearchActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        intNav()
        LocalBroadcastManager.getInstance(this).registerReceiver(messageReceiver, IntentFilter("send"))
        val tag = "home"
        when {
            null == supportFragmentManager.findFragmentByTag(tag) -> {
                val ft = supportFragmentManager.beginTransaction()
                ft.replace(R.id.fragment_container_search, SearchFragment.newInstance(), tag)
                ft.commit()
            }
        }
    }

    private val messageReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            // Get extra data included in the Intent
            val message = intent.getStringExtra("message")

            if (message == "started") {
                Log.d("HOMEAct", message)
                //   player_control.visibility = View.VISIBLE
            }
            if (message == "stopped") {
                Log.d("HOME", message)
                player_control.visibility = View.GONE
            }


        }
    }

    override fun onDestroy() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(messageReceiver)
        super.onDestroy()
    }

    @OnClick(R.id.ib_play_player)
    fun previewTrack() {
        Log.d("HOMEAct", "play/stop")
    }

    @OnClick(R.id.ib_next_player)
    fun nextTrack() {
        Log.d("HOMEAct", "next")
    }

}
