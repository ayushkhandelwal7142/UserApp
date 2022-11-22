package com.akstudios.userapp.ui.about

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.akstudios.userapp.R


class AboutFragment : Fragment() {

    private lateinit var imgMap: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_about, container, false)
        imgMap = view.findViewById(R.id.img_map)

        imgMap.setOnClickListener {
            openMap()
        }
        return view
    }
    private fun openMap() {
        val uri = Uri.parse("geo:0, 0?q=Arya Cars, Gondia, Maharashtra")
        val intent = Intent(Intent.ACTION_VIEW, uri)
        intent.setPackage("com.google.android.apps.maps")
        startActivity(intent)
    }
}