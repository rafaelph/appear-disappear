package com.rafelds.appeardisappear

import android.support.v4.view.ViewPager

class FastSwipeOnPageListener(private val viewPager: ViewPager) : ViewPager.OnPageChangeListener {

    private var lastPositionOffset = 0f
    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        if (positionOffset < lastPositionOffset && positionOffset < 0.7) {
            viewPager.currentItem = position
        } else if (positionOffset < lastPositionOffset && positionOffset > 0.3) {
            viewPager.currentItem = position + 1
        }
        lastPositionOffset = positionOffset
    }

    override fun onPageSelected(position: Int) {
    }

}