package com.rafelds.appeardisappear

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.os.Handler
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.View.GONE
import android.view.View.VISIBLE
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_image.*


class MainActivity : AppCompatActivity(), SensorEventListener {

    companion object {
        private const val DELAY_TO_RUN_HIDE_UI_MS = 3000L
        private const val SENSITIVITY = 3
    }


    private val handler = Handler()
    private val runnable = object : Runnable {
        override fun run() {
            UiUtils.hideSystemUI(window)
            handler.postDelayed(this, DELAY_TO_RUN_HIDE_UI_MS)
        }
    }

    private lateinit var sensorManager: SensorManager
    private lateinit var proximitySensor: Sensor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        UiUtils.hideSystemUI(window)

        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)

        view_pager.adapter = ImageViewPagerAdapter(supportFragmentManager)
        view_pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(x: Int) = Unit
            override fun onPageScrolled(x: Int, y: Float, z: Int) = Unit
            override fun onPageSelected(position: Int) {
                if (position == 1) {
                    first_fragment_image.visibility = GONE
                }
            }
        })
        handler.postDelayed(runnable, DELAY_TO_RUN_HIDE_UI_MS)
        view_pager.visibility = GONE
    }

    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(this, proximitySensor, SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
        finish()
    }

    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) = Unit

    override fun onSensorChanged(event: SensorEvent) {
        val sensor = event.sensor
        if (sensor.type == Sensor.TYPE_PROXIMITY) {
            val sensorValue = event.values[0]
            if (sensorValue >= -SENSITIVITY && sensorValue <= SENSITIVITY) {
                view_pager.visibility = VISIBLE
                sensorManager.unregisterListener(this)
            }
        }
    }
}
