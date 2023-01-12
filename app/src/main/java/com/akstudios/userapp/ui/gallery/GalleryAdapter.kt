package com.akstudios.userapp.ui.gallery

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.akstudios.userapp.ui.FullImageView
import com.akstudios.userapp.R
import com.bumptech.glide.Glide

class GalleryAdapter(private val context: GalleryFragment, private val list: ArrayList<GalleryData>): RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder>() {

    inner class GalleryViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.galleryImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.gallery_layout, parent, false)
        return GalleryViewHolder(view)
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        Glide.with(context).load(list[position].url).into(holder.imageView)

        holder.imageView.setOnClickListener {
            val intent = Intent(context.requireContext(), FullImageView::class.java)
            intent.putExtra("image", list[position].url)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}