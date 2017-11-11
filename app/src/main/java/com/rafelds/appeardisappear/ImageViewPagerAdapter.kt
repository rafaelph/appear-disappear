package com.rafelds.appeardisappear

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.rafelds.appeardisappear.fragments.FirstFragment
import com.rafelds.appeardisappear.fragments.SecondFragment
import com.rafelds.appeardisappear.fragments.ThirdFragment

class ImageViewPagerAdapter(fragmentManager: FragmentManager)
    : FragmentPagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment? {
        return when (position) {
            0 -> FirstFragment()
            1 -> SecondFragment()
            2 -> ThirdFragment()
            else -> null
        }
    }

    override fun getCount() = 3
}