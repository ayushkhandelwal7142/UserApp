package com.akstudios.userapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.akstudios.userapp.R
import com.bumptech.glide.Glide
import com.github.chrisbanes.photoview.PhotoView

class FullImageView : AppCompatActivity() {
    private lateinit var imgView: PhotoView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_image_view)

        imgView = findViewById(R.id.fullImageView)
        val img = intent.getStringExtra("image")

        Glide.with(this).load(img).into(imgView)
    }
}