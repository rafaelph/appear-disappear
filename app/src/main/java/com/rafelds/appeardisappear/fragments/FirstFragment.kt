package com.rafelds.appeardisappear.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import com.rafelds.appeardisappear.R

class FirstFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_first, container, false)
        view?.let {
            view.findViewById<ViewGroup>(R.id.first_fragment).setOnLongClickListener {
                view.findViewById<ImageView>(R.id.first_fragment_image).visibility = VISIBLE
                true
            }
        }
        return view
    }
}