package com.rafelds.appeardisappear

import android.os.Bundle
import android.os.Handler
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.View.GONE
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_first.*


class MainActivity : AppCompatActivity() {

    companion object {
        private val DELAY_TO_RUN_HIDE_UI_MS = 3000L
    }

    private val handler = Handler()
    private val runnable = object : Runnable {
        override fun run() {
            UiUtils.hideSystemUI(window)
            handler.postDelayed(this, DELAY_TO_RUN_HIDE_UI_MS)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        UiUtils.hideSystemUI(window)

        view_pager.adapter = ImageViewPagerAdapter(supportFragmentManager)
        view_pager.addOnPageChangeListener( object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(x: Int) = Unit
            override fun onPageScrolled(x: Int, y: Float, z: Int) = Unit
            override fun onPageSelected(position: Int) {
                if (position == 1) {
                    first_fragment_image.visibility = GONE
                }
            }
        })
        handler.postDelayed(runnable, DELAY_TO_RUN_HIDE_UI_MS)
    }

    override fun onPause() {
        super.onPause()
        finish()
    }

}
