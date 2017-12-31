package com.rafelds.appeardisappear.fragments

import android.content.Context.SENSOR_SERVICE
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import com.rafelds.appeardisappear.R

class FirstFragment : Fragment(), SensorEventListener {

    companion object {
        const val SENSITIVITY = 4
    }

    private lateinit var sensorManager: SensorManager
    private lateinit var proximitySensor: Sensor
    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) = Unit

    override fun onSensorChanged(event: SensorEvent) {
        val sensor = event.sensor
        if (sensor.type == Sensor.TYPE_PROXIMITY) {
            val sensorValue = event.values[0]
            if (sensorValue >= -SENSITIVITY && sensorValue <= SENSITIVITY) {
                view?.findViewById<ImageView>(R.id.first_fragment_image)?.visibility = VISIBLE
                sensorManager.unregisterListener(this)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(this, proximitySensor, SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onCreateView(inflater: LayoutInflater?,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        sensorManager = activity.getSystemService(SENSOR_SERVICE) as SensorManager
        proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)
        return inflater?.inflate(R.layout.fragment_first, container, false)
    }
}