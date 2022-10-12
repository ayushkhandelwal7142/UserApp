package com.akstudios.userapp.ui.ebook

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.akstudios.userapp.R
import com.google.android.material.card.MaterialCardView

class EbookAdapter(val context: Context, private val list: ArrayList<EbookData>): RecyclerView.Adapter<EbookAdapter.EbookViewHolder>() {

    inner class EbookViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.ebookTitle)
        val btnDownload: ImageView = itemView.findViewById(R.id.btnDownload)
        val ebook: MaterialCardView = itemView.findViewById(R.id.ebook_itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EbookViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.ebook_item_layout, parent, false)

        return EbookViewHolder(view)
    }

    override fun onBindViewHolder(holder: EbookViewHolder, position: Int) {
       holder.title.text = list[position].title
        holder.ebook.setOnClickListener {
           val intent = Intent(context, PdfViewer::class.java)
            intent.putExtra("url", list[position].url)
            context.startActivity(intent)
        }
        holder.btnDownload.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setData(Uri.parse(list.get(position).url))
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}