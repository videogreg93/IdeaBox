package com.gregory.ideabox.views.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.gregory.ideabox.R

object ViewUtils {
    /**
     * Replace the current fragment without arguments
     *
     * @param fragmentToDisplay new fragment
     * @param addToBackStack    true if the new fragment should be added to the backstack
     */
    fun displayFragmentWithoutArgs(activity: FragmentActivity, fragmentToDisplay: Fragment, addToBackStack: Boolean) {
        val fragment = activity.supportFragmentManager.findFragmentByTag(fragmentToDisplay.javaClass.name)
        if (fragment != null) {
            activity.supportFragmentManager
                .beginTransaction()
                .replace(R.id.content, fragment, fragmentToDisplay.javaClass.name).commit()
        } else {
            val transaction = activity.supportFragmentManager
                .beginTransaction()
                .replace(R.id.content, fragmentToDisplay, fragmentToDisplay.javaClass.name)
            if (addToBackStack) {
                transaction.addToBackStack(fragmentToDisplay.javaClass.name)
            }
            transaction.commit()
        }
    }


    /**
     * Replace the current fragment
     *
     * @param fragmentToDisplay new fragment
     * @param addToBackStack    true if the new fragment should be added to the backstack
     * @param args              fragment arguments
     */
    fun displayFragmentWithArgs(
        activity: FragmentActivity,
        fragmentToDisplay: Fragment,
        addToBackStack: Boolean,
        args: Bundle?
    ) {
        fragmentToDisplay.arguments = args
        displayFragmentWithoutArgs(activity, fragmentToDisplay, addToBackStack)
    }

}