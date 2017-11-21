package com.umusic.marcus.umusic

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.test.rule.ActivityTestRule
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar


/**
 * Created by Marcus on 11/21/2017.
 */
object EspressoTestUtil {
    /**
     * Disables progress bar animations for the views of the given activity rule
     *
     * @param activityTestRule The activity rule whose views will be checked
     */
    fun disableAnimations(
            activityTestRule: ActivityTestRule<out FragmentActivity>) {
        activityTestRule.activity.supportFragmentManager
                .registerFragmentLifecycleCallbacks(
                        object : FragmentManager.FragmentLifecycleCallbacks() {
                            override fun onFragmentViewCreated(fm: FragmentManager, f: Fragment, v: View,
                                                               savedInstanceState: Bundle) {
                                // traverse all views, if any is a progress bar, replace its animation
                                traverseViews(v)
                            }
                        }, true)
    }

    private fun traverseViews(view: View) {
        if (view is ViewGroup) {
            traverseViewGroup(view)
        } else {
            if (view is ProgressBar) {
                disableProgressBarAnimation(view)
            }
        }
    }

    private fun traverseViewGroup(view: ViewGroup) {
        if (view is RecyclerView) {
            disableRecyclerViewAnimations(view)
        } else {
            val count = view.childCount
            for (i in 0 until count) {
                traverseViews(view.getChildAt(i))
            }
        }
    }

    private fun disableRecyclerViewAnimations(view: RecyclerView) {
        view.itemAnimator = null
    }

    /**
     * necessary to run tests on older API levels where progress bar uses handler loop to animate.
     *
     * @param progressBar The progress bar whose animation will be swapped with a drawable
     */
    private fun disableProgressBarAnimation(progressBar: ProgressBar) {
        progressBar.indeterminateDrawable = ColorDrawable(Color.BLUE)
    }
}