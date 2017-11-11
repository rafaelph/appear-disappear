package com.rafelds.appeardisappear

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


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
        view_pager.addOnPageChangeListener(FastSwipeOnPageListener(view_pager))

        handler.postDelayed(runnable, DELAY_TO_RUN_HIDE_UI_MS)
    }

}
