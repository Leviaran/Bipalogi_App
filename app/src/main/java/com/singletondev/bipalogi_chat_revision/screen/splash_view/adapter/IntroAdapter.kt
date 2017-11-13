package com.singletondev.bipalogi_chat_revision.screen.splash_view.adapter

import android.graphics.Color
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.singletondev.bipalogi_chat_revision.screen.splash_view.fragment.IntroFragment

/**
 * Created by Randy Arba on 10/30/17.
 * This apps contains Bipalogi_Chat_Revision
 * @email randy.arba@gmail.com
 * @github https://github.com/Leviaran
 */

class IntroAdapter (var fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment = when (position) {
        0 -> IntroFragment.newInstance(Color.parseColor("#03A9F4"),position)
        1 -> IntroFragment.newInstance(Color.parseColor("#03A9F4"),position)
        else -> IntroFragment.newInstance(Color.parseColor("#4CAF50"),position)
    }

    override fun getCount(): Int = 3

}