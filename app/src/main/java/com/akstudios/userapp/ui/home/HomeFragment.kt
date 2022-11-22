package com.akstudios.userapp.ui.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
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
        sAdapter = SliderAdapter(this@HomeFragment, sliderDataArrayList)

        val url1 = "https://www.geeksforgeeks.org/wp-content/uploads/gfg_200X200-1.png"
        val url2 = "https://qphs.fs.quoracdn.net/main-qimg-8e203d34a6a56345f86f1a92570557ba.webp"
        val url3 = "https://bizzbucket.co/wp-content/uploads/2020/08/Life-in-The-Metro-Blog-Title-22.png"

        sliderDataArrayList.add(SliderData(url1))
        sliderDataArrayList.add(SliderData(url2))
        sliderDataArrayList.add(SliderData(url3))

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