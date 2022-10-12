package com.akstudios.userapp.ui.home.imageSlider

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.akstudios.userapp.R
import com.akstudios.userapp.ui.home.HomeFragment
import com.bumptech.glide.Glide
import com.smarteist.autoimageslider.SliderViewAdapter


 class SliderAdapter(val context: HomeFragment, private val sliderData: ArrayList<SliderData>) :
    SliderViewAdapter<SliderAdapter.SliderAdapterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup): SliderAdapterViewHolder {
        val inflate: View = LayoutInflater.from(parent.context).inflate(R.layout.slider_layout, null)
        return SliderAdapterViewHolder(inflate)
    }
    override fun onBindViewHolder(viewHolder: SliderAdapterViewHolder, position: Int) {

        Glide.with(viewHolder.itemView)
            .load(sliderData[position].imgUrl)
            .fitCenter()
            .into(viewHolder.imageViewBackground)
    }
    override fun getCount(): Int {
        return sliderData.size
    }

    inner class SliderAdapterViewHolder(itemView: View) : ViewHolder(itemView) {
        var imageViewBackground: ImageView = itemView.findViewById(R.id.myimage)
    }
}
