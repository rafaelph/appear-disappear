package com.rafelds.appeardisappear

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.rafelds.appeardisappear.fragments.EmptyFragment
import com.rafelds.appeardisappear.fragments.ImageFragment

class ImageViewPagerAdapter(fragmentManager: FragmentManager)
    : FragmentPagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment? {
        return when (position) {
            0 -> ImageFragment()
            1 -> EmptyFragment()
            else -> null
        }
    }

    override fun getCount() = 2
}