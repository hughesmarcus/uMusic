package com.umusic.marcus.umusic.ui.player

import android.content.Context
import android.content.ServiceConnection
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import com.umusic.marcus.umusic.R
import com.umusic.marcus.umusic.UMusicApplication
import com.umusic.marcus.umusic.data.model.Album
import kotlinx.android.synthetic.main.fragment_mini_player.*
import javax.inject.Inject


class MiniPlayerFragment : Fragment(), AudioPlayerPresenter.View {
    override fun context(): Context? {
        return activity
    }

    override fun onStartAudioService(trackUrl: String, serviceConnection: ServiceConnection) {

    }

    override fun setInfoTrackPlayer(trackPosition: Int) {

    }

    override fun setTimeStart(trackCurrentPosition: Int) {

    }

    override fun setTimeFinished(audioPlayerService: AudioPlayerService) {

    }

    override fun onResetTrackDuration() {

    }

    override fun setInfoTrackPlayer(trackPosition: Int, album: Album) {

    }

    override fun setTrackPlayer(trackPosition: Int) {

    }


    private var mListener: OnFragmentInteractionListener? = null
    @Inject
    lateinit var audioPlayerPresenter: AudioPlayerPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        ((activity.application as UMusicApplication).appComponent)!!.inject(this)
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_mini_player, container, false)
        ButterKnife.bind(this, view)
        audioPlayerPresenter.view = this
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {

        initButtons()
    }

    private fun initButtons() {
        val btn_play = ib_play_player
        btn_play.setOnClickListener {
            Log.d("HOMEAct", "play/stop")
            audioPlayerPresenter.onPlayPauseTrack()
        }
        val btn_next = ib_next_player
        btn_next.setOnClickListener {
            audioPlayerPresenter.onNextTrack()
            Log.d("HOMEAct", "next")
        }

    }

    override fun isPlay() {
        ib_play_player!!.setImageResource(android.R.drawable.ic_media_play)
    }

    override fun isPause() {
        ib_play_player!!.setImageResource(android.R.drawable.ic_media_pause)
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        if (mListener != null) {
            mListener!!.onFragmentInteraction(uri)
        }
    }

    override fun onDestroyView() {
        audioPlayerPresenter.terminate()
        super.onDestroyView()
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html) for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {

        fun newInstance(): MiniPlayerFragment {
            val fragment = MiniPlayerFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}// Required empty public constructor
