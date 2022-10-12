package com.akstudios.userapp.ui.notice

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.akstudios.userapp.R
import com.bumptech.glide.Glide

class NoticeAdapter(private val context: NoticeFragment, private val noticeData: ArrayList<NoticeData>):RecyclerView.Adapter<NoticeAdapter.NoticeDataViewHolder>() {
    inner class NoticeDataViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val noticeImage: ImageView = itemView.findViewById(R.id.noticeImage)
        val noticeDate: TextView = itemView.findViewById(R.id.noticeDate)
        val noticeTime: TextView = itemView.findViewById(R.id.noticeTime)
        val noticeTitle: TextView = itemView.findViewById(R.id.noticeTitle)
    }

     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticeDataViewHolder {
         val inflater = LayoutInflater.from(parent.context)
         val view = inflater.inflate(R.layout.notice_layout, parent, false)
         return NoticeDataViewHolder(view)
     }

     override fun onBindViewHolder(holder: NoticeDataViewHolder, position: Int) {
         Glide.with(context).load(noticeData[position].imageUrl).into(holder.noticeImage)
         holder.noticeTitle.text = noticeData[position].title
         holder.noticeDate.text = noticeData[position].date
         holder.noticeTime.text = noticeData[position].time
     }

     override fun getItemCount(): Int {
         return noticeData.size
     }
 }