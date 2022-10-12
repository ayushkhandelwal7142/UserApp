package com.akstudios.userapp.ui.gallery

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.akstudios.userapp.R
import com.bumptech.glide.Glide

class GalleryChildAdapter(val context: Context, val list: ArrayList<GalleryChildData>):
    RecyclerView.Adapter<GalleryChildAdapter.MyViewHolder>() {

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val child_image = itemView.findViewById<ImageView>(R.id.child_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.gallery_image_child_layout, parent, false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Glide.with(context).load(list[position].url).into(holder.child_image)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}