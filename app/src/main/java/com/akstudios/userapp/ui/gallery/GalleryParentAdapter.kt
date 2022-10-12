package com.akstudios.userapp.ui.gallery

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.akstudios.userapp.R

class GalleryParentAdapter(val context: Context, private val list: ArrayList<GalleryParentData>): RecyclerView.Adapter<GalleryParentAdapter.GalleryViewHolder>() {

    inner class GalleryViewHolder(private val itemView: View): RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.imageCategory)
        val parentRv: RecyclerView = itemView.findViewById(R.id.parent_rv)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.gallery_image_parent_layout, parent, false)
        return GalleryViewHolder(view)
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        holder.title.text = list[position].title
       val fAdapter = GalleryChildAdapter(context, list[position].list)
        holder.parentRv.apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter = fAdapter
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }
}