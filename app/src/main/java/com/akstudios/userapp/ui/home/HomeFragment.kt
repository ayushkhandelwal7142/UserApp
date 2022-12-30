package com.akstudios.userapp.ui.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.akstudios.userapp.MainActivity
import com.akstudios.userapp.R
import com.akstudios.userapp.ui.home.imageSlider.SliderAdapter
import com.akstudios.userapp.ui.home.imageSlider.SliderData
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView


class HomeFragment : Fragment() {

    private lateinit var sliderView: SliderView
    private var sliderDataArrayList: ArrayList<SliderData> = arrayListOf()
    private lateinit var  sAdapter: SliderAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        sliderView = view.findViewById(R.id.slider)

        val url1 = "https://firebasestorage.googleapis.com/v0/b/schoolapp-2c09a.appspot.com/o/Gallery%20Images%2FIndependence%20Day%2F%5BB%404b2bc20%20jpg?alt=media&token=1f3e4e4c-0570-434e-a8d4-2572c68780f1"
        val url2 = "https://firebasestorage.googleapis.com/v0/b/schoolapp-2c09a.appspot.com/o/Gallery%20Images%2FIndependence%20Day%2F%5BB%40a860dc9%20jpg?alt=media&token=28bf682e-e370-4829-9ed5-70b90deab8a0"
        val url3 = "https://firebasestorage.googleapis.com/v0/b/schoolapp-2c09a.appspot.com/o/Gallery%20Images%2FIndependence%20Day%2F%5BB%4015c40a7%20jpg?alt=media&token=ad4f202f-c619-450b-b2f1-a2f21add95f8"

        sliderDataArrayList.add(SliderData(url1))
        sliderDataArrayList.add(SliderData(url2))
        sliderDataArrayList.add(SliderData(url3))

        sAdapter = SliderAdapter(this@HomeFragment, sliderDataArrayList)

        sliderView.apply {
            setIndicatorAnimation(IndicatorAnimationType.FILL)
            setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION)
            scrollTimeInSec = 3
            autoCycleDirection = SliderView.LAYOUT_DIRECTION_LTR
            isAutoCycle = true
            setSliderAdapter(sAdapter)
            startAutoCycle()
        }

        return view
    }
}